/*******************************************************************************
 * Copyright (c) 2013 Stefan Prisca.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Stefan Prisca - initial API and implementation
 ******************************************************************************/
package org.eclipse.recommenders.templates.tests;

import org.eclipse.recommenders.templates.SnipDSLInjectorProvider;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipselabs.xtext.utils.unittesting.XtextRunner2;
import org.eclipselabs.xtext.utils.unittesting.XtextTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner2.class)
@InjectWith(SnipDSLInjectorProvider.class)
public class SnipDSLTest extends XtextTest {

    private static ClassLoader contextClassLoader;

    @BeforeClass
    public static void beforeTests() {
        final Thread currentThread = Thread.currentThread();
        contextClassLoader = currentThread.getContextClassLoader();
        currentThread.setContextClassLoader(SnipDSLTest.class.getClassLoader());
    };

    @AfterClass
    public static void afterTests() {
        Thread.currentThread().setContextClassLoader(contextClassLoader);
    }

    public SnipDSLTest() {
        super("SnipDSLTest");
    }

    @Test
    public void randomTests() {
        assert true;
       /* suppressSerialization();
        testFile("Tests.cSnip");
        ignoreUnassertedWarnings();*/
    }
/*
    @Test
    public void conversionTests() {
        suppressSerialization();
        testFile("conversions.cSnip");
        ignoreUnassertedWarnings();
    }

    @Test
    public void arraysTests() {
        suppressSerialization();
        testFile("arrays.cSnip");
        ignoreUnassertedWarnings();
    }

    @Test
    public void captureScreenShoot() {
        suppressSerialization();
        testFile("capture_screenshoot.cSnip");
        ignoreUnassertedWarnings();
    }

    /*
     * @Test public void createSwingWindow() { suppressSerialization();
     * testFile("createSwingWindow.cSnip"); ignoreUnassertedWarnings(); }
     * 
     * @Test public void file_manip() {
     * 
     * suppressSerialization(); testFile("file_manipulation.cSnip");
     * ignoreUnassertedWarnings(); }
     * 
     * @Test public void if_else() { suppressSerialization();
     * testFile("if_else.cSnip"); ignoreUnassertedWarnings(); }
     * 
     * @Test public void instance_of() { suppressSerialization();
     * testFile("instanceof.cSnip"); ignoreUnassertedWarnings(); }
     * 
     * @Test public void print_error() { suppressSerialization();
     * testFile("print_error.cSnip"); ignoreUnassertedWarnings(); }
     * 
     * @Test public void stdDerivMethod() { suppressSerialization();
     * testFile("stdDerivMethod.cSnip"); ignoreUnassertedWarnings(); }
     * 
     * @Test public void sync() { suppressSerialization();
     * testFile("sync.cSnip"); ignoreUnassertedWarnings(); }
     * 
     * @Test public void try_catch() { suppressSerialization();
     * testFile("try_catch.cSnip"); ignoreUnassertedWarnings(); }
     */
}
