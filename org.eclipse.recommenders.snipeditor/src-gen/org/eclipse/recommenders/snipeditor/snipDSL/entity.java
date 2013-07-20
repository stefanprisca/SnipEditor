/**
 */
package org.eclipse.recommenders.snipeditor.snipDSL;

import org.eclipse.emf.common.util.EList;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.entity#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.entity#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.entity#getJType <em>JType</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.entity#getFeatures <em>Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getentity()
 * @model
 * @generated
 */
public interface entity extends XExpression
{
  /**
   * Returns the value of the '<em><b>Visibility</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.recommenders.snipeditor.snipDSL.Visibility}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Visibility</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Visibility</em>' attribute.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.Visibility
   * @see #setVisibility(Visibility)
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getentity_Visibility()
   * @model
   * @generated
   */
  Visibility getVisibility();

  /**
   * Sets the value of the '{@link org.eclipse.recommenders.snipeditor.snipDSL.entity#getVisibility <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Visibility</em>' attribute.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.Visibility
   * @see #getVisibility()
   * @generated
   */
  void setVisibility(Visibility value);

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
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getentity_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.recommenders.snipeditor.snipDSL.entity#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

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
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getentity_JType()
   * @model containment="true"
   * @generated
   */
  abstractTypeName getJType();

  /**
   * Sets the value of the '{@link org.eclipse.recommenders.snipeditor.snipDSL.entity#getJType <em>JType</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>JType</em>' containment reference.
   * @see #getJType()
   * @generated
   */
  void setJType(abstractTypeName value);

  /**
   * Returns the value of the '<em><b>Features</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.recommenders.snipeditor.snipDSL.feature}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Features</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Features</em>' containment reference list.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getentity_Features()
   * @model containment="true"
   * @generated
   */
  EList<feature> getFeatures();

} // entity
