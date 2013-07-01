/**
 */
package org.eclipse.recommenders.snipeditor.snipDSL.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage;
import org.eclipse.recommenders.snipeditor.snipDSL.domainmodel;
import org.eclipse.recommenders.snipeditor.snipDSL.importDeclare;

import org.eclipse.xtext.xbase.XExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>domainmodel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.domainmodelImpl#getImportSection <em>Import Section</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.domainmodelImpl#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class domainmodelImpl extends MinimalEObjectImpl.Container implements domainmodel
{
  /**
   * The cached value of the '{@link #getImportSection() <em>Import Section</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImportSection()
   * @generated
   * @ordered
   */
  protected EList<importDeclare> importSection;

  /**
   * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElements()
   * @generated
   * @ordered
   */
  protected EList<XExpression> elements;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected domainmodelImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return SnipDSLPackage.Literals.DOMAINMODEL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<importDeclare> getImportSection()
  {
    if (importSection == null)
    {
      importSection = new EObjectContainmentEList<importDeclare>(importDeclare.class, this, SnipDSLPackage.DOMAINMODEL__IMPORT_SECTION);
    }
    return importSection;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XExpression> getElements()
  {
    if (elements == null)
    {
      elements = new EObjectContainmentEList<XExpression>(XExpression.class, this, SnipDSLPackage.DOMAINMODEL__ELEMENTS);
    }
    return elements;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case SnipDSLPackage.DOMAINMODEL__IMPORT_SECTION:
        return ((InternalEList<?>)getImportSection()).basicRemove(otherEnd, msgs);
      case SnipDSLPackage.DOMAINMODEL__ELEMENTS:
        return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case SnipDSLPackage.DOMAINMODEL__IMPORT_SECTION:
        return getImportSection();
      case SnipDSLPackage.DOMAINMODEL__ELEMENTS:
        return getElements();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SnipDSLPackage.DOMAINMODEL__IMPORT_SECTION:
        getImportSection().clear();
        getImportSection().addAll((Collection<? extends importDeclare>)newValue);
        return;
      case SnipDSLPackage.DOMAINMODEL__ELEMENTS:
        getElements().clear();
        getElements().addAll((Collection<? extends XExpression>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case SnipDSLPackage.DOMAINMODEL__IMPORT_SECTION:
        getImportSection().clear();
        return;
      case SnipDSLPackage.DOMAINMODEL__ELEMENTS:
        getElements().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case SnipDSLPackage.DOMAINMODEL__IMPORT_SECTION:
        return importSection != null && !importSection.isEmpty();
      case SnipDSLPackage.DOMAINMODEL__ELEMENTS:
        return elements != null && !elements.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //domainmodelImpl
