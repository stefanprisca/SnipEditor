/**
 */
package org.eclipse.recommenders.snipeditor.snipDSL.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage;
import org.eclipse.recommenders.snipeditor.snipDSL.abstractTypeName;
import org.eclipse.recommenders.snipeditor.snipDSL.parameter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.parameterImpl#getJType <em>JType</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.parameterImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class parameterImpl extends MinimalEObjectImpl.Container implements parameter
{
  /**
   * The cached value of the '{@link #getJType() <em>JType</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJType()
   * @generated
   * @ordered
   */
  protected abstractTypeName jType;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected parameterImpl()
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
    return SnipDSLPackage.Literals.PARAMETER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public abstractTypeName getJType()
  {
    return jType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetJType(abstractTypeName newJType, NotificationChain msgs)
  {
    abstractTypeName oldJType = jType;
    jType = newJType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SnipDSLPackage.PARAMETER__JTYPE, oldJType, newJType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setJType(abstractTypeName newJType)
  {
    if (newJType != jType)
    {
      NotificationChain msgs = null;
      if (jType != null)
        msgs = ((InternalEObject)jType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SnipDSLPackage.PARAMETER__JTYPE, null, msgs);
      if (newJType != null)
        msgs = ((InternalEObject)newJType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SnipDSLPackage.PARAMETER__JTYPE, null, msgs);
      msgs = basicSetJType(newJType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SnipDSLPackage.PARAMETER__JTYPE, newJType, newJType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SnipDSLPackage.PARAMETER__NAME, oldName, name));
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
      case SnipDSLPackage.PARAMETER__JTYPE:
        return basicSetJType(null, msgs);
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
      case SnipDSLPackage.PARAMETER__JTYPE:
        return getJType();
      case SnipDSLPackage.PARAMETER__NAME:
        return getName();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SnipDSLPackage.PARAMETER__JTYPE:
        setJType((abstractTypeName)newValue);
        return;
      case SnipDSLPackage.PARAMETER__NAME:
        setName((String)newValue);
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
      case SnipDSLPackage.PARAMETER__JTYPE:
        setJType((abstractTypeName)null);
        return;
      case SnipDSLPackage.PARAMETER__NAME:
        setName(NAME_EDEFAULT);
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
      case SnipDSLPackage.PARAMETER__JTYPE:
        return jType != null;
      case SnipDSLPackage.PARAMETER__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //parameterImpl
