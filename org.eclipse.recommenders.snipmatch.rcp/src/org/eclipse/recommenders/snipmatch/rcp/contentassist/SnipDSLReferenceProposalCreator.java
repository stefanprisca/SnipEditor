package org.eclipse.recommenders.snipmatch.rcp.contentassist;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.recommenders.templates.rcp.snipDSL.jFaceExpression;
import org.eclipse.recommenders.templates.rcp.snipDSL.impl.methodImpl;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XbasePackage;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.ui.contentassist.XbaseReferenceProposalCreator;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.inject.Inject;

@SuppressWarnings("all")
public class SnipDSLReferenceProposalCreator extends
        XbaseReferenceProposalCreator {

    // @Inject
    // private SnipDSLScopeProvider scopeProvider;

    public Iterable<IEObjectDescription> queryScope(IScope scope,
            EObject model, EReference reference,
            Predicate<IEObjectDescription> filter) {
        // System.out.println("Content assist scope"+scope.getAllElements());
        return scope.getAllElements();
    }

    @Override
    public void lookupCrossReference(IScope scope, EObject model,
            EReference reference, ICompletionProposalAcceptor acceptor,
            Predicate<IEObjectDescription> filter,
            Function<IEObjectDescription, ICompletionProposal> proposalFactory) {

        if (TypesPackage.Literals.JVM_TYPE.isSuperTypeOf(getEReferenceType(
                model, reference))) {
            if (!isShowTypeProposals() && !isShowSmartProposals())
                return;
        }

        if (reference == XbasePackage.Literals.XABSTRACT_FEATURE_CALL__FEATURE) {
            if (!isShowShortestSugar() && !isShowJavaLikeProposals()
                    && !isShowSmartProposals())
                return;
            // System.out.println("abstract feature");
        }
        // System.out.println("Works fine up untill here");
        Function<IEObjectDescription, ICompletionProposal> wrappedFactory = getWrappedFactory(
                model, reference, proposalFactory);
        Iterable<IEObjectDescription> candidates = queryScope(
                getScopeProvider().getScope(model, reference), model,
                reference, filter);

        for (IEObjectDescription candidate : candidates) {
            if (!acceptor.canAcceptMoreProposals())
                return;

            // checks and skips Extensions proposals
            // System.out.println("Candidate: "+candidate.getName());
            if (Pattern.matches("[a-zA-Z${}]+", candidate.getName().toString()) == false) {
                // System.out.println(candidate.getName());

                continue;
                // System.out.println(operation.eResource().getURI().toString());
            }
            // System.out.println(candidate);
            if (filter.apply(candidate)) {
                acceptor.accept(wrappedFactory.apply(candidate));
            }
        }
    }

    /*
     * @Override public IScopeProvider getScopeProvider() {
     * System.out.println("This is my scope provider for references:"
     * +scopeProvider); return scopeProvider; }
     */
}
