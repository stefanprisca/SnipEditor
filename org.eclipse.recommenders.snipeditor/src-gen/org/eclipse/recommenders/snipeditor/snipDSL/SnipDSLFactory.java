/**
 */
package org.eclipse.recommenders.snipeditor.snipDSL;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage
 * @generated
 */
public interface SnipDSLFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SnipDSLFactory eINSTANCE = org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLFactoryImpl.init();

  /**
   * Returns a new object of class '<em>domainmodel</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>domainmodel</em>'.
   * @generated
   */
  domainmodel createdomainmodel();

  /**
   * Returns a new object of class '<em>import Declare</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>import Declare</em>'.
   * @generated
   */
  importDeclare createimportDeclare();

  /**
   * Returns a new object of class '<em>package Declare</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>package Declare</em>'.
   * @generated
   */
  packageDeclare createpackageDeclare();

  /**
   * Returns a new object of class '<em>entity</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>entity</em>'.
   * @generated
   */
  entity createentity();

  /**
   * Returns a new object of class '<em>parameter</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>parameter</em>'.
   * @generated
   */
  parameter createparameter();

  /**
   * Returns a new object of class '<em>method</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>method</em>'.
   * @generated
   */
  method createmethod();

  /**
   * Returns a new object of class '<em>attribute</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>attribute</em>'.
   * @generated
   */
  attribute createattribute();

  /**
   * Returns a new object of class '<em>my Block Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>my Block Expression</em>'.
   * @generated
   */
  myBlockExpression createmyBlockExpression();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  SnipDSLPackage getSnipDSLPackage();

} //SnipDSLFactory
