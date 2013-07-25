package org.eclipse.recommenders.snipeditor.validation;

import static org.eclipse.xtext.xbase.validation.IssueCodes.ASSIGNMENT_TO_FINAL;
import static org.eclipse.xtext.xbase.validation.IssueCodes.MISSING_INITIALIZATION;
import static org.eclipse.xtext.xbase.validation.IssueCodes.MISSING_TYPE;
import static org.eclipse.xtext.xbase.validation.IssueCodes.NULL_SAFE_FEATURE_CALL_OF_PRIMITIVE_VALUED_FEATURE;
import static org.eclipse.xtext.xbase.validation.IssueCodes.NULL_SAFE_FEATURE_CALL_ON_PRIMITIVE;

import org.eclipse.recommenders.snipeditor.snipDSL.attributeDeclaration;
import org.eclipse.xtext.common.types.JvmConstructor;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;
import org.eclipse.xtext.xbase.XAbstractFeatureCall;
import org.eclipse.xtext.xbase.XAssignment;
import org.eclipse.xtext.xbase.XMemberFeatureCall;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.XbasePackage.Literals;
import org.eclipse.xtext.xbase.jvmmodel.ILogicalContainerProvider;
import org.eclipse.xtext.xbase.validation.XbaseJavaValidator;

import com.google.inject.Inject;

@SuppressWarnings("restriction")
public class SnipDSLValidator extends AbstractSnipDSLValidator {

	@Inject
	private ILogicalContainerProvider logicalContainerProvider;
	
	
	@Check
	@Override
	public void checkVariableDeclaration(XVariableDeclaration declaration) {
		if (declaration.getRight() == null) {
			if (declaration.isWriteable())
				error("Value must be initialized", Literals.XVARIABLE_DECLARATION__WRITEABLE,
						ValidationMessageAcceptor.INSIGNIFICANT_INDEX, MISSING_INITIALIZATION);
		/*	if (declaration.getType() == null)
				error("Type cannot be derived", Literals.XVARIABLE_DECLARATION__NAME,
						ValidationMessageAcceptor.INSIGNIFICANT_INDEX, MISSING_TYPE);*/
		}
	}
	
	@Check
	void checkFullMethodCall(XMemberFeatureCall featureCall) {
		if (!featureCall.isExplicitOperationCall() && !(featureCall.getFeature() instanceof JvmField)){
			error("Please provide full feature call",Literals.XABSTRACT_FEATURE_CALL__FEATURE);
			System.out.println(featureCall.getClass());
		}
		
	}
	@Check
	void checkCorrectMethodCallLocation(XAbstractFeatureCall featureCall) {
		if(featureCall.isExplicitOperationCallOrBuilderSyntax()){
			if(featureCall.eResource().equals(featureCall)){
				System.out.println("found it");
			}
		}
	}
	
	@Check
	@Override
	public void checkAssignment(XAssignment assignment) {
		JvmIdentifiableElement assignmentFeature = assignment.getFeature();
		System.out.println("");
		if (assignmentFeature instanceof XVariableDeclaration
				&& ((XVariableDeclaration) assignmentFeature).isWriteable()){
			error("Assignment to final variable", Literals.XASSIGNMENT__ASSIGNABLE,
					ValidationMessageAcceptor.INSIGNIFICANT_INDEX, ASSIGNMENT_TO_FINAL);
		
		}
		else if (assignmentFeature instanceof JvmFormalParameter)
			error("Assignment to final parameter", Literals.XASSIGNMENT__ASSIGNABLE,
					ValidationMessageAcceptor.INSIGNIFICANT_INDEX, ASSIGNMENT_TO_FINAL);
		else if (assignmentFeature instanceof JvmField && ((JvmField) assignmentFeature).isFinal()) {
			JvmField field = (JvmField) assignmentFeature;
			JvmIdentifiableElement container = logicalContainerProvider.getNearestLogicalContainer(assignment);
			
			// don't issue an error if it's an assignment of a local final field within a constructor.
			if (container != null && container instanceof JvmConstructor) {
				JvmConstructor constructor = (JvmConstructor) container;
				if (field.getDeclaringType() == constructor.getDeclaringType())
					return;
			}
			error("Assignment to final field", Literals.XASSIGNMENT__ASSIGNABLE,
					ValidationMessageAcceptor.INSIGNIFICANT_INDEX, ASSIGNMENT_TO_FINAL);
		}
	}
}
