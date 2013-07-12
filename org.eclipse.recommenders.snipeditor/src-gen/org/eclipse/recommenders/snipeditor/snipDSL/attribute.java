/**
 */
package org.eclipse.recommenders.snipeditor.snipDSL;

import org.eclipse.xtext.common.types.JvmTypeReference;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.attribute#getVisible <em>Visible</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.attribute#getJType <em>JType</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.attribute#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.attribute#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getattribute()
 * @model
 * @generated
 */
public interface attribute extends XExpression
{
  /**
   * Returns the value of the '<em><b>Visible</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.recommenders.snipeditor.snipDSL.Visibility}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Visible</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Visible</em>' attribute.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.Visibility
   * @see #setVisible(Visibility)
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getattribute_Visible()
   * @model
   * @generated
   */
  Visibility getVisible();

  /**
   * Sets the value of the '{@link org.eclipse.recommenders.snipeditor.snipDSL.attribute#getVisible <em>Visible</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Visible</em>' attribute.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.Visibility
   * @see #getVisible()
   * @generated
   */
  void setVisible(Visibility value);

  /**
   * Returns the value of the '<em><b>JType</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>JType</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>JType</em>' containment reference.
   * @see #setJType(JvmTypeReference)
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getattribute_JType()
   * @model containment="true"
   * @generated
   */
  JvmTypeReference getJType();

  /**
   * Sets the value of the '{@link org.eclipse.recommenders.snipeditor.snipDSL.attribute#getJType <em>JType</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>JType</em>' containment reference.
   * @see #getJType()
   * @generated
   */
  void setJType(JvmTypeReference value);

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
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getattribute_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.recommenders.snipeditor.snipDSL.attribute#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' containment reference.
   * @see #setValue(XExpression)
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getattribute_Value()
   * @model containment="true"
   * @generated
   */
  XExpression getValue();

  /**
   * Sets the value of the '{@link org.eclipse.recommenders.snipeditor.snipDSL.attribute#getValue <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */
  void setValue(XExpression value);

} // attribute
