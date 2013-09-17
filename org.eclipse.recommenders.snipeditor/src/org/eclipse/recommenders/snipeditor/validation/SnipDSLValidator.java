package org.eclipse.recommenders.snipeditor.validation;

import static org.eclipse.xtext.xbase.validation.IssueCodes.ASSIGNMENT_TO_FINAL;
import static org.eclipse.xtext.xbase.validation.IssueCodes.MISSING_INITIALIZATION;

import org.eclipse.recommenders.snipeditor.snipDSL.attributeDeclaration;
import org.eclipse.recommenders.snipeditor.snipDSL.blockAssignment;
import org.eclipse.xtext.common.types.JvmConstructor;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;
import org.eclipse.xtext.xbase.XAssignment;
import org.eclipse.xtext.xbase.XClosure;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XFeatureCall;
import org.eclipse.xtext.xbase.XMemberFeatureCall;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.XbasePackage.Literals;
import org.eclipse.xtext.xbase.jvmmodel.ILogicalContainerProvider;

import com.google.inject.Inject;

@SuppressWarnings("restriction")
public class SnipDSLValidator extends AbstractSnipDSLValidator {

    @Inject
    private ILogicalContainerProvider logicalContainerProvider;

    /**
     * overridden to accommodate the <i>writable</i> field
     */
    @Check
    @Override
    public void checkVariableDeclaration(XVariableDeclaration declaration) {
        if (declaration.getRight() == null) {
            if (declaration.isWriteable())
                error("Value must be initialized",
                        Literals.XVARIABLE_DECLARATION__WRITEABLE,
                        ValidationMessageAcceptor.INSIGNIFICANT_INDEX,
                        MISSING_INITIALIZATION);
            /*
             * if (declaration.getType() == null)
             * error("Type cannot be derived",
             * Literals.XVARIABLE_DECLARATION__NAME,
             * ValidationMessageAcceptor.INSIGNIFICANT_INDEX, MISSING_TYPE);
             */
        }
    }

    /**
     * Check for correct feature calls.
     * <p>
     * <li>if caller is not instantiated and a member feature is called, prompt
     * error</li>
     * 
     * <li>if a call to a method is made without explicit feature call <i>()</i>
     * prompt error</li>
     * </p>
     * 
     * @param featureCall
     */
    @Check
    void checkCorrectFeatureCall(XMemberFeatureCall featureCall) {
        if (featureCall.getActualReceiver() instanceof XFeatureCall) {
            XFeatureCall caller = (XFeatureCall) featureCall
                    .getActualReceiver();
            if (caller.getFeature() instanceof XVariableDeclaration) {
                // System.out.println("it's a variable declaration");
                XVariableDeclaration varCaller = (XVariableDeclaration) caller
                        .getFeature();
                if (varCaller.getRight() == null) {
                    error("Element has not been instantiated! \n"
                            + "Cannot access a null element!",
                            Literals.XABSTRACT_FEATURE_CALL__FEATURE);

                }
            } else if (caller.getFeature() instanceof attributeDeclaration) {
                // System.out.println("it's a variable declaration");
                attributeDeclaration varCaller = (attributeDeclaration) caller
                        .getFeature();
                if (varCaller.getValue() == null) {
                    error("Element has not been instantiated! \n"
                            + "Cannot access a null element!",
                            Literals.XABSTRACT_FEATURE_CALL__FEATURE);

                }
            }
            // System.out.println(caller.getFeature().eClass());
        }
        // System.out.println(featureCall.getActualReceiver().eClass());
        if (!featureCall.isExplicitOperationCall()
                && (featureCall.getFeature() instanceof JvmOperation)) {
            error("Please provide full feature call",
                    Literals.XABSTRACT_FEATURE_CALL__FEATURE);
            System.out.println(featureCall.getMemberCallTarget());
        }

    }

    /**
     * Check if assignment if made to final variable Overridden to accommodate
     * <i>writable</i> field
     */
    @Check
    @Override
    public void checkAssignment(XAssignment assignment) {
        JvmIdentifiableElement assignmentFeature = assignment.getFeature();
        if (assignmentFeature instanceof XVariableDeclaration
                && ((XVariableDeclaration) assignmentFeature).isWriteable()) {
            error("Assignment to final variable",
                    Literals.XASSIGNMENT__ASSIGNABLE,
                    ValidationMessageAcceptor.INSIGNIFICANT_INDEX,
                    ASSIGNMENT_TO_FINAL);

        } else if (assignmentFeature instanceof JvmFormalParameter)
            error("Assignment to final parameter",
                    Literals.XASSIGNMENT__ASSIGNABLE,
                    ValidationMessageAcceptor.INSIGNIFICANT_INDEX,
                    ASSIGNMENT_TO_FINAL);
        else if (assignmentFeature instanceof JvmField
                && ((JvmField) assignmentFeature).isFinal()) {
            JvmField field = (JvmField) assignmentFeature;
            JvmIdentifiableElement container = logicalContainerProvider
                    .getNearestLogicalContainer(assignment);

            // don't issue an error if it's an assignment of a local final field
            // within a constructor.
            if (container != null && container instanceof JvmConstructor) {
                JvmConstructor constructor = (JvmConstructor) container;
                if (field.getDeclaringType() == constructor.getDeclaringType())
                    return;
            }
            error("Assignment to final field",
                    Literals.XASSIGNMENT__ASSIGNABLE,
                    ValidationMessageAcceptor.INSIGNIFICANT_INDEX,
                    ASSIGNMENT_TO_FINAL);
        }
    }

    /**
     * Override to eliminate checking for side effects Not sure if it is good
     * TODO: take a deeper look into the effects
     */
    @Override
    @Check
    public void checkInnerExpressions(XExpression expr) {

    }

    /**
     * Override to check for <b>DeclaredFormalParameters</b> The super checks
     * for other parameter type and promts error TODO: get a better
     * understanding of the mechanics
     */
    @Override
    @Check
    public void checkClosureParameterTypes(XClosure closure) {
        if (closure.getDeclaredFormalParameters().isEmpty())
            return;
        super.checkClosureParameterTypes(closure);
    }

    /**
     * check if there are different types in a block assignment
     * 
     * @param blkAssign
     */
    @Check
    public void checkValidBlockAssignment(blockAssignment blkAssign) {
        for (int i = 1; i < blkAssign.getValues().size(); i++) {
            if (!blkAssign.getValues().get(i).getClass()
                    .equals(blkAssign.getValues().get(i - 1).getClass())) {
                error("Different types in block!", blkAssign.getValues().get(i)
                        .eContainingFeature());
            }
        }
    }

}
