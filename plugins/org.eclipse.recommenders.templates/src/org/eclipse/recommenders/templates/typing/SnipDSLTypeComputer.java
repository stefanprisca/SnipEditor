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
package org.eclipse.recommenders.templates.typing;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.eclipse.recommenders.templates.services.SnipDSLGrammarAccess.JFACEEXPRASTYPEElements;
import org.eclipse.recommenders.templates.snipDSL.AssignmentWithClosure;
import org.eclipse.recommenders.templates.snipDSL.ClassicForLoopExpression;
import org.eclipse.recommenders.templates.snipDSL.FeatureCallWithClosure;
import org.eclipse.recommenders.templates.snipDSL.UnaryOperation;
import org.eclipse.recommenders.templates.snipDSL.blockAssignment;
import org.eclipse.recommenders.templates.snipDSL.jFaceCastExpression;
import org.eclipse.recommenders.templates.snipDSL.jFaceConstructorCall;
import org.eclipse.recommenders.templates.snipDSL.jFaceDeclarationType;
import org.eclipse.recommenders.templates.snipDSL.jFaceExpression;
import org.eclipse.recommenders.templates.snipDSL.jFaceSpecificLiteral;
import org.eclipse.recommenders.templates.snipDSL.jFaceVariableDeclaration;
import org.eclipse.recommenders.templates.snipDSL.method;
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
import org.eclipse.xtext.xbase.typesystem.computation.IConstructorLinkingCandidate;
import org.eclipse.xtext.xbase.typesystem.computation.ILinkingCandidate;
import org.eclipse.xtext.xbase.typesystem.computation.ITypeComputationState;
import org.eclipse.xtext.xbase.typesystem.conformance.ConformanceHint;
import org.eclipse.xtext.xbase.typesystem.references.ITypeReferenceOwner;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;

import com.google.inject.Inject;

/**
 * Class used to compute the types for each of the expressions inside the
 * domainmodel
 * 
 * @author Stefan
 * 
 */
@SuppressWarnings("restriction")
public class SnipDSLTypeComputer extends XbaseWithAnnotationsTypeComputer {
    @Inject
    JvmTypesBuilder typeBuilder;

    private static ITypeComputationState compState = null;

    public void computeTypes(XExpression expression, ITypeComputationState state) {

        if (compState != state) {
            compState = state;
        }

        if (expression instanceof blockAssignment) {
            this._computeTypes((blockAssignment) expression, state);
        } else if (expression instanceof UnaryOperation) {
            this._computeTypes((UnaryOperation) expression, state);
        } else if (expression instanceof jFaceConstructorCall) {
            _computeTypes((jFaceConstructorCall) expression, state);
        } else if (expression instanceof jFaceCastExpression) {
            _computeTypes((jFaceCastExpression) expression, state);
        } else if (expression instanceof jFaceExpression) {
            _computeTypes((jFaceExpression) expression, state);
        } else if (expression instanceof method) {
            _computeTypes((method) expression, state);
        } else if (expression instanceof jFaceVariableDeclaration) {
            _computeTypes((jFaceVariableDeclaration) expression, state);
        } else if (expression instanceof XConstructorCall) {
            _computeTypes((XConstructorCall) expression, state);
        } else if (expression instanceof XClosure) {
            _computeTypes((XClosure) expression, state);
        } else if (expression instanceof FeatureCallWithClosure) {
            _computeTypes((FeatureCallWithClosure) expression, state);
        } else if (expression instanceof AssignmentWithClosure) {
            _computeTypes((AssignmentWithClosure) expression, state);
        } else if (expression instanceof ClassicForLoopExpression) {
            _computeTypes((ClassicForLoopExpression) expression, state);
        } else if (expression instanceof jFaceSpecificLiteral) {
            this._computeTypes((jFaceSpecificLiteral) expression, state);
        } else if (expression instanceof jFaceDeclarationType) {
            this._computeTypes((jFaceDeclarationType) expression, state);
        } else /*
                * if (expression instanceof jFaceFullDeclarationType) {
                * this._computeTypes((jFaceFullDeclarationType) expression,
                * state); } else
                */{
            super.computeTypes(expression, state);
        }
    }

    private void _computeTypes(final method expression,
            ITypeComputationState state) {
        // TODO Auto-generated method stub
        // simple type computation, the true work is done via the model
        // inferrer.
        JvmTypeReference type = null;
        if (expression.getJType().getType() != null) {
            type = expression.getJType().getType();

        } else if (expression.getJType().getJFaceType() != null) {
            jFaceDeclarationType jfaceType = expression.getJType()
                    .getJFaceType();
            if (jfaceType.getType().contains("array")) {
                type = typeBuilder.addArrayTypeDimension(typeBuilder
                        .newTypeRef(expression, Object.class, null));

            } else if (jfaceType.getType().contains("interable")) {
                type = typeBuilder.newTypeRef(expression, Iterable.class, null);
            } else if (jfaceType.getType().contains("collection")) {
                type = typeBuilder.newTypeRef(expression, Collection.class,
                        null);

            }

        }
        if (type != null) {
            state.acceptActualType(state.getConverter().toLightweightReference(
                    type));
        }

    }

    private void _computeTypes(UnaryOperation expression,
            ITypeComputationState state) {
        // TODO Auto-generated method stub
        computeTypes(expression.getOperand(), state);
    }

    private void _computeTypes(jFaceCastExpression expression,
            ITypeComputationState state) {
        // TODO Auto-generated method stub
        state.acceptActualType(state.getConverter().toLightweightReference(
                typeBuilder.newTypeRef(expression, Object.class, null)));

    }

    /**
     * Computes the type for a template variable The type is computed based on
     * the variable "name". The method checks what kind of variable it is, and
     * creates a JvmField with the corresponding type and name that will be
     * registered in the type computation state.
     * 
     * @param expression
     *            The template variable
     * @param state
     *            The type computation state
     */
    private void _computeTypes(jFaceExpression expression,
            ITypeComputationState state) {
        // TODO Auto-generated method stub
        if (!expression.getTypes().isEmpty()) {
            // System.out.println("Type is :" + expression.getTypes().get(0));

            JvmField toField = typeBuilder.toField(expression,
                    expression.getValue(), expression.getTypes().get(0));

            state.assignType(toField, state.getConverter()
                    .toLightweightReference(expression.getTypes().get(0)));
            state.acceptActualType(state.getConverter().toLightweightReference(
                    expression.getTypes().get(0)));
        } else if (expression.getValue().contains("array")) {
            JvmTypeReference type = typeBuilder
                    .addArrayTypeDimension(typeBuilder.newTypeRef(expression,
                            Object.class, null));
            JvmField toField = typeBuilder.toField(expression, "${"
                    + expression.getValue() + "}", type);
            state.assignType(toField, state.getConverter()
                    .toLightweightReference(type));
            state.acceptActualType(state.getConverter().toLightweightReference(
                    type));
        } else if (expression.getValue().contains("iterable")) {
            JvmTypeReference type = typeBuilder.newTypeRef(expression,
                    Iterable.class, null);
            JvmField toField = typeBuilder.toField(expression, "${"
                    + expression.getValue() + "}", type);
            state.assignType(toField, state.getConverter()
                    .toLightweightReference(type));
            state.acceptActualType(state.getConverter().toLightweightReference(
                    type));
        } else if (expression.getValue().contains("collection")) {
            JvmTypeReference type = typeBuilder.newTypeRef(expression,
                    Collection.class, null);
            JvmField toField = typeBuilder.toField(expression, "${"
                    + expression.getValue() + "}", type);
            state.assignType(toField, state.getConverter()
                    .toLightweightReference(type));
            state.acceptActualType(state.getConverter().toLightweightReference(
                    type));
        } else if (expression.getValue().contains("index")) {
            JvmTypeReference type = typeBuilder.newTypeRef(expression,
                    int.class, null);
            JvmField toField = typeBuilder.toField(expression, "${"
                    + expression.getValue() + "}", type);
            state.assignType(toField, state.getConverter()
                    .toLightweightReference(type));
            state.acceptActualType(state.getConverter().toLightweightReference(
                    type));
        }
    }

    /**
     * Type computation for jFaceConstructorCall. Computes types based on the
     * type of the template specific type variable
     * 
     * @param expression
     * @param state
     */
    private void _computeTypes(jFaceConstructorCall expression,
            ITypeComputationState state) {
        // TODO Auto-generated method stub
        // System.out.println(expression.getConstructor().getType());
        if (expression.getConstructor() instanceof jFaceDeclarationType) {
            jFaceDeclarationType constructor = (jFaceDeclarationType) expression
                    .getConstructor();
            if (constructor.getType() == "array_type") {
                state.acceptActualType(state.getConverter()
                        .toLightweightReference(
                                typeBuilder.addArrayTypeDimension(typeBuilder
                                        .newTypeRef(expression, Object.class,
                                                (JvmTypeReference[]) null))));
            } else if (constructor.getType().contains("iterable")) {
                state.acceptActualType(state.getConverter()
                        .toLightweightReference(
                                typeBuilder.newTypeRef(expression,
                                        Iterable.class,
                                        (JvmTypeReference[]) null)));
            }
        } else if (expression.getConstructor() instanceof JvmTypeReference) {
            JvmTypeReference reference = (JvmTypeReference) expression
                    .getConstructor();
            state.acceptActualType(state.getConverter().toLightweightReference(
                    reference));
        }
    }

    /**
     * Type computation for template specific type variables Registers the type
     * based on the type of the template type variables
     * 
     * @param expression
     * @param state
     */
    private void _computeTypes(jFaceDeclarationType expression,
            ITypeComputationState state) {
        // TODO Auto-generated method stub
        System.out.println(state.getExpectations().toArray());
        if (expression.getType() == "array_type") {
            // System.out.println(expression.getType());
            LightweightTypeReference type = getTypeForName(Object[].class,
                    state);
            state.acceptActualType(type);
        } else if (expression.getType() == "interable_type") {
            // System.out.println(expression.getType());
            LightweightTypeReference type = getTypeForName(Iterable.class,
                    state);
            state.acceptActualType(type);
        } else {
            LightweightTypeReference type = getTypeForName(Object.class, state);
            state.acceptActualType(type);
        }
    }

    protected void _computeTypes(ClassicForLoopExpression expression,
            ITypeComputationState state) {
        // TODO Auto-generated method stub
        // System.out.println("Got a classic for");
        if (expression.getDeclaredParam().getParameterType() != null)
            state.acceptActualType(state.getConverter().toLightweightReference(
                    expression.getDeclaredParam().getParameterType()));

    }

    protected void _computeTypes(AssignmentWithClosure expression,
            ITypeComputationState state) {
        // TODO Set the correct type!
        System.out.println("Got an assignment call with closure ");

        // super._computeTypes((XAssignment)expression.getValue(), state);

    }

    protected void _computeTypes(FeatureCallWithClosure expression,
            ITypeComputationState state) {
        // TODO Set the correct type!
        // System.out.println("Got a feeature call with closure");
        // state.acceptActualType(getTypeForName(Object.class, state));

    }

    /**
     * Computes the type for a block assignment e.g.:{1,2,3} It loops through
     * the elements in the block and takes their type
     * 
     * @param blkAssign
     *            the feature to compute type for
     * @param state
     *            the computation state
     */
    protected void _computeTypes(blockAssignment blkAssign,
            ITypeComputationState state) {
        // System.out.println("got a block assign type");

        LightweightTypeReference type = getTypeForName(Object.class, state);

        // TODO: set to error if different types in features!-> solved in the
        // validator
        // This only takes the first element in the block, and applies the type
        // of that element,
        // as if there are different types in the block, the validator prompts
        // an error
        XExpression expr = blkAssign.getValues().get(0);
        if (expr instanceof XStringLiteral) {
            type = getTypeForName(String.class, state);
        } else {
            type = getTypeForName(Integer.class, state);
        }
        JvmTypeReference auxType = type.toTypeReference();
        auxType = typeBuilder.addArrayTypeDimension(auxType);
        // System.out.println(auxType.getSimpleName());
        state.acceptActualType(state.getConverter().toLightweightReference(
                auxType));
    }

    /**
     * Compute type for var declaration Sets the name accordingly if it has
     * jface specific structure e.g.: ${freeName(SomeName)} => ${SomeName} Also
     * registers the variable in the current scope with the specific type
     */
    protected void _computeTypes(jFaceVariableDeclaration var,
            ITypeComputationState state) {
        // System.out.println(var);
        if (var == null)
            return;
        if (var.getSimpleName().contains("newName")) {
            // System.out.println("an abstract declaration");
            String computedVarName = var.getSimpleName();
            int beginIndex = computedVarName.indexOf('{');
            int endIndex = computedVarName.indexOf(':');
            String resultName = computedVarName.substring(beginIndex + 1,
                    endIndex);
            // System.out.println(resultName + "  " + var.isWriteable());
            var.setSimpleName("${" + resultName + "}");
        }

        LightweightTypeReference varType = null;

        // System.out.println(var.getJType().getSimpleName());

        if (var.getJType().getType() != null) {
            if (var.getArrayType() != null) {
                var.getJType().setType(
                        typeBuilder.addArrayTypeDimension(var.getJType()
                                .getType()));
            }
            varType = state.getConverter().toLightweightReference(
                    var.getJType().getType());
        } else if (var.getJType().getJFaceType() != null) {
            jFaceDeclarationType jfaceType = var.getJType().getJFaceType();
            if (jfaceType.getType().contains("array")) {
                // System.out.println(expression.getType());
                varType = state.getConverter().toLightweightReference(
                        typeBuilder.addArrayTypeDimension(typeBuilder
                                .newTypeRef(var, Object.class, null)));
            } else if (jfaceType.getType().contains("interable")) {
                varType = state.getConverter().toLightweightReference(
                        typeBuilder.newTypeRef(var, Iterable.class, null));
            } else if (jfaceType.getType().contains("collection")) {
                varType = state.getConverter().toLightweightReference(
                        typeBuilder.newTypeRef(var, Collection.class, null));

            }
        }
        // System.out.println("The jFaceVarType is: "+varType.getSimpleName());
        if (varType != null) {
            state.assignType(var, varType);
        }
        // LightweightTypeReference primitiveVoid = getPrimitiveVoid(state);
        // state.acceptActualType(primitiveVoid);
        // super._computeTypes(var, state);
    }

    /**
     * Computes the type for the constructor Created to include the constructor
     * with [] closure Takes the name of the declared type, converts it in a
     * class and sets the type accordingly. If it encounters primitives, sets
     * the type accordingly
     */

    protected void _computeTypes(XConstructorCall var,
            ITypeComputationState state) {

        if (var.getArguments().size() > 0) {
            if (var.getArguments().get(0) instanceof XClosure) {
                List<? extends IConstructorLinkingCandidate> candidates = state
                        .getLinkingCandidates(var);
                ILinkingCandidate best = getBestCandidate(candidates);
                String typeString = best.getFeature().getQualifiedName();
                try {
                    JvmTypeReference typeAux1 = getTypeForName(
                            Class.forName(typeString), state).toTypeReference();
                    typeAux1 = typeBuilder.addArrayTypeDimension(typeAux1);

                    LightweightTypeReference finalType = state.getConverter()
                            .toLightweightReference(typeAux1);

                    state.acceptActualType(finalType);
                    // state.computeTypes(var.getArguments().get(0));
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    JvmTypeReference typeAux1;
                    if (typeString == "int") {
                        typeAux1 = getTypeForName(int.class, state)
                                .toTypeReference();
                    } else if (typeString == "double") {
                        typeAux1 = getTypeForName(int.class, state)
                                .toTypeReference();
                    } else if (typeString == "float") {
                        typeAux1 = getTypeForName(float.class, state)
                                .toTypeReference();
                    } else if (typeString == "long") {
                        typeAux1 = getTypeForName(long.class, state)
                                .toTypeReference();
                    } else if (typeString == "char") {
                        typeAux1 = getTypeForName(char.class, state)
                                .toTypeReference();
                    } else if (typeString == "byte") {
                        typeAux1 = getTypeForName(byte.class, state)
                                .toTypeReference();
                    } else {
                        typeAux1 = getTypeForName(Object.class, state)
                                .toTypeReference();
                    }

                    typeAux1 = typeBuilder.addArrayTypeDimension(typeAux1);
                    LightweightTypeReference finalType = state.getConverter()
                            .toLightweightReference(typeAux1);
                    state.acceptActualType(finalType);
                    // e.printStackTrace();
                }
            }
        } else
            super._computeTypes(var, state);
    }

    /**
     * does nothing for a closure
     */
    protected void _computeTypes(XClosure object, ITypeComputationState state) {
        // do nothing
    }

    /**
     * Type computation for template literals Most of them should not be cast to
     * any type, but I did not find a way to do that TODO: find a way to simply
     * ignore some variables
     * 
     * @param expression
     * @param state
     */
    protected void _computeTypes(jFaceSpecificLiteral expression,
            ITypeComputationState state) {
        // TODO Auto-generated method stub
        // state.acceptActualType(getTypeForName(Void.TYPE, state));

        if (expression.getType().contains("date")) {
            LightweightTypeReference type = getTypeForName(Date.class, state);
            state.acceptActualType(type);
        } else if (expression.getType().contains("time")) {
            LightweightTypeReference type = getTypeForName(Long.class, state);
            state.acceptActualType(type);
        } else {// TODO: make the type computer accept any type
            LightweightTypeReference type = getTypeForName(boolean.class, state);
            ConformanceHint hints;
            hints = ConformanceHint.SUCCESS;
            state.acceptActualType(type, hints);
        }
        // state.acceptActualType(JvmAnyTypeReference);
    }

    /**
     * Returns the computation state reference owner. This is used in the
     * snipmatch multi page editor metadata page for obtaining the types used in
     * the code snippet.
     * 
     * @return The reference owner of the type computation state.
     */
    public ITypeReferenceOwner getReferenceOwner() {
        if (compState != null)
            return compState.getReferenceOwner();
        else
            return null;
    }

    /**
     * Overriden to solve writable problem. Note: if a variable is
     * <i>writable</i> it means it is final!
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
