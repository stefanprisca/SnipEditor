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
package org.eclipse.recommenders.internal.snipmatch.rcp;

import static com.google.inject.Scopes.SINGLETON;

import javax.inject.Singleton;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.recommenders.snipmatch.ISnippetRepository;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.themes.ITheme;
import org.eclipse.ui.themes.IThemeManager;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class SnipmatchRcpModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ISnippetRepository.class).to(EclipseSnippetRepository.class).in(
                SINGLETON);
        bind(EclipseSnippetRepository.class).in(SINGLETON);
        bind(TemplateProcessor.class).in(SINGLETON);

    }

    @Provides
    @Singleton
    public SnipmatchRcpPreferences provide(IWorkbench wb) {
        IEclipseContext context = (IEclipseContext) wb
                .getService(IEclipseContext.class);
        SnipmatchRcpPreferences prefs = ContextInjectionFactory.make(
                SnipmatchRcpPreferences.class, context);
        return prefs;
    }

    @Provides
    public IThemeManager provideThemeManager(IWorkbench wb)
            throws PartInitException {
        return wb.getThemeManager();
    }

    @Provides
    public ITheme provideThemeManager(IThemeManager mgr)
            throws PartInitException {
        return mgr.getCurrentTheme();
    }

    @Provides
    public ColorRegistry provideColorRegistry(ITheme theme)
            throws PartInitException {
        return theme.getColorRegistry();
    }

    @Provides
    public FontRegistry provideFontRegistry(ITheme theme)
            throws PartInitException {
        return theme.getFontRegistry();
    }

}
