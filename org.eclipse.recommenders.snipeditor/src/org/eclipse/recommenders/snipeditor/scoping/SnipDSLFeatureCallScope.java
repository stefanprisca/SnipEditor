package org.eclipse.recommenders.snipeditor.scoping;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.xbase.XbasePackage;
import org.eclipse.xtext.xbase.scoping.batch.FeatureScopes;

public class SnipDSLFeatureCallScope extends FeatureScopes {

	@Override
	public boolean isFeatureCallScope(EReference reference) {
		return reference == XbasePackage.Literals.XABSTRACT_FEATURE_CALL__FEATURE;
	}
}
