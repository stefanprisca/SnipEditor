package org.eclipse.recommenders.snipeditor.ui.contentassist;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.recommenders.snipeditor.snipDSL.impl.methodImpl;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XbasePackage;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xbase.ui.contentassist.XbaseReferenceProposalCreator;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

@SuppressWarnings("all")
public class SnipDSLReferenceProposalCreator extends
		XbaseReferenceProposalCreator {

	public Iterable<IEObjectDescription> queryScope(IScope scope, EObject model, EReference reference, Predicate<IEObjectDescription> filter) {
		System.out.println(model+"\n"+reference+"\n"+scope);
		
		return scope.getAllElements();
	}
	
	@Override
	public void lookupCrossReference(IScope scope, EObject model, EReference reference,
			ICompletionProposalAcceptor acceptor, Predicate<IEObjectDescription> filter,
			Function<IEObjectDescription, ICompletionProposal> proposalFactory) {
		if (TypesPackage.Literals.JVM_TYPE.isSuperTypeOf(getEReferenceType(model, reference))) {
			if (!isShowTypeProposals() && !isShowSmartProposals())
				return;
		}
		if (reference == XbasePackage.Literals.XABSTRACT_FEATURE_CALL__FEATURE) {
			if (!isShowShortestSugar() && !isShowJavaLikeProposals() && !isShowSmartProposals())
				return;
		//	System.out.println("abstract feature");
		}
		Function<IEObjectDescription, ICompletionProposal> wrappedFactory = getWrappedFactory(model, reference, proposalFactory);
		Iterable<IEObjectDescription> candidates = queryScope(scope, model, reference, filter);
		
				
		for (IEObjectDescription candidate : candidates) {
			if (!acceptor.canAcceptMoreProposals())
				return;
			
			//checks and skips Extensions proposals
			if(candidate.getEObjectOrProxy() instanceof JvmOperation)
			{
				JvmOperation operation=(JvmOperation)candidate.getEObjectOrProxy();
				if(operation.eResource().getURI().toString().contains("ObjectExtensions")){
					//System.out.println("an object extenssion");
					continue;
				}
				//System.out.println(operation.eResource().getURI().toString());
			}
			//System.out.println(candidate);
			if (filter.apply(candidate)) {
				acceptor.accept(wrappedFactory.apply(candidate));
			}
		}
	}
}
