/**
 */
package org.eclipse.recommenders.snipeditor.snipDSL.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLPackage;
import org.eclipse.recommenders.snipeditor.snipDSL.Visibility;
import org.eclipse.recommenders.snipeditor.snipDSL.attribute;

import org.eclipse.xtext.common.types.JvmTypeReference;

import org.eclipse.xtext.xbase.XExpression;

import org.eclipse.xtext.xbase.impl.XExpressionImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.attributeImpl#getVisible <em>Visible</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.attributeImpl#getJType <em>JType</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.attributeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.attributeImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class attributeImpl extends XExpressionImpl implements attribute
{
  /**
   * The default value of the '{@link #getVisible() <em>Visible</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVisible()
   * @generated
   * @ordered
   */
  protected static final Visibility VISIBLE_EDEFAULT = Visibility.PROTECTED;

  /**
   * The cached value of the '{@link #getVisible() <em>Visible</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVisible()
   * @generated
   * @ordered
   */
  protected Visibility visible = VISIBLE_EDEFAULT;

  /**
   * The cached value of the '{@link #getJType() <em>JType</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJType()
   * @generated
   * @ordered
   */
  protected JvmTypeReference jType;

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
   * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected XExpression value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected attributeImpl()
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
    return SnipDSLPackage.Literals.ATTRIBUTE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Visibility getVisible()
  {
    return visible;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVisible(Visibility newVisible)
  {
    Visibility oldVisible = visible;
    visible = newVisible == null ? VISIBLE_EDEFAULT : newVisible;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SnipDSLPackage.ATTRIBUTE__VISIBLE, oldVisible, visible));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JvmTypeReference getJType()
  {
    return jType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetJType(JvmTypeReference newJType, NotificationChain msgs)
  {
    JvmTypeReference oldJType = jType;
    jType = newJType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SnipDSLPackage.ATTRIBUTE__JTYPE, oldJType, newJType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setJType(JvmTypeReference newJType)
  {
    if (newJType != jType)
    {
      NotificationChain msgs = null;
      if (jType != null)
        msgs = ((InternalEObject)jType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SnipDSLPackage.ATTRIBUTE__JTYPE, null, msgs);
      if (newJType != null)
        msgs = ((InternalEObject)newJType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SnipDSLPackage.ATTRIBUTE__JTYPE, null, msgs);
      msgs = basicSetJType(newJType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SnipDSLPackage.ATTRIBUTE__JTYPE, newJType, newJType));
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
      eNotify(new ENotificationImpl(this, Notification.SET, SnipDSLPackage.ATTRIBUTE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XExpression getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetValue(XExpression newValue, NotificationChain msgs)
  {
    XExpression oldValue = value;
    value = newValue;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SnipDSLPackage.ATTRIBUTE__VALUE, oldValue, newValue);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(XExpression newValue)
  {
    if (newValue != value)
    {
      NotificationChain msgs = null;
      if (value != null)
        msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SnipDSLPackage.ATTRIBUTE__VALUE, null, msgs);
      if (newValue != null)
        msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SnipDSLPackage.ATTRIBUTE__VALUE, null, msgs);
      msgs = basicSetValue(newValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SnipDSLPackage.ATTRIBUTE__VALUE, newValue, newValue));
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
      case SnipDSLPackage.ATTRIBUTE__JTYPE:
        return basicSetJType(null, msgs);
      case SnipDSLPackage.ATTRIBUTE__VALUE:
        return basicSetValue(null, msgs);
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
      case SnipDSLPackage.ATTRIBUTE__VISIBLE:
        return getVisible();
      case SnipDSLPackage.ATTRIBUTE__JTYPE:
        return getJType();
      case SnipDSLPackage.ATTRIBUTE__NAME:
        return getName();
      case SnipDSLPackage.ATTRIBUTE__VALUE:
        return getValue();
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
      case SnipDSLPackage.ATTRIBUTE__VISIBLE:
        setVisible((Visibility)newValue);
        return;
      case SnipDSLPackage.ATTRIBUTE__JTYPE:
        setJType((JvmTypeReference)newValue);
        return;
      case SnipDSLPackage.ATTRIBUTE__NAME:
        setName((String)newValue);
        return;
      case SnipDSLPackage.ATTRIBUTE__VALUE:
        setValue((XExpression)newValue);
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
      case SnipDSLPackage.ATTRIBUTE__VISIBLE:
        setVisible(VISIBLE_EDEFAULT);
        return;
      case SnipDSLPackage.ATTRIBUTE__JTYPE:
        setJType((JvmTypeReference)null);
        return;
      case SnipDSLPackage.ATTRIBUTE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case SnipDSLPackage.ATTRIBUTE__VALUE:
        setValue((XExpression)null);
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
      case SnipDSLPackage.ATTRIBUTE__VISIBLE:
        return visible != VISIBLE_EDEFAULT;
      case SnipDSLPackage.ATTRIBUTE__JTYPE:
        return jType != null;
      case SnipDSLPackage.ATTRIBUTE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case SnipDSLPackage.ATTRIBUTE__VALUE:
        return value != null;
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
    result.append(" (visible: ");
    result.append(visible);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //attributeImpl
