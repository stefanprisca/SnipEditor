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

import java.io.IOException;
import java.util.List;

import com.google.common.collect.ImmutableSet;

public interface ISnippetRepository {

    ImmutableSet<Snippet> getSnippets();

    void close() throws IOException;

    void open() throws IOException;

    List<Snippet> search(String query) throws IOException;
}
