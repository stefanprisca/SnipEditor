/**
 * Copyright (c) 2010, 2013 Darmstadt University of Technology.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Marcel Bruch - initial API and implementation.
 */
package org.eclipse.recommenders.snipmatch;

import static com.google.common.collect.ImmutableSet.copyOf;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.eclipse.recommenders.utils.IOUtils;
import org.eclipse.recommenders.utils.gson.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.io.PatternFilenameFilter;

public class SimpleSnippetRepository implements ISnippetRepository {

    private static final PatternFilenameFilter SNIPPETS_FILENAME_FILTER = new PatternFilenameFilter(
            "^.+?\\.json");
    private static final String F_DESCRIPTION = "description";
    private static final String F_PATH = "path";

    private Logger log = LoggerFactory.getLogger(getClass());

    SoftReference<Set<Snippet>> snippets = new SoftReference<Set<Snippet>>(null);

    private File snippetsdir;
    private File indexdir;
    private Directory directory;
    private IndexReader reader;

    public SimpleSnippetRepository(File basedir) {
        snippetsdir = new File(basedir, "snippets");
        indexdir = new File(basedir, "index");
    }

    @Override
    public ImmutableSet<Snippet> getSnippets() {
        // TODO MB: this is a costly operation that works only well with small
        // repos.
        TreeSet<Snippet> res = Sets.newTreeSet();
        for (File fSnippet : snippetsdir.listFiles(SNIPPETS_FILENAME_FILTER)) {
            Snippet snippet = loadSnippet(fSnippet);
            res.add(snippet);
        }
        return copyOf(res);
    }

    @Override
    public void open() throws IOException {
        snippetsdir.mkdirs();
        indexdir.mkdirs();
        directory = FSDirectory.open(indexdir);
        if (!IndexReader.indexExists(directory)) {
            index();
        }
        reader = IndexReader.open(FSDirectory.open(indexdir));
    }

    public void index() throws IOException {
        // TODO MB: this needs to be refined later. We should add the
        // description/aliases to the index. Search should
        // not
        // need to deserialize snippets to display the description string...
        File[] fSnippets = snippetsdir.listFiles(SNIPPETS_FILENAME_FILTER);
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_35,
                analyzer);
        config.setOpenMode(OpenMode.CREATE);
        IndexWriter writer = new IndexWriter(directory, config);

        for (File fSnippet : fSnippets) {
            try {
                Snippet snippet = loadSnippet(fSnippet);
                Document doc = new Document();
                doc.add(new Field(F_PATH, fSnippet.getPath(), Field.Store.YES,
                        Field.Index.NO));
                doc.add(new Field(F_DESCRIPTION, snippet.getDescription(),
                        Field.Store.YES, Field.Index.ANALYZED));
                writer.addDocument(doc);
            } catch (Exception e) {
                log.error("Failed to index snippet in " + fSnippet, e);
            }
        }
        writer.close();
    }

    @Override
    public void close() throws IOException {
        IOUtils.closeQuietly(reader);
        IOUtils.closeQuietly(directory);
    }

    @Override
    public List<Snippet> search(String query) {
        if (isBlank(query)) {
            return ImmutableList.copyOf(getSnippets());
        }
        // TODO MB: the query parser is a bit too relaxed I think
        query = query.trim() + "*";

        List<Snippet> results = Lists.newLinkedList();
        IndexSearcher searcher = null;
        try {
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_35);
            QueryParser parser = new QueryParser(Version.LUCENE_35,
                    F_DESCRIPTION, analyzer);
            parser.setAllowLeadingWildcard(true);
            Query q = parser.parse(query); // Add star to enable Wildcard
                                           // Searches

            searcher = new IndexSearcher(reader);
            for (ScoreDoc hit : searcher.search(q, null, 100).scoreDocs) {
                Document doc = searcher.doc(hit.doc);
                File fSnippet = new File(doc.get(F_PATH));
                if (fSnippet.exists()) {
                    Snippet snippet = loadSnippet(fSnippet);
                    results.add(snippet);
                }
            }
        } catch (IOException e) {
            log.error("Exception occurred while searching the snippet index.",
                    e);
        } catch (ParseException e) {
            log.error("Failed to parse query", e);
        } finally {
            IOUtils.closeQuietly(searcher);
        }
        return results;
    }

    private Snippet loadSnippet(File file) {
        Snippet snippet = GsonUtil.deserialize(file, Snippet.class);
        snippet.setLocation(file);
        return snippet;
    }

}
