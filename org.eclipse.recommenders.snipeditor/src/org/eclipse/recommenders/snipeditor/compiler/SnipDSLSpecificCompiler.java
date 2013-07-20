package org.eclipse.recommenders.snipeditor.compiler;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.recommenders.snipeditor.snipDSL.impl.XVariableDeclarationImpl;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.compiler.AbstractXbaseCompiler;
import org.eclipse.xtext.xbase.compiler.IAppendable;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;

@SuppressWarnings("restriction")
public class SnipDSLSpecificCompiler extends XbaseCompiler {
	
	public void _toJavaStatement(XVariableDeclarationImpl expr, ITreeAppendable b, boolean isReferenced) {
		if (expr.getValue() != null) {
			//TODO: internalToJavaStatement(expr.getRight(), b, true);
		}
		final ITreeAppendable trace = b.trace(expr, true).trace(expr, true);
		trace.newLine();
		JvmTypeReference type = appendVariableTypeAndName(expr, trace);
		trace.append(" = ");
		/*if (expr.getValue() != null) {
			internalToConvertedExpression((XExpression) expr.getValue(), b, type);
		} else {*/
			appendDefaultLiteral(trace, type);
		//}
		trace.append(";");
	}
	protected JvmTypeReference appendVariableTypeAndName(XVariableDeclarationImpl varDeclaration, ITreeAppendable b) {
		/*if (!varDeclaration.isWriteable()) {
			appendable.append("final ");
		}*/
		JvmTypeReference type = null;
		if (varDeclaration.getType() != null) {
			type = varDeclaration.getType().getType();
		} 
		serialize(type, varDeclaration, b);
		b.append(" ");
		b.append(b.declareVariable(varDeclaration, makeJavaIdentifier(varDeclaration.getName())));
		return type;
	}

}
