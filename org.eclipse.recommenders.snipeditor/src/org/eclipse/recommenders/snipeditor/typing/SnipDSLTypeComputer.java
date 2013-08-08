package org.eclipse.recommenders.snipeditor.typing;

import java.util.List;

import org.eclipse.recommenders.snipeditor.snipDSL.AssignmentWithClosure;
import org.eclipse.recommenders.snipeditor.snipDSL.ClassicForLoopExpression;
import org.eclipse.recommenders.snipeditor.snipDSL.FeatureCallWithClosure;
import org.eclipse.recommenders.snipeditor.snipDSL.blockAssignment;
import org.eclipse.recommenders.snipeditor.snipDSL.jFaceSpecificLiteral;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.XAssignment;
import org.eclipse.xtext.xbase.XClosure;
import org.eclipse.xtext.xbase.XConstructorCall;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XStringLiteral;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.annotations.typesystem.XbaseWithAnnotationsTypeComputer;
import org.eclipse.xtext.xbase.impl.XAssignmentImpl;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.typesystem.computation.IConstructorLinkingCandidate;
import org.eclipse.xtext.xbase.typesystem.computation.ILinkingCandidate;
import org.eclipse.xtext.xbase.typesystem.computation.ITypeComputationState;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;

import com.google.inject.Inject;

@SuppressWarnings("restriction")
public class SnipDSLTypeComputer extends XbaseWithAnnotationsTypeComputer {
	@Inject JvmTypesBuilder typeBuilder;

	public void computeTypes(XExpression expression, ITypeComputationState state) {
		if (expression instanceof blockAssignment) {
			this._computeTypes((blockAssignment)expression, state);
		}else if (expression instanceof XConstructorCall) {
			_computeTypes((XConstructorCall)expression, state);
		} else if (expression instanceof XClosure) {
			_computeTypes((XClosure)expression, state);
		} else if (expression instanceof FeatureCallWithClosure) {
			_computeTypes((FeatureCallWithClosure)expression, state);
		} else  if (expression instanceof AssignmentWithClosure) {
			_computeTypes((AssignmentWithClosure)expression, state);
		} else if (expression instanceof ClassicForLoopExpression) {
			_computeTypes((ClassicForLoopExpression)expression, state);
		} else if (expression instanceof jFaceSpecificLiteral) {
			this._computeTypes((jFaceSpecificLiteral)expression, state);
		}else {
			super.computeTypes(expression, state);
		}
	}
	
	
	




	protected void _computeTypes(ClassicForLoopExpression expression,
			ITypeComputationState state) {
		// TODO Auto-generated method stub
		System.out.println("Got a classic for");
		if(expression.getDeclaredParam().getParameterType()!=null)
			state.acceptActualType(state.getConverter().toLightweightReference(expression.getDeclaredParam().getParameterType()));
	
		
	}







	protected void _computeTypes(AssignmentWithClosure expression,
			ITypeComputationState state) {
		// TODO Set the correct type!
		//System.out.println("Got an assignment call with closure ");
		
		//super._computeTypes((XAssignment)expression.getValue(), state);
		 
				
	}



	protected void _computeTypes(FeatureCallWithClosure expression,
			ITypeComputationState state) {
		// TODO Set the correct type!
		//System.out.println("Got a feeature call with closure");
		//state.acceptActualType(getTypeForName(Object.class, state));
		
	}






/**
 * Computes the type for a block assignment 
 * e.g.:{1,2,3}
 * It loops through the elements in the block and takes their type
 * @param blkAssign the feature to compute type for
 * @param state the computation state
 */
	protected void _computeTypes(blockAssignment blkAssign, ITypeComputationState state)
	{
		//System.out.println("got a block assign type");

		LightweightTypeReference type=getTypeForName(Object.class, state);
		
		//TODO: set to error if different types in features!-> solved in the validator
		//This only takes the first element in the block, and applies the type of that element,
		//as if there are different types in the block, the validator prompts an error 
		XExpression expr=blkAssign.getValues().get(0);
		if(expr instanceof XStringLiteral){
				type=getTypeForName(String.class, state);
		}else{
				type=getTypeForName(Integer.class, state);
		}
		JvmTypeReference auxType= type.toTypeReference();
		auxType=typeBuilder.addArrayTypeDimension(auxType);
		//System.out.println(auxType.getSimpleName());
		state.acceptActualType(state.getConverter().toLightweightReference(auxType));
	}
	/**
	 * Compute type for var declaration
	 * Sets the name accordingly if it has jface specific structure
	 * e.g.: ${freeName(SomeName)} => ${SomeName}
	 */
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
	
	/**
	 * Computes the type for the constructor
	 * Created to include the constructor with [] closure
	 * Takes the name of the declared type, converts it in a class and sets the type 
	 * accordingly. If it encounters primitives, sets the type accordingly
	 */
	
	protected void _computeTypes(XConstructorCall var, ITypeComputationState state){
		
		if(var.getArguments().size()>0){
			if(var.getArguments().get(0) instanceof XClosure){
				List<? extends IConstructorLinkingCandidate> candidates = state.getLinkingCandidates(var);
				ILinkingCandidate best = getBestCandidate(candidates);
				String typeString=best.getFeature().getQualifiedName();
				try {
					JvmTypeReference typeAux1 = getTypeForName(Class.forName(typeString),state).toTypeReference();
					typeAux1=typeBuilder.addArrayTypeDimension(typeAux1);
					
					LightweightTypeReference finalType=state.getConverter().toLightweightReference(typeAux1);
					
					state.acceptActualType(finalType);
				//	state.computeTypes(var.getArguments().get(0));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					JvmTypeReference typeAux1;
					if(typeString == "int"){
						typeAux1 = getTypeForName(int.class,state).toTypeReference();
					}else if(typeString ==  "double"){
						typeAux1 = getTypeForName(int.class,state).toTypeReference();
					}else if(typeString ==  "float"){
						typeAux1 = getTypeForName(float.class,state).toTypeReference();
					}else if(typeString ==  "long"){
						typeAux1 = getTypeForName(long.class,state).toTypeReference();
					}else if(typeString ==  "char"){
						typeAux1 = getTypeForName(char.class,state).toTypeReference();
					}else if(typeString ==  "byte"){
						typeAux1 = getTypeForName(byte.class,state).toTypeReference();
					}
					else{
						typeAux1 = getTypeForName(Object.class,state).toTypeReference();
					}
					
					
					typeAux1=typeBuilder.addArrayTypeDimension(typeAux1);
					LightweightTypeReference finalType=state.getConverter().toLightweightReference(typeAux1);
					state.acceptActualType(finalType);
					//e.printStackTrace();
				}
			}
		}else
			super._computeTypes(var, state);
	}
	
	/**
	 * does nothing for a closure
	 */
	protected void _computeTypes(XClosure object, ITypeComputationState state) {
		//do nothing
	}
	
	
	protected void _computeTypes(jFaceSpecificLiteral expression,
			ITypeComputationState state) {
		// TODO Auto-generated method stub
		state.acceptActualType(getTypeForName(Object.class, state));
	}
	/**
	 * Overriden to solve writable problem.
	 * Note: if a variable is <i>writable</i> it means it is final!
	 */
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
