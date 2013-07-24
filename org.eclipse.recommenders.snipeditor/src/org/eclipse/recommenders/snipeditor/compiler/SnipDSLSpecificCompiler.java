package org.eclipse.recommenders.snipeditor.compiler;

import org.eclipse.recommenders.snipeditor.scoping.SnipDSLFeatureCallScope;
import org.eclipse.recommenders.snipeditor.services.SnipDSLGrammarAccess.XVariableDeclarationElements;
import org.eclipse.recommenders.snipeditor.snipDSL.jFaceSpecificLiteral;
import org.eclipse.recommenders.snipeditor.snipDSL.impl.jFaceSpecificLiteralImpl;
import org.eclipse.recommenders.snipeditor.snipDSL.impl.unaryOperationImpl;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XCastedExpression;
import org.eclipse.xtext.xbase.XClosure;
import org.eclipse.xtext.xbase.XConstructorCall;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XIfExpression;
import org.eclipse.xtext.xbase.XInstanceOfExpression;
import org.eclipse.xtext.xbase.XListLiteral;
import org.eclipse.xtext.xbase.XSetLiteral;
import org.eclipse.xtext.xbase.XSwitchExpression;
import org.eclipse.xtext.xbase.XTryCatchFinallyExpression;
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
	protected void doInternalToJavaStatement(XExpression obj, ITreeAppendable appendable, boolean isReferenced) {
		if(obj instanceof unaryOperationImpl){
			this._toJavaStatement((unaryOperationImpl)obj, appendable, isReferenced);
		}else if(obj instanceof jFaceSpecificLiteralImpl){
			this._toJavaStatement((jFaceSpecificLiteralImpl)obj, appendable, isReferenced);
		} else {
			super.doInternalToJavaStatement(obj, appendable, isReferenced);
		}
	}
	@Override
	protected void internalToConvertedExpression(XExpression obj, ITreeAppendable appendable) {
		if (obj instanceof jFaceSpecificLiteralImpl) {
			_toJavaExpression((jFaceSpecificLiteralImpl) obj, appendable);
		} else {
			super.internalToConvertedExpression(obj, appendable);
		}
	}
	protected void _toJavaStatement(unaryOperationImpl obj, ITreeAppendable appendable,boolean isReferenced){
		appendable.append(obj.getResult()+obj.getOperator());
	}
	protected void _toJavaStatement(jFaceSpecificLiteralImpl obj, ITreeAppendable appendable,boolean isReferenced){
		appendable.append(obj.getType().equalsIgnoreCase("dolar") ? "dollar" : "");
	}
	protected void _toJavaExpression(jFaceSpecificLiteralImpl obj, ITreeAppendable appendable){
		appendable.append(obj.getType().equalsIgnoreCase("dolar") ? "dollar" : "");
	}
}