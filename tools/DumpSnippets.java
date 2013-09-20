package org.eclipse.recommenders.internal.snipmatch.rcp;

import java.io.File;
import java.util.UUID;

import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jface.text.templates.Template;
import org.eclipse.jface.text.templates.persistence.TemplateStore;
import org.eclipse.recommenders.snipmatch.Snippet;
import org.eclipse.recommenders.utils.gson.GsonUtil;

import com.google.common.collect.Lists;
import com.google.common.io.Files;

public class DumpSnippets implements Runnable {

    @Override
    public void run() {
        File out = Files.createTempDir();
        TemplateStore store = JavaPlugin.getDefault().getTemplateStore();
        for (Template t : store.getTemplates()) {
            Snippet s = new Snippet(UUID.randomUUID(), t.getName(), t.getDescription(), Lists.<String>newArrayList(),
                    t.getPattern());
            System.out.println(t);
            File jsonFile = new File(out, s.getName() + ".json");
            int i = 1;
            if (jsonFile.exists()) {
                jsonFile = new File(out, s.getName() + i++ + ".json");
            }
            GsonUtil.serialize(s, jsonFile);
        }
        System.out.println(out);

    }

}
