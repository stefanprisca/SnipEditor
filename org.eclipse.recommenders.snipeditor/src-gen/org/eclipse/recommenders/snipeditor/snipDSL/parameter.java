/**
 */
package org.eclipse.recommenders.snipeditor.snipDSL;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.parameter#getJType <em>JType</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.parameter#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getparameter()
 * @model
 * @generated
 */
public interface parameter extends EObject
{
  /**
   * Returns the value of the '<em><b>JType</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>JType</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>JType</em>' containment reference.
   * @see #setJType(abstractTypeName)
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getparameter_JType()
   * @model containment="true"
   * @generated
   */
  abstractTypeName getJType();

  /**
   * Sets the value of the '{@link org.eclipse.recommenders.snipeditor.snipDSL.parameter#getJType <em>JType</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>JType</em>' containment reference.
   * @see #getJType()
   * @generated
   */
  void setJType(abstractTypeName value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getparameter_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.recommenders.snipeditor.snipDSL.parameter#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // parameter
