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
package org.eclipse.recommenders.templates;

import org.eclipse.recommenders.templates.SnipDSLStandaloneSetupGenerated;

/**
 * Initialization support for running Xtext languages without equinox extension
 * registry
 */
public class SnipDSLStandaloneSetup extends SnipDSLStandaloneSetupGenerated {

    public static void doSetup() {
        new SnipDSLStandaloneSetup().createInjectorAndDoEMFRegistration();
    }
}
