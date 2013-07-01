/**
 */
package org.eclipse.recommenders.snipeditor.snipDSL.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.recommenders.snipeditor.snipDSL.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SnipDSLFactoryImpl extends EFactoryImpl implements SnipDSLFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SnipDSLFactory init()
  {
    try
    {
      SnipDSLFactory theSnipDSLFactory = (SnipDSLFactory)EPackage.Registry.INSTANCE.getEFactory(SnipDSLPackage.eNS_URI);
      if (theSnipDSLFactory != null)
      {
        return theSnipDSLFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new SnipDSLFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SnipDSLFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case SnipDSLPackage.DOMAINMODEL: return createdomainmodel();
      case SnipDSLPackage.IMPORT_DECLARE: return createimportDeclare();
      case SnipDSLPackage.PACKAGE_DECLARE: return createpackageDeclare();
      case SnipDSLPackage.ENTITY: return createentity();
      case SnipDSLPackage.PARAMETER: return createparameter();
      case SnipDSLPackage.METHOD: return createmethod();
      case SnipDSLPackage.ATTRIBUTE: return createattribute();
      case SnipDSLPackage.MY_BLOCK_EXPRESSION: return createmyBlockExpression();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case SnipDSLPackage.VISIBILITY:
        return createVisibilityFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case SnipDSLPackage.VISIBILITY:
        return convertVisibilityToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public domainmodel createdomainmodel()
  {
    domainmodelImpl domainmodel = new domainmodelImpl();
    return domainmodel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public importDeclare createimportDeclare()
  {
    importDeclareImpl importDeclare = new importDeclareImpl();
    return importDeclare;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public packageDeclare createpackageDeclare()
  {
    packageDeclareImpl packageDeclare = new packageDeclareImpl();
    return packageDeclare;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public entity createentity()
  {
    entityImpl entity = new entityImpl();
    return entity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public parameter createparameter()
  {
    parameterImpl parameter = new parameterImpl();
    return parameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public method createmethod()
  {
    methodImpl method = new methodImpl();
    return method;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public attribute createattribute()
  {
    attributeImpl attribute = new attributeImpl();
    return attribute;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public myBlockExpression createmyBlockExpression()
  {
    myBlockExpressionImpl myBlockExpression = new myBlockExpressionImpl();
    return myBlockExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Visibility createVisibilityFromString(EDataType eDataType, String initialValue)
  {
    Visibility result = Visibility.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertVisibilityToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SnipDSLPackage getSnipDSLPackage()
  {
    return (SnipDSLPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static SnipDSLPackage getPackage()
  {
    return SnipDSLPackage.eINSTANCE;
  }

} //SnipDSLFactoryImpl
