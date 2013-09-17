package org.eclipse.recommenders.snipeditor.scoping;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.scoping.LocalVariableScopeContext;
import org.eclipse.xtext.xbase.scoping.XImportSectionNamespaceScopeProvider;
import org.eclipse.xtext.xbase.scoping.batch.FeatureScopes;
import org.eclipse.xtext.xbase.scoping.batch.XbaseBatchScopeProvider;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class SnipDSLLocalScope extends XbaseBatchScopeProvider {
    @Inject
    ImportedNamespaceAwareLocalScopeProvider inalsp;
    @Inject
    @Named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE)
    private IScopeProvider delegate;
    @Inject
    private SnipDSLFeatureCallScope featureScopes;

    @Override
    public IScope getScope(EObject context, EReference reference) {

        if (context == null)
            throw new NullPointerException("context");
        if (reference instanceof XExpression) {
            // return Scopes.scopeFor(context.eContents());
            System.out.println("An Expression!");
        }
        System.out.println("#1  " + context.eClass().getName() + "     "
                + reference.getEReferenceType().getName());
        if (super.getScope(context, reference) != null) {
            return super.getScope(context, reference);
        }
        return delegate.getScope(context, reference);
    }

}
