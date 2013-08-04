package org.eclipse.recommenders.snipeditor.typing;

import java.util.List;

import org.eclipse.recommenders.snipeditor.snipDSL.blockAssignment;
import org.eclipse.recommenders.snipeditor.snipDSL.jFaceSpecificLiteral;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.XClosure;
import org.eclipse.xtext.xbase.XConstructorCall;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XStringLiteral;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.annotations.typesystem.XbaseWithAnnotationsTypeComputer;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.typesystem.computation.ClosureTypeComputer;
import org.eclipse.xtext.xbase.typesystem.computation.IConstructorLinkingCandidate;
import org.eclipse.xtext.xbase.typesystem.computation.ILinkingCandidate;
import org.eclipse.xtext.xbase.typesystem.computation.ITypeComputationState;
import org.eclipse.xtext.xbase.typesystem.computation.ITypeExpectation;
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
		}else if (expression instanceof XConstructorCall) {
			_computeTypes((XConstructorCall)expression, state);
		} else if (expression instanceof XClosure) {
			_computeTypes((XClosure)expression, state);
		} else if (expression instanceof XVariableDeclaration) {
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
	
	
	
	protected void _computeTypes(XConstructorCall var, ITypeComputationState state){
		
		if(var.getArguments().size()>0){
			if(var.getArguments().get(0) instanceof XClosure){
				System.out.println("got a closure : ");
				List<? extends IConstructorLinkingCandidate> candidates = state.getLinkingCandidates(var);
				ILinkingCandidate best = getBestCandidate(candidates);
				System.out.println(best.getFeature().getIdentifier());
				String typeString=best.getFeature().getIdentifier().substring(
									best.getFeature().getIdentifier().indexOf('(')+1,
									best.getFeature().getIdentifier().indexOf(')'));
				try {
					System.out.println(getTypeForName(Class.forName(typeString),
							state).getSimpleName());
					JvmTypeReference typeAux1 = getTypeForName(Class.forName(typeString),state).toTypeReference();
					typeAux1=typeBuilder.addArrayTypeDimension(typeAux1);
					
					LightweightTypeReference finalType=state.getConverter().toLightweightReference(typeAux1);
					
					state.acceptActualType(finalType);
				//	state.computeTypes(var.getArguments().get(0));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else
			super._computeTypes(var, state);
	}
	protected void _computeTypes(XClosure object, ITypeComputationState state) {
		//do nothing
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
