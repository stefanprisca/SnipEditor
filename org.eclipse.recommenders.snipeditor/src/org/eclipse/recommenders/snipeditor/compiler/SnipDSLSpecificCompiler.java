package org.eclipse.recommenders.snipeditor.compiler;

import java.util.List;

import org.eclipse.recommenders.snipeditor.scoping.SnipDSLFeatureCallScope;
import org.eclipse.recommenders.snipeditor.snipDSL.blockAssignment;
import org.eclipse.recommenders.snipeditor.snipDSL.impl.jFaceSpecificLiteralImpl;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.XClosure;
import org.eclipse.xtext.xbase.XConstructorCall;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.typesystem.computation.IConstructorLinkingCandidate;
import org.eclipse.xtext.xbase.typesystem.computation.ILinkingCandidate;

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
		} else if (obj instanceof blockAssignment) {
			_toJavaExpression((blockAssignment) obj, appendable);
		} else if (obj instanceof XClosure) {
			_toJavaExpression((XClosure) obj, appendable);
		} else {
			super.internalToConvertedExpression(obj, appendable);
		}
	}
	@Override
	protected void doInternalToJavaStatement(XExpression obj, ITreeAppendable appendable, boolean isReferenced) {
		if(obj instanceof jFaceSpecificLiteralImpl){
			this._toJavaStatement((jFaceSpecificLiteralImpl)obj, appendable, isReferenced);
		} else if (obj instanceof XVariableDeclaration) {
			this._toJavaStatement((XVariableDeclaration) obj, appendable, isReferenced);
		} else {
			super.doInternalToJavaStatement(obj, appendable, isReferenced);
		}
	}
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
	@Override
	protected JvmTypeReference appendVariableTypeAndName(XVariableDeclaration varDeclaration, ITreeAppendable appendable) {
		//set the variable to final if writable is true; no logic, but the field name is writable
		if (varDeclaration.isWriteable()) {
			appendable.append("final ");
		}
		JvmTypeReference type = null;
		if (varDeclaration.getType() != null) {
			type = varDeclaration.getType();
		} else {
			type = getType(varDeclaration.getRight());
		}
		serialize(type, varDeclaration, appendable);
		appendable.append(" ");
		appendable.append(appendable.declareVariable(varDeclaration, makeJavaIdentifier(varDeclaration.getName())));
		return type;
	}
	
	protected void _toJavaStatement(jFaceSpecificLiteralImpl obj, ITreeAppendable appendable,boolean isReferenced){
		appendable.append(obj.getType().equalsIgnoreCase("dolar") ? "dollar" : "");
	}
	protected void _toJavaExpression(jFaceSpecificLiteralImpl obj, ITreeAppendable appendable){
		appendable.append(obj.getType().equalsIgnoreCase("dolar") ? "dollar" : "");
	}
	
	protected void _toJavaExpression(blockAssignment obj, ITreeAppendable appendable){
		for(XExpression expr: obj.getValues()){
			appendable.append(expr.toString());
		}
	}
	
	protected void _toJavaExpression(XConstructorCall obj, ITreeAppendable appendable){
		
		//TODO: create java code for constructor
		appendable.append("new " + obj.getConstructor().getIdentifier());
		for(XExpression x:obj.getArguments()){
				internalToConvertedExpression(x, appendable);
			}
			
	}
		

	
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

	
	
}
