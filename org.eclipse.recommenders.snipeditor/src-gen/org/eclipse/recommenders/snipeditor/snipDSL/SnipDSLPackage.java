/**
 */
package org.eclipse.recommenders.snipeditor.snipDSL;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.xtext.xbase.XbasePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.recommenders.snipeditor.snipDSL.SnipDSLFactory
 * @model kind="package"
 * @generated
 */
public interface SnipDSLPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "snipDSL";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/recommenders/snipeditor/SnipDSL";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "snipDSL";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SnipDSLPackage eINSTANCE = org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.domainmodelImpl <em>domainmodel</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.domainmodelImpl
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getdomainmodel()
   * @generated
   */
  int DOMAINMODEL = 0;

  /**
   * The feature id for the '<em><b>Import Section</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOMAINMODEL__IMPORT_SECTION = 0;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOMAINMODEL__ELEMENTS = 1;

  /**
   * The number of structural features of the '<em>domainmodel</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DOMAINMODEL_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.importDeclareImpl <em>import Declare</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.importDeclareImpl
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getimportDeclare()
   * @generated
   */
  int IMPORT_DECLARE = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_DECLARE__NAME = 0;

  /**
   * The number of structural features of the '<em>import Declare</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IMPORT_DECLARE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.packageDeclareImpl <em>package Declare</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.packageDeclareImpl
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getpackageDeclare()
   * @generated
   */
  int PACKAGE_DECLARE = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_DECLARE__NAME = XbasePackage.XEXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_DECLARE__ELEMENTS = XbasePackage.XEXPRESSION_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>package Declare</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PACKAGE_DECLARE_FEATURE_COUNT = XbasePackage.XEXPRESSION_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.entityImpl <em>entity</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.entityImpl
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getentity()
   * @generated
   */
  int ENTITY = 3;

  /**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__VISIBILITY = XbasePackage.XEXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__NAME = XbasePackage.XEXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Super Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__SUPER_TYPE = XbasePackage.XEXPRESSION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Features</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY__FEATURES = XbasePackage.XEXPRESSION_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>entity</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENTITY_FEATURE_COUNT = XbasePackage.XEXPRESSION_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.parameterImpl <em>parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.parameterImpl
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getparameter()
   * @generated
   */
  int PARAMETER = 4;

  /**
   * The feature id for the '<em><b>Parameter Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__PARAMETER_TYPE = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER__NAME = 1;

  /**
   * The number of structural features of the '<em>parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.methodImpl <em>method</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.methodImpl
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getmethod()
   * @generated
   */
  int METHOD = 5;

  /**
   * The feature id for the '<em><b>Visibility</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD__VISIBILITY = XbasePackage.XEXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>JType</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD__JTYPE = XbasePackage.XEXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD__NAME = XbasePackage.XEXPRESSION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Params</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD__PARAMS = XbasePackage.XEXPRESSION_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD__BODY = XbasePackage.XEXPRESSION_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>method</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int METHOD_FEATURE_COUNT = XbasePackage.XEXPRESSION_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.attributeImpl <em>attribute</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.attributeImpl
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getattribute()
   * @generated
   */
  int ATTRIBUTE = 6;

  /**
   * The feature id for the '<em><b>Visible</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__VISIBLE = XbasePackage.XEXPRESSION_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>JType</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__JTYPE = XbasePackage.XEXPRESSION_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__NAME = XbasePackage.XEXPRESSION_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE__VALUE = XbasePackage.XEXPRESSION_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>attribute</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ATTRIBUTE_FEATURE_COUNT = XbasePackage.XEXPRESSION_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.myBlockExpressionImpl <em>my Block Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.myBlockExpressionImpl
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getmyBlockExpression()
   * @generated
   */
  int MY_BLOCK_EXPRESSION = 7;

  /**
   * The feature id for the '<em><b>Expressions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MY_BLOCK_EXPRESSION__EXPRESSIONS = XbasePackage.XEXPRESSION_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>my Block Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MY_BLOCK_EXPRESSION_FEATURE_COUNT = XbasePackage.XEXPRESSION_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.Visibility <em>Visibility</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.recommenders.snipeditor.snipDSL.Visibility
   * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getVisibility()
   * @generated
   */
  int VISIBILITY = 8;


  /**
   * Returns the meta object for class '{@link org.eclipse.recommenders.snipeditor.snipDSL.domainmodel <em>domainmodel</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>domainmodel</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.domainmodel
   * @generated
   */
  EClass getdomainmodel();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.recommenders.snipeditor.snipDSL.domainmodel#getImportSection <em>Import Section</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Import Section</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.domainmodel#getImportSection()
   * @see #getdomainmodel()
   * @generated
   */
  EReference getdomainmodel_ImportSection();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.recommenders.snipeditor.snipDSL.domainmodel#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.domainmodel#getElements()
   * @see #getdomainmodel()
   * @generated
   */
  EReference getdomainmodel_Elements();

  /**
   * Returns the meta object for class '{@link org.eclipse.recommenders.snipeditor.snipDSL.importDeclare <em>import Declare</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>import Declare</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.importDeclare
   * @generated
   */
  EClass getimportDeclare();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.recommenders.snipeditor.snipDSL.importDeclare#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.importDeclare#getName()
   * @see #getimportDeclare()
   * @generated
   */
  EAttribute getimportDeclare_Name();

  /**
   * Returns the meta object for class '{@link org.eclipse.recommenders.snipeditor.snipDSL.packageDeclare <em>package Declare</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>package Declare</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.packageDeclare
   * @generated
   */
  EClass getpackageDeclare();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.recommenders.snipeditor.snipDSL.packageDeclare#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.packageDeclare#getName()
   * @see #getpackageDeclare()
   * @generated
   */
  EAttribute getpackageDeclare_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.recommenders.snipeditor.snipDSL.packageDeclare#getElements <em>Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Elements</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.packageDeclare#getElements()
   * @see #getpackageDeclare()
   * @generated
   */
  EReference getpackageDeclare_Elements();

  /**
   * Returns the meta object for class '{@link org.eclipse.recommenders.snipeditor.snipDSL.entity <em>entity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>entity</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.entity
   * @generated
   */
  EClass getentity();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.recommenders.snipeditor.snipDSL.entity#getVisibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visibility</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.entity#getVisibility()
   * @see #getentity()
   * @generated
   */
  EAttribute getentity_Visibility();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.recommenders.snipeditor.snipDSL.entity#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.entity#getName()
   * @see #getentity()
   * @generated
   */
  EAttribute getentity_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.recommenders.snipeditor.snipDSL.entity#getSuperType <em>Super Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Super Type</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.entity#getSuperType()
   * @see #getentity()
   * @generated
   */
  EReference getentity_SuperType();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.recommenders.snipeditor.snipDSL.entity#getFeatures <em>Features</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Features</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.entity#getFeatures()
   * @see #getentity()
   * @generated
   */
  EReference getentity_Features();

  /**
   * Returns the meta object for class '{@link org.eclipse.recommenders.snipeditor.snipDSL.parameter <em>parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>parameter</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.parameter
   * @generated
   */
  EClass getparameter();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.recommenders.snipeditor.snipDSL.parameter#getParameterType <em>Parameter Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameter Type</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.parameter#getParameterType()
   * @see #getparameter()
   * @generated
   */
  EReference getparameter_ParameterType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.recommenders.snipeditor.snipDSL.parameter#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.parameter#getName()
   * @see #getparameter()
   * @generated
   */
  EAttribute getparameter_Name();

  /**
   * Returns the meta object for class '{@link org.eclipse.recommenders.snipeditor.snipDSL.method <em>method</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>method</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.method
   * @generated
   */
  EClass getmethod();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.recommenders.snipeditor.snipDSL.method#getVisibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visibility</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.method#getVisibility()
   * @see #getmethod()
   * @generated
   */
  EAttribute getmethod_Visibility();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.recommenders.snipeditor.snipDSL.method#getJType <em>JType</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>JType</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.method#getJType()
   * @see #getmethod()
   * @generated
   */
  EReference getmethod_JType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.recommenders.snipeditor.snipDSL.method#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.method#getName()
   * @see #getmethod()
   * @generated
   */
  EAttribute getmethod_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.recommenders.snipeditor.snipDSL.method#getParams <em>Params</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Params</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.method#getParams()
   * @see #getmethod()
   * @generated
   */
  EReference getmethod_Params();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.recommenders.snipeditor.snipDSL.method#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.method#getBody()
   * @see #getmethod()
   * @generated
   */
  EReference getmethod_Body();

  /**
   * Returns the meta object for class '{@link org.eclipse.recommenders.snipeditor.snipDSL.attribute <em>attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>attribute</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.attribute
   * @generated
   */
  EClass getattribute();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.recommenders.snipeditor.snipDSL.attribute#getVisible <em>Visible</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Visible</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.attribute#getVisible()
   * @see #getattribute()
   * @generated
   */
  EAttribute getattribute_Visible();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.recommenders.snipeditor.snipDSL.attribute#getJType <em>JType</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>JType</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.attribute#getJType()
   * @see #getattribute()
   * @generated
   */
  EReference getattribute_JType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.recommenders.snipeditor.snipDSL.attribute#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.attribute#getName()
   * @see #getattribute()
   * @generated
   */
  EAttribute getattribute_Name();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.recommenders.snipeditor.snipDSL.attribute#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.attribute#getValue()
   * @see #getattribute()
   * @generated
   */
  EReference getattribute_Value();

  /**
   * Returns the meta object for class '{@link org.eclipse.recommenders.snipeditor.snipDSL.myBlockExpression <em>my Block Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>my Block Expression</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.myBlockExpression
   * @generated
   */
  EClass getmyBlockExpression();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.recommenders.snipeditor.snipDSL.myBlockExpression#getExpressions <em>Expressions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expressions</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.myBlockExpression#getExpressions()
   * @see #getmyBlockExpression()
   * @generated
   */
  EReference getmyBlockExpression_Expressions();

  /**
   * Returns the meta object for enum '{@link org.eclipse.recommenders.snipeditor.snipDSL.Visibility <em>Visibility</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Visibility</em>'.
   * @see org.eclipse.recommenders.snipeditor.snipDSL.Visibility
   * @generated
   */
  EEnum getVisibility();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  SnipDSLFactory getSnipDSLFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.domainmodelImpl <em>domainmodel</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.domainmodelImpl
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getdomainmodel()
     * @generated
     */
    EClass DOMAINMODEL = eINSTANCE.getdomainmodel();

    /**
     * The meta object literal for the '<em><b>Import Section</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOMAINMODEL__IMPORT_SECTION = eINSTANCE.getdomainmodel_ImportSection();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DOMAINMODEL__ELEMENTS = eINSTANCE.getdomainmodel_Elements();

    /**
     * The meta object literal for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.importDeclareImpl <em>import Declare</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.importDeclareImpl
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getimportDeclare()
     * @generated
     */
    EClass IMPORT_DECLARE = eINSTANCE.getimportDeclare();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IMPORT_DECLARE__NAME = eINSTANCE.getimportDeclare_Name();

    /**
     * The meta object literal for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.packageDeclareImpl <em>package Declare</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.packageDeclareImpl
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getpackageDeclare()
     * @generated
     */
    EClass PACKAGE_DECLARE = eINSTANCE.getpackageDeclare();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PACKAGE_DECLARE__NAME = eINSTANCE.getpackageDeclare_Name();

    /**
     * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PACKAGE_DECLARE__ELEMENTS = eINSTANCE.getpackageDeclare_Elements();

    /**
     * The meta object literal for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.entityImpl <em>entity</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.entityImpl
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getentity()
     * @generated
     */
    EClass ENTITY = eINSTANCE.getentity();

    /**
     * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY__VISIBILITY = eINSTANCE.getentity_Visibility();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENTITY__NAME = eINSTANCE.getentity_Name();

    /**
     * The meta object literal for the '<em><b>Super Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY__SUPER_TYPE = eINSTANCE.getentity_SuperType();

    /**
     * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENTITY__FEATURES = eINSTANCE.getentity_Features();

    /**
     * The meta object literal for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.parameterImpl <em>parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.parameterImpl
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getparameter()
     * @generated
     */
    EClass PARAMETER = eINSTANCE.getparameter();

    /**
     * The meta object literal for the '<em><b>Parameter Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER__PARAMETER_TYPE = eINSTANCE.getparameter_ParameterType();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PARAMETER__NAME = eINSTANCE.getparameter_Name();

    /**
     * The meta object literal for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.methodImpl <em>method</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.methodImpl
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getmethod()
     * @generated
     */
    EClass METHOD = eINSTANCE.getmethod();

    /**
     * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METHOD__VISIBILITY = eINSTANCE.getmethod_Visibility();

    /**
     * The meta object literal for the '<em><b>JType</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference METHOD__JTYPE = eINSTANCE.getmethod_JType();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute METHOD__NAME = eINSTANCE.getmethod_Name();

    /**
     * The meta object literal for the '<em><b>Params</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference METHOD__PARAMS = eINSTANCE.getmethod_Params();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference METHOD__BODY = eINSTANCE.getmethod_Body();

    /**
     * The meta object literal for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.attributeImpl <em>attribute</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.attributeImpl
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getattribute()
     * @generated
     */
    EClass ATTRIBUTE = eINSTANCE.getattribute();

    /**
     * The meta object literal for the '<em><b>Visible</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ATTRIBUTE__VISIBLE = eINSTANCE.getattribute_Visible();

    /**
     * The meta object literal for the '<em><b>JType</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ATTRIBUTE__JTYPE = eINSTANCE.getattribute_JType();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ATTRIBUTE__NAME = eINSTANCE.getattribute_Name();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ATTRIBUTE__VALUE = eINSTANCE.getattribute_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.impl.myBlockExpressionImpl <em>my Block Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.myBlockExpressionImpl
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getmyBlockExpression()
     * @generated
     */
    EClass MY_BLOCK_EXPRESSION = eINSTANCE.getmyBlockExpression();

    /**
     * The meta object literal for the '<em><b>Expressions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MY_BLOCK_EXPRESSION__EXPRESSIONS = eINSTANCE.getmyBlockExpression_Expressions();

    /**
     * The meta object literal for the '{@link org.eclipse.recommenders.snipeditor.snipDSL.Visibility <em>Visibility</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.recommenders.snipeditor.snipDSL.Visibility
     * @see org.eclipse.recommenders.snipeditor.snipDSL.impl.SnipDSLPackageImpl#getVisibility()
     * @generated
     */
    EEnum VISIBILITY = eINSTANCE.getVisibility();

  }

} //SnipDSLPackage
