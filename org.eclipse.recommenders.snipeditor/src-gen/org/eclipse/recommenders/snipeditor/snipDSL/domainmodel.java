/**
 */
package org.eclipse.recommenders.snipeditor.snipDSL;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>domainmodel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.domainmodel#getImportSection <em>Import Section</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.domainmodel#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getdomainmodel()
 * @model
 * @generated
 */
public interface domainmodel extends EObject
{
  /**
   * Returns the value of the '<em><b>Import Section</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.recommenders.snipeditor.snipDSL.importDeclare}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Import Section</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Import Section</em>' containment reference list.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getdomainmodel_ImportSection()
   * @model containment="true"
   * @generated
   */
  EList<importDeclare> getImportSection();

  /**
   * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.xtext.xbase.XExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elements</em>' containment reference list.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage#getdomainmodel_Elements()
   * @model containment="true"
   * @generated
   */
  EList<XExpression> getElements();

} // domainmodel
