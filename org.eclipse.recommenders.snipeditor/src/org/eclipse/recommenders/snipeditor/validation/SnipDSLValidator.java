package org.eclipse.recommenders.snipeditor.validation;

import static org.eclipse.xtext.xbase.validation.IssueCodes.ASSIGNMENT_TO_FINAL;
import static org.eclipse.xtext.xbase.validation.IssueCodes.MISSING_INITIALIZATION;
import static org.eclipse.xtext.xbase.validation.IssueCodes.MISSING_TYPE;
import static org.eclipse.xtext.xbase.validation.IssueCodes.NULL_SAFE_FEATURE_CALL_OF_PRIMITIVE_VALUED_FEATURE;
import static org.eclipse.xtext.xbase.validation.IssueCodes.NULL_SAFE_FEATURE_CALL_ON_PRIMITIVE;
import static org.eclipse.xtext.xbase.validation.IssueCodes.TOO_LITTLE_TYPE_INFORMATION;

import org.eclipse.recommenders.snipeditor.snipDSL.attributeDeclaration;
import org.eclipse.recommenders.snipeditor.snipDSL.method;
import org.eclipse.xtext.common.types.JvmConstructor;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;
import org.eclipse.xtext.xbase.XAbstractFeatureCall;
import org.eclipse.xtext.xbase.XAssignment;
import org.eclipse.xtext.xbase.XClosure;
import org.eclipse.xtext.xbase.XFeatureCall;
import org.eclipse.xtext.xbase.XMemberFeatureCall;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.XbasePackage.Literals;
import org.eclipse.xtext.xbase.jvmmodel.ILogicalContainerProvider;
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference;
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
	void checkCorrectFeatureCall(XMemberFeatureCall featureCall) {
		if(featureCall.getActualReceiver() instanceof XFeatureCall){
			XFeatureCall caller= (XFeatureCall)featureCall.getActualReceiver();
			if(caller.getFeature() instanceof XVariableDeclaration){
				//System.out.println("it's a variable declaration");
				XVariableDeclaration varCaller=(XVariableDeclaration)caller.getFeature();
				if(varCaller.getRight()==null){
					error("Element has not been instantiated! \n"
							+ "Cannot access a null element!",Literals.XABSTRACT_FEATURE_CALL__FEATURE);
					
				}
			}else if(caller.getFeature() instanceof attributeDeclaration){
				//System.out.println("it's a variable declaration");
				attributeDeclaration varCaller=(attributeDeclaration)caller.getFeature();
				if(varCaller.getValue()==null){
					error("Element has not been instantiated! \n"
							+ "Cannot access a null element!",Literals.XABSTRACT_FEATURE_CALL__FEATURE);
					
				}
			}
			System.out.println(caller.getFeature().eClass());
		}
		//System.out.println(featureCall.getActualReceiver().eClass());
		if (!featureCall.isExplicitOperationCall() && !(featureCall.getFeature() instanceof JvmField)){
			error("Please provide full feature call",Literals.XABSTRACT_FEATURE_CALL__FEATURE);
			//System.out.println(featureCall.getClass());
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
	
	@Check
	public void checkClosureParameterTypes(XClosure closure) {
		if (closure.getDeclaredFormalParameters().isEmpty())
			return;
		LightweightTypeReference closureType = getActualType(closure);
		if (closureType != null && closureType.isUnknown())
			return;
		boolean checkedClosure = false;
		for (JvmFormalParameter p : closure.getFormalParameters()) {
			if (p.getParameterType() == null) {
				if (!checkedClosure) {
					LightweightTypeReference type = getExpectedType(closure);
					if (type == null) {
						error("There is no context to infer the closure's argument types from. Consider typing the arguments or put the closures into a typed context.",
								closure, null, INSIGNIFICANT_INDEX, TOO_LITTLE_TYPE_INFORMATION);
						return;
					} else {
						JvmOperation operation = getServices().getFunctionTypes().findImplementingOperation(type);
						if (operation == null) {
							error("There is no context to infer the closure's argument types from. Consider typing the arguments or use the closures in a more specific context.",
									closure, null, INSIGNIFICANT_INDEX, TOO_LITTLE_TYPE_INFORMATION);
							return;
						}
					}
					checkedClosure = true;
				}
				LightweightTypeReference parameterType = getActualType(closure, p);
				if (parameterType == null) {
					error("There is no context to infer the closure's argument types from. Consider typing the arguments or use the closures in a more specific context.",
							closure, null, INSIGNIFICANT_INDEX, TOO_LITTLE_TYPE_INFORMATION);
					return;
				}
			}
		}
	}
}
