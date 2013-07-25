package org.eclipse.recommenders.snipeditor.typing;

import org.eclipse.recommenders.snipeditor.snipDSL.blockAssignment;
import org.eclipse.recommenders.snipeditor.snipDSL.jFaceSpecificLiteral;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XStringLiteral;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.annotations.typesystem.XbaseWithAnnotationsTypeComputer;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.typesystem.computation.ITypeComputationState;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;
import com.google.inject.Inject;

@SuppressWarnings("restriction")
public class SnipDSLTypeComputer extends XbaseWithAnnotationsTypeComputer {
	@Inject JvmTypesBuilder typeBuilder;

	
	/*protected void _computeTypes(final abstractTypeName object, ITypeComputationState state) {
		System.out.println("got an abstract type");
		JvmTypeReference declaredType = object.getType();
		LightweightTypeReference lightweightTypeReference = declaredType != null ? state.getConverter().toLightweightReference(declaredType) : null;
		//LightweightTypeReference primitiveVoid = getPrimitiveVoid(state);
		state.acceptActualType(lightweightTypeReference);
	}*/
	/*protected void _computeTypes(final XAssignment assignment, ITypeComputationState state) {
		List<? extends IFeatureLinkingCandidate> candidates = state.getLinkingCandidates(assignment);
		ILinkingCandidate best = getBestCandidate(candidates);
		JvmIdentifiableElement feature = best.getFeature();
		if (feature != null && mustDiscardRefinement(feature)) {
			state.discardReassignedTypes(feature);
		}
		best.applyToComputationState();
	}
	*/
	
	public void computeTypes(XExpression expression, ITypeComputationState state) {
		if (expression instanceof blockAssignment) {
			this._computeTypes((blockAssignment)expression, state);
		}else /*if (expression instanceof arrayAssignment) {
			this._computeTypes((arrayAssignment)expression, state);
		}else */if (expression instanceof XVariableDeclaration) {
			this._computeTypes((XVariableDeclaration)expression, state);
		}else if (expression instanceof jFaceSpecificLiteral) {
			this._computeTypes((jFaceSpecificLiteral)expression, state);
		}else{
			super.computeTypes(expression, state);
		}
	}

	protected void _computeTypes(blockAssignment blkAssign, ITypeComputationState state)
	{
		System.out.println("got a block assign type");

		LightweightTypeReference type=getTypeForName(Object.class, state);
		
		//TODO: better implementation
		for(XExpression expr: blkAssign.getValues()){
			if(expr instanceof XStringLiteral){
				type=getTypeForName(XStringLiteral.class, state);
			}else{
				type=getTypeForName(Integer.class, state);
			}
		}
		state.acceptActualType(type);
	}
	/*protected void _computeTypes(arrayAssignment arrAssign, ITypeComputationState state)
	{
		System.out.println("got a array assign type");
		//state.computeTypes(arrAssign.getArguments().getExpression());
		state.acceptActualType(state.getConverter().toLightweightReference(arrAssign.getJType()));
	}*/
	protected void _computeTypes(XVariableDeclaration var, ITypeComputationState state){
		if(var.getName().contains("freeName") || 
				var.getName().contains("newName"))
		{
			System.out.println("an abstract declaration");
			String computedVarName=var.getName();
			int beginIndex=computedVarName.indexOf('(');
			int endIndex=computedVarName.indexOf(')');
			String resultName=computedVarName.substring(beginIndex+1, endIndex);
			System.out.println(resultName+"  "+var.isWriteable());
			var.setName("${"+resultName+"}");
		}
		super._computeTypes(var, state);
	}
	protected void _computeTypes(jFaceSpecificLiteral expression,
			ITypeComputationState state) {
		// TODO Auto-generated method stub
		state.acceptActualType(getTypeForName(Object.class, state));
	}
	@Override
	protected boolean mustDiscardRefinement(JvmIdentifiableElement feature) {
		if (feature instanceof XVariableDeclaration) {
			return !((XVariableDeclaration) feature).isWriteable();
		}
		if (feature instanceof JvmField) {
			return !((JvmField) feature).isFinal();
		}
		return false;
	}
}
