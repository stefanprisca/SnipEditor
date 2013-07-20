package org.eclipse.recommenders.snipeditor.scoping;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.recommenders.snipeditor.snipDSL.impl.XFeatureCallImpl;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.xbase.XAbstractFeatureCall;
import org.eclipse.xtext.xbase.scoping.batch.FeatureScopes;
import org.eclipse.xtext.xbase.scoping.batch.XbaseBatchScopeProvider;
import org.eclipse.xtext.xbase.typesystem.IBatchTypeResolver;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class SnipDSLScopeProvider extends XbaseBatchScopeProvider {
	@Inject
	private FeatureScopes featureScopes;
	
	@Inject
	@Named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE)
	private IScopeProvider delegate;
	
	@Inject
	private IBatchTypeResolver typeResolver;
	
	public IScope getScope(EObject context, EReference reference) {
			if (context == null || context.eResource() == null || context.eResource().getResourceSet() == null) {
			return IScope.NULLSCOPE;
		}
		/*if (isFeatureCallScope(reference)) {
			if (context instanceof XAbstractFeatureCall) {
				IScope result = typeResolver.getFeatureScope((XAbstractFeatureCall) context);
				return result;
			}
			return IScope.NULLSCOPE;
		}*/
		return delegateGetScope(context, reference);
	
	}
}
