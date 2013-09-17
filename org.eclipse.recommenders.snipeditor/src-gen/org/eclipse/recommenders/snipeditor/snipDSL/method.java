/**
 */
package org.eclipse.recommenders.snipeditor.snipDSL;

import org.eclipse.emf.common.util.EList;

import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmTypeReference;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.method#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.method#getJType <em>JType</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.method#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.method#getParams <em>Params</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.method#getThrowExceptions <em>Throw Exceptions</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.method#getBody <em>Body</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getmethod()
 * @model
 * @generated
 */
public interface method extends XExpression
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
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getmethod_Visibility()
   * @model
   * @generated
   */
  Visibility getVisibility();

  /**
   * Sets the value of the '{@link org.eclipse.recommenders.snipeditor.snipDSL.method#getVisibility <em>Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Visibility</em>' attribute.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.Visibility
   * @see #getVisibility()
   * @generated
   */
  void setVisibility(Visibility value);

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
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getmethod_JType()
   * @model containment="true"
   * @generated
   */
  JvmTypeReference getJType();

  /**
   * Sets the value of the '{@link org.eclipse.recommenders.snipeditor.snipDSL.method#getJType <em>JType</em>}' containment reference.
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
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getmethod_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.recommenders.snipeditor.snipDSL.method#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Params</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xtext.common.types.JvmFormalParameter}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Params</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Params</em>' containment reference list.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getmethod_Params()
   * @model containment="true"
   * @generated
   */
  EList<JvmFormalParameter> getParams();

  /**
   * Returns the value of the '<em><b>Throw Exceptions</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Throw Exceptions</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Throw Exceptions</em>' attribute list.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getmethod_ThrowExceptions()
   * @model unique="false"
   * @generated
   */
  EList<String> getThrowExceptions();

  /**
   * Returns the value of the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Body</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Body</em>' containment reference.
   * @see #setBody(XExpression)
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getmethod_Body()
   * @model containment="true"
   * @generated
   */
  XExpression getBody();

  /**
   * Sets the value of the '{@link org.eclipse.recommenders.snipeditor.snipDSL.method#getBody <em>Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Body</em>' containment reference.
   * @see #getBody()
   * @generated
   */
  void setBody(XExpression value);

} // method
