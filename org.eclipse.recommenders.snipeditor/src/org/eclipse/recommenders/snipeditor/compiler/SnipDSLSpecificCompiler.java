package org.eclipse.recommenders.snipeditor.compiler;

import java.util.Iterator;

import org.eclipse.recommenders.snipeditor.scoping.SnipDSLFeatureCallScope;
import org.eclipse.recommenders.snipeditor.snipDSL.AssignmentWithClosure;
import org.eclipse.recommenders.snipeditor.snipDSL.ClassicForLoopExpression;
import org.eclipse.recommenders.snipeditor.snipDSL.FeatureCallWithClosure;
import org.eclipse.recommenders.snipeditor.snipDSL.UnaryOperation;
import org.eclipse.recommenders.snipeditor.snipDSL.blockAssignment;
import org.eclipse.recommenders.snipeditor.snipDSL.impl.jFaceSpecificLiteralImpl;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.XAbstractFeatureCall;
import org.eclipse.xtext.xbase.XClosure;
import org.eclipse.xtext.xbase.XConstructorCall;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XNumberLiteral;
import org.eclipse.xtext.xbase.XStringLiteral;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;

import com.google.inject.Inject;

@SuppressWarnings("restriction")
public class SnipDSLSpecificCompiler extends XbaseCompiler {
	@Inject JvmTypesBuilder types;
	@Inject SnipDSLFeatureCallScope scopper;
	@Override
	protected void internalToConvertedExpression(XExpression obj, ITreeAppendable appendable) {
		if (obj instanceof XConstructorCall) {
			_toJavaExpression((XConstructorCall) obj, appendable);
		} else if (obj instanceof jFaceSpecificLiteralImpl) {
			_toJavaExpression((jFaceSpecificLiteralImpl) obj, appendable);
		} else if(obj instanceof FeatureCallWithClosure){
			this._toJavaExpression((FeatureCallWithClosure)obj, appendable);
		} else if(obj instanceof AssignmentWithClosure){
			this._toJavaExpression((AssignmentWithClosure)obj, appendable);
		} else if (obj instanceof blockAssignment) {
			_toJavaExpression((blockAssignment) obj, appendable);
		} else if (obj instanceof XClosure) {
			_toJavaExpression((XClosure) obj, appendable);
		} else if(obj instanceof UnaryOperation){
			this._toJavaExpression((UnaryOperation)obj, appendable);
		} else{
			super.internalToConvertedExpression(obj, appendable);
		}
	}
	@Override
	protected void doInternalToJavaStatement(XExpression obj, ITreeAppendable appendable, boolean isReferenced) {
		if(obj instanceof jFaceSpecificLiteralImpl){
			this._toJavaStatement((jFaceSpecificLiteralImpl)obj, appendable, isReferenced);
		} else if(obj instanceof ClassicForLoopExpression){
			this._toJavaStatement((ClassicForLoopExpression)obj, appendable, isReferenced);
		} else if(obj instanceof UnaryOperation){
			this._toJavaStatement((UnaryOperation)obj, appendable);
		} else if(obj instanceof FeatureCallWithClosure){
			this._toJavaStatement((FeatureCallWithClosure)obj, appendable, isReferenced);
		} else if (obj instanceof AssignmentWithClosure) {
			this._toJavaStatement((AssignmentWithClosure) obj, appendable);
		} else if (obj instanceof XVariableDeclaration) {
			this._toJavaStatement((XVariableDeclaration) obj, appendable, isReferenced);
		} else {
			super.doInternalToJavaStatement(obj, appendable, isReferenced);
		}
	}
	
	/**
	 * Creates a java translation of a <i>XVariableDeclaration</i>
	 */
	protected void _toJavaStatement(XVariableDeclaration varDeclaration, ITreeAppendable b, boolean isReferenced) {
		b.newLine();
		JvmTypeReference type = this.appendVariableTypeAndName(varDeclaration, b);
		b.append(" = ");
		if (varDeclaration.getRight() != null) {
			this.internalToConvertedExpression(varDeclaration.getRight(), b, type);
		} else {
			appendDefaultLiteral(b, type);
		}
		b.append(";");
	}
	/**
	 * Appends the variable type and name
	 */
	@Override
	protected JvmTypeReference appendVariableTypeAndName(XVariableDeclaration varDeclaration, ITreeAppendable appendable) {
		//set the variable to final if writable is true; 
		//the field name was writable and it was simpler not to change it
		if (varDeclaration.isWriteable()) {
			appendable.append("final ");
		}
		JvmTypeReference type = null;
		if (varDeclaration.getType() != null) {
			type = varDeclaration.getType();
		} else {
			type = types.newTypeRef(varDeclaration, Object.class, null);
		}
		serialize(type, varDeclaration, appendable);
		appendable.append(" ");
		appendable.append(appendable.declareVariable(varDeclaration, makeJavaIdentifier(varDeclaration.getName())));
		return type;
	}
	
	protected void _toJavaStatement(jFaceSpecificLiteralImpl obj, ITreeAppendable appendable,boolean isReferenced){
		appendable.append(obj.getType().equalsIgnoreCase("dolar") ? "dollar" : "");
	}
	
	/**
	 * create a java translation
	 * @param obj
	 * @param appendable
	 */
	protected void _toJavaStatement(AssignmentWithClosure obj, ITreeAppendable appendable){
		appendable.newLine();
		/*String featureName=obj.getFeature().getFeature();
		appendable.append(featureName);
		internalToConvertedExpression(obj.getFeature().getClosure(), appendable);
		appendable.append("=");
		internalToConvertedExpression(obj.getValue(), appendable);
		appendable.append(";");*/
	}
	
	protected void _toJavaStatement(FeatureCallWithClosure obj, ITreeAppendable appendable,boolean isReferenced){
		//appendable.append();
		String featureName=((XAbstractFeatureCall)obj.getFeature()).getConcreteSyntaxFeatureName();
		appendable.append(featureName+"[]");
	}
	
	protected void _toJavaStatement(ClassicForLoopExpression obj, ITreeAppendable appendable,boolean isReferenced){
		appendable.newLine();
		appendable.append("for (" + obj.getDeclaredParam().getName()+" ;");
		internalToConvertedExpression(obj.getCondition(), appendable);
		appendable.append(";");
		internalToConvertedExpression(obj.getForExpression(), appendable);
		appendable.append("){");
		//doInternalToJavaStatement(obj.getEachExpression(), appendable, false);
		appendable.append("}");
		
	}
	protected void _toJavaStatement(UnaryOperation obj, ITreeAppendable appendable){
		appendable.append(obj.getFeature()+obj.getOperand());
	}
	
	
	
	protected void _toJavaExpression(FeatureCallWithClosure obj, ITreeAppendable appendable){
		//appendable.append();
		String featureName=((XAbstractFeatureCall)obj.getFeature()).getConcreteSyntaxFeatureName();
		appendable.append(featureName);
		internalToConvertedExpression(obj.getClosure(), appendable);
		
	}
	protected void _toJavaExpression(AssignmentWithClosure obj, ITreeAppendable appendable){
		//appendable.append();
		/*String featureName=obj.getFeature().getFeature();
		appendable.append(featureName);
		internalToConvertedExpression(obj.getFeature().getClosure(), appendable);
		*/
	}
	
	protected void _toJavaExpression(jFaceSpecificLiteralImpl obj, ITreeAppendable appendable){
		appendable.append(obj.getType().equalsIgnoreCase("dolar") ? "dollar" : "");
	}
	
	protected void _toJavaExpression(UnaryOperation obj, ITreeAppendable appendable){
		appendable.append(obj.getFeature()+obj.getOperand());
	}
	
	
	
	/**
	 * Java translation for blockAssignment;
	 * sorts elements by type and treats them accordingly
	 * @param obj
	 * @param appendable
	 */
	protected void _toJavaExpression(blockAssignment obj, ITreeAppendable appendable){
		appendable.append("{");
		Iterator<XExpression> expr=obj.getValues().iterator();
		while(expr.hasNext()){
			XExpression theExpression=expr.next();
			
			if(theExpression instanceof XStringLiteral){
				appendable.append('"'+((XStringLiteral)theExpression).getValue()+'"');
			}else{
				appendable.append(((XNumberLiteral)theExpression).getValue());
			}
			if(expr.hasNext()){
				appendable.append(", ");
			}
			
		}
		appendable.append("}");
	}
	
	protected void _toJavaExpression(XConstructorCall obj, ITreeAppendable appendable){
		
		//TODO: create java code for constructor
		appendable.append("new " + obj.getConstructor().getIdentifier());
		for(XExpression x:obj.getArguments()){
				internalToConvertedExpression(x, appendable);
			}
			
	}
		

	/**
	 * converts a closure to a java expression
	 * if it has expressions inside, converts them too
	 */
	protected void _toJavaExpression(XClosure obj, ITreeAppendable appendable){
		appendable.append("[");
		if (! obj.getDeclaredFormalParameters().isEmpty()){
			for(JvmFormalParameter parameter : obj.getDeclaredFormalParameters()){
				appendable.append(parameter.getSimpleName()+", ");
			}
		}
		if(obj.getExpression()!=null)
		{
			internalToConvertedExpression(obj.getExpression(), appendable);
		}
		
		appendable.append("]");
	}

	/*protected void _toJavaStatement(XAssignment assign, ITreeAppendable appendable, boolean isReferenced){
		appendable.append("this is an assignment");
		
	}*/
	
}
