/*******************************************************************************
 * Copyright (c) 2013 Stefan Prisca.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Stefan Prisca - initial API and implementation
 ******************************************************************************/
package org.eclipse.recommenders.templates.compiler;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.recommenders.templates.snipDSL.AssignmentWithClosure;
import org.eclipse.recommenders.templates.snipDSL.ClassicForLoopExpression;
import org.eclipse.recommenders.templates.snipDSL.FeatureCallWithClosure;
import org.eclipse.recommenders.templates.snipDSL.JvmFormalParameter;
import org.eclipse.recommenders.templates.snipDSL.UnaryOperation;
import org.eclipse.recommenders.templates.snipDSL.blockAssignment;
import org.eclipse.recommenders.templates.snipDSL.jFaceCastExpression;
import org.eclipse.recommenders.templates.snipDSL.jFaceConstructorCall;
import org.eclipse.recommenders.templates.snipDSL.jFaceExpression;
import org.eclipse.recommenders.templates.snipDSL.jFaceVariableDeclaration;
import org.eclipse.recommenders.templates.snipDSL.method;
import org.eclipse.recommenders.templates.snipDSL.impl.jFaceSpecificLiteralImpl;
import org.eclipse.xtext.common.types.JvmArrayType;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.xbase.XAbstractFeatureCall;
import org.eclipse.xtext.xbase.XClosure;
import org.eclipse.xtext.xbase.XConstructorCall;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XForLoopExpression;
import org.eclipse.xtext.xbase.XIfExpression;
import org.eclipse.xtext.xbase.XNumberLiteral;
import org.eclipse.xtext.xbase.XStringLiteral;
import org.eclipse.xtext.xbase.XTryCatchFinallyExpression;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.compiler.Later;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.scoping.featurecalls.OperatorMapping;

import com.google.inject.Inject;

/**
 * Class used to compile the template syntax into java code. Does not work
 * properly yet. -> This is a big TODO
 * 
 * @author Stefan
 * 
 */

@SuppressWarnings("restriction")
public class SnipDSLSpecificCompiler extends XbaseCompiler {
    @Inject
    JvmTypesBuilder types;

    @Override
    protected void internalToConvertedExpression(XExpression obj,
            ITreeAppendable appendable) {
        if (obj instanceof jFaceExpression) {
            _toJavaExpression((jFaceExpression) obj, appendable);
        } else if (obj instanceof jFaceCastExpression) {
            _toJavaExpression((jFaceCastExpression) obj, appendable);
        } else if (obj instanceof jFaceConstructorCall) {
            _toJavaExpression((jFaceConstructorCall) obj, appendable);
        } else if (obj instanceof jFaceSpecificLiteralImpl) {
            _toJavaExpression((jFaceSpecificLiteralImpl) obj, appendable);
        } else if (obj instanceof FeatureCallWithClosure) {
            this._toJavaExpression((FeatureCallWithClosure) obj, appendable);
        } else if (obj instanceof AssignmentWithClosure) {
            this._toJavaExpression((AssignmentWithClosure) obj, appendable);
        } else if (obj instanceof XConstructorCall) {
            this._toJavaExpression((XConstructorCall) obj, appendable);
        } else if (obj instanceof blockAssignment) {
            _toJavaExpression((blockAssignment) obj, appendable);
        } else if (obj instanceof XClosure) {
            _toJavaExpression((XClosure) obj, appendable);
        } else if (obj instanceof method) {
            this._toJavaExpression((method) obj, appendable);
        } else if (obj instanceof UnaryOperation) {
            this._toJavaExpression((UnaryOperation) obj, appendable);
        } else {
            super.internalToConvertedExpression(obj, appendable);
        }
    }

    @Override
    protected void doInternalToJavaStatement(XExpression obj,
            ITreeAppendable appendable, boolean isReferenced) {
        if (obj instanceof jFaceExpression) {
            this._toJavaStatement((jFaceExpression) obj, appendable,
                    isReferenced);
        } else if (obj instanceof jFaceCastExpression) {
            _toJavaExpression((jFaceCastExpression) obj, appendable);
        } else if (obj instanceof jFaceSpecificLiteralImpl) {
            this._toJavaStatement((jFaceSpecificLiteralImpl) obj, appendable,
                    isReferenced);
        } else if (obj instanceof ClassicForLoopExpression) {
            this._toJavaStatement((ClassicForLoopExpression) obj, appendable,
                    isReferenced);
        } else if (obj instanceof UnaryOperation) {
            this._toJavaStatement((UnaryOperation) obj, appendable);
        } else if (obj instanceof FeatureCallWithClosure) {
            this._toJavaStatement((FeatureCallWithClosure) obj, appendable,
                    isReferenced);
        } else if (obj instanceof XIfExpression) {
            this._toJavaStatement((XIfExpression) obj, appendable, isReferenced);
        } else if (obj instanceof AssignmentWithClosure) {
            this._toJavaStatement((AssignmentWithClosure) obj, appendable);
        } else if (obj instanceof XVariableDeclaration) {
            this._toJavaStatement((XVariableDeclaration) obj, appendable,
                    isReferenced);
        } else if (obj instanceof XConstructorCall) {
            this._toJavaStatement((XConstructorCall) obj, appendable,
                    isReferenced);
        } else if (obj instanceof jFaceConstructorCall) {
            this._toJavaStatement((jFaceConstructorCall) obj, appendable,
                    isReferenced);
        } else if (obj instanceof method) {
            this._toJavaStatement((method) obj, appendable, isReferenced);
        } else {
            // System.out.println("passed to super: "+obj+" "+obj.eClass());
            super.doInternalToJavaStatement(obj, appendable, isReferenced);
        }
    }

    protected void _toJavaStatement(method m, ITreeAppendable appendable,
            boolean isReferenced) {
        // The translation is done by the model inferer. Added this here to be
        // able to add methods anywhere in the domainmodel.
        // -> treat methods as an expression inside the domainmodel body
    }

    /**
     * Creates a java translation of a <i>XVariableDeclaration</i>
     */
    protected void _toJavaStatement(XVariableDeclaration varDeclaration,
            ITreeAppendable b, boolean isReferenced) {
        b.newLine();
        JvmTypeReference type = this.appendVariableTypeAndName(varDeclaration,
                b);
        b.append(" = ");
        // TODO:
        // if (varDeclaration.getRight() != null) {
        // this.internalToConvertedExpression(varDeclaration.getRight(), b,
        // type);
        // } else {
        appendDefaultLiteral(b, type);
        // }
        b.append(";");
    }

    /**
     * Appends the variable type and name
     */
    @Override
    protected JvmTypeReference appendVariableTypeAndName(
            XVariableDeclaration varDeclaration, ITreeAppendable appendable) {
        // set the variable to final if writable is true;
        // the field name was writable and it was simpler not to change it
        if (varDeclaration.isWriteable()) {
            appendable.append("final ");
        }
        JvmTypeReference type = null;
        if (varDeclaration.getType() != null) {
            type = varDeclaration.getType();
        } else {
            type = types.newTypeRef(varDeclaration, Object[].class, null);
        }
        serialize(type, varDeclaration, appendable);
        appendable.append(" ");
        appendable.append(appendable.declareVariable(varDeclaration,
                makeJavaIdentifier(varDeclaration.getSimpleName())));
        return type;
    }

    protected void _toJavaStatement(jFaceSpecificLiteralImpl obj,
            ITreeAppendable appendable, boolean isReferenced) {
        // appendable.append(obj.getType().equalsIgnoreCase("dolar") ? "dollar"
        // : "");
    }

    protected void _toJavaStatement(jFaceExpression obj,
            ITreeAppendable appendable, boolean isReferenced) {
        appendable.newLine();
        /*
         * serialize(types.addArrayTypeDimension(types.newTypeRef(obj,
         * Object.class, null)), obj, appendable);
         */
        appendable.append(" ${"
                + appendable.declareVariable(obj, obj.getValue()) + "}" + ";");
    }

    /**
     * create a java translation
     * 
     * @param obj
     * @param appendable
     */
    protected void _toJavaStatement(AssignmentWithClosure obj,
            ITreeAppendable appendable) {
        appendable.newLine();
        /*
         * String featureName=obj.getFeature().getFeature();
         * appendable.append(featureName);
         * internalToConvertedExpression(obj.getFeature().getClosure(),
         * appendable); appendable.append("=");
         * internalToConvertedExpression(obj.getValue(), appendable);
         * appendable.append(";");
         */
    }

    protected void _toJavaStatement(FeatureCallWithClosure obj,
            ITreeAppendable appendable, boolean isReferenced) {
        // appendable.append();
        String featureName = ((XAbstractFeatureCall) obj.getFeature())
                .getConcreteSyntaxFeatureName();
        appendable.append(featureName + "[]");
    }

    protected void _toJavaStatement(XIfExpression obj,
            ITreeAppendable appendable, boolean isReferenced) {
        // appendable.append();
        if (obj.getIf() != null) {
            super._toJavaStatement(obj, appendable, isReferenced);

        } else {// TODO: complete the statement
            appendable.newLine().append("else {}");
        }
    }

    protected void _toJavaStatement(XTryCatchFinallyExpression obj,
            ITreeAppendable appendable, boolean isReferenced) {
        // appendable.append();
        if (obj.getExpression() != null) {
            super._toJavaStatement(obj, appendable, isReferenced);

        } else {// TODO: complete the statement
            appendable.newLine().append("catch {}");
        }
    }

    protected void _toJavaStatement(XConstructorCall obj,
            ITreeAppendable appendable, boolean isReferenced) {
        // appendable.append();
        appendable.append("new " + obj.getConstructor().getIdentifier());
        for (XExpression x : obj.getArguments()) {
            // System.out.println(x);
            if (!(x instanceof method || x instanceof jFaceVariableDeclaration))
                internalToConvertedExpression(x, appendable);
        }
        // super._toJavaStatement(obj, appendable, isReferenced);

    }

    protected void _toJavaStatement(jFaceConstructorCall obj,
            ITreeAppendable appendable, boolean isReferenced) {
        // appendable.append();
        appendable.append("new " + obj.getConstructor());
        for (XExpression x : obj.getArguments()) {
            // System.out.println(x);
            if (!(x instanceof method || x instanceof jFaceVariableDeclaration))
                internalToConvertedExpression(x, appendable);
        }
        // super._toJavaStatement(obj, appendable, isReferenced);

    }

    protected void _toJavaStatement(ClassicForLoopExpression obj,
            ITreeAppendable appendable, boolean isReferenced) {
        appendable.newLine();
        appendable.append("for (" + obj.getDeclaredParam().getName() + " ;");
        if(obj.getCondition()!=null){
              	internalToConvertedExpression(obj.getCondition(), appendable);
        }
        appendable.append(";");
        if(obj.getForExpression()!=null)
        {
        	internalToConvertedExpression(obj.getForExpression(), appendable);
        }
        appendable.append("){");
        // doInternalToJavaStatement(obj.getEachExpression(), appendable,
        // false);
        appendable.append("}");

    }

    protected void _toJavaStatement(UnaryOperation obj,
            ITreeAppendable appendable) {
        appendable.append(obj.getFeature() + obj.getOperand());
    }

    /*
     * protected void _toJavaStatement(XAbstractFeatureCall obj, ITreeAppendable
     * appendable, boolean isReferenced) {
     * 
     * System.out.println("OH SNAP!"); if(obj.toString().contains("${")){
     * appendable.append(obj.toString()); } else{
     * super.doInternalToJavaStatement(obj, appendable, isReferenced); }
     * 
     * }
     */
    protected void _toJavaExpression(FeatureCallWithClosure obj,
            ITreeAppendable appendable) {
        // appendable.append();
        String featureName = ((XAbstractFeatureCall) obj.getFeature())
                .getConcreteSyntaxFeatureName();
        appendable.append(featureName);
        internalToConvertedExpression(obj.getClosure(), appendable);

    }

    protected void _toJavaExpression(AssignmentWithClosure obj,
            ITreeAppendable appendable) {
        // appendable.append();
        /*
         * String featureName=obj.getFeature().getFeature();
         * appendable.append(featureName);
         * internalToConvertedExpression(obj.getFeature().getClosure(),
         * appendable);
         */
    }

    protected void _toJavaExpression(method m, ITreeAppendable appendable) {
        // The translation is done by the model inferer. Added this here to be
        // able to add methods anywhere in the domainmodel.
        // -> treat methods as an expression inside the domainmodel body
    }

    protected void _toJavaExpression(jFaceSpecificLiteralImpl obj,
            ITreeAppendable appendable) {
        // appendable.append(obj.getType().equalsIgnoreCase("dolar") ? "dollar"
        // : "");
    }

    protected void _toJavaExpression(jFaceExpression obj,
            ITreeAppendable appendable) {
        appendable.append("${" + obj.getValue() + "};");
    }

    protected void _toJavaExpression(UnaryOperation obj,
            ITreeAppendable appendable) {
        appendable.append(obj.getFeature() + obj.getOperand());
    }

    /*
     * protected void _toJavaExpression(XAbstractFeatureCall obj,
     * ITreeAppendable appendable) { if(obj.toString().contains("${"))
     * appendable.append(obj.toString()); else super._toJavaExpression(obj,
     * appendable);
     * 
     * }
     */
    /**
     * Java translation for blockAssignment; sorts elements by type and treats
     * them accordingly
     * 
     * @param obj
     * @param appendable
     */
    protected void _toJavaExpression(blockAssignment obj,
            ITreeAppendable appendable) {
        appendable.append("{");
        Iterator<XExpression> expr = obj.getValues().iterator();
        while (expr.hasNext()) {
            XExpression theExpression = expr.next();

            if (theExpression instanceof XStringLiteral) {
                appendable.append('"' + ((XStringLiteral) theExpression)
                        .getValue() + '"');
            } else {
                appendable.append(((XNumberLiteral) theExpression).getValue());
            }
            if (expr.hasNext()) {
                appendable.append(", ");
            }

        }
        appendable.append("}");
    }

    protected void _toJavaExpression(XConstructorCall obj,
            ITreeAppendable appendable) {

        // TODO: create java code for constructor
        appendable.append("new " + obj.getConstructor().getIdentifier());
        for (XExpression x : obj.getArguments()) {
            // System.out.println(x);
            internalToConvertedExpression(x, appendable);
        }

    }

    protected void _toJavaExpression(jFaceConstructorCall obj,
            ITreeAppendable appendable) {

        // TODO: create java code for constructor
        appendable.append("new " + obj.getConstructor() + "()");

    }

    /**
     * converts a closure to a java expression if it has expressions inside,
     * converts them too
     */
    protected void _toJavaExpression(XClosure obj, ITreeAppendable appendable) {
        appendable.append("[");
        /*
         * if (!obj.getDeclaredFormalParameters().isEmpty()) { for
         * (JvmFormalParameter parameter : obj .getDeclaredFormalParameters()) {
         * appendable.append(parameter.getSimpleName() + ", "); } }
         */
        if (obj.getExpression() != null) {
            internalToConvertedExpression(obj.getExpression(), appendable);
        }

        appendable.append("]");
    }

    protected void _toJavaExpression(jFaceCastExpression obj,
            ITreeAppendable appendable) {
        appendable.append(obj.getType().getType());
    }

    @Override
    /**
     * Overriden to append parameter simple name insead of name.
     * Changed name to simple name in order to bypass the exception thrown by JvmIdentElement
     * at the getSimpleName() method
     */
    protected void appendForLoopParameter(XForLoopExpression expr,
            ITreeAppendable appendable) {
        JvmTypeReference paramType = getForLoopParameterType(expr);
        serialize(paramType, expr, appendable);
        appendable.append(" ");
        if (expr.getDeclaredParam().getSimpleName() != null) {
            final String name = makeJavaIdentifier(expr.getDeclaredParam()
                    .getSimpleName());
            String varName = appendable.declareVariable(
                    expr.getDeclaredParam(), name);
            appendable.trace(expr.getDeclaredParam(),
                    TypesPackage.Literals.JVM_FORMAL_PARAMETER__NAME, 0)
                    .append(varName);
        } else if (((JvmFormalParameter) expr.getDeclaredParam())
                .getJFaceExpr() != null) {
            final String name = makeJavaIdentifier("${"
                    + ((jFaceExpression) ((JvmFormalParameter) expr
                            .getDeclaredParam()).getJFaceExpr()).getValue()
                    + "}");
            String varName = appendable.declareVariable(
                    expr.getDeclaredParam(), name);
            appendable.trace(expr.getDeclaredParam(),
                    TypesPackage.Literals.JVM_FORMAL_PARAMETER__NAME, 0)
                    .append(varName);
        }
    }

}
