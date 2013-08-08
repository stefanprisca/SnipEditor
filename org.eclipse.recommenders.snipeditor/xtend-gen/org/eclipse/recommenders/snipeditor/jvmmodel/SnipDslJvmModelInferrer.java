package org.eclipse.recommenders.snipeditor.jvmmodel;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.recommenders.snipeditor.compiler.SnipDSLSpecificCompiler;
import org.eclipse.recommenders.snipeditor.scoping.SnipDSLLocalScope;
import org.eclipse.recommenders.snipeditor.snipDSL.attributeDeclaration;
import org.eclipse.recommenders.snipeditor.snipDSL.entity;
import org.eclipse.recommenders.snipeditor.snipDSL.method;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.compiler.TypeReferenceSerializer;
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor;
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor.IPostIndexingInitializing;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

/**
 * <p>Infers a JVM model from the source model.</p>
 * 
 * <p>The JVM model should contain all elements that would appear in the Java code
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>
 */
@SuppressWarnings("all")
public class SnipDSLJvmModelInferrer extends AbstractModelInferrer {
  /**
   * convenience API to build and initialize JVM types and their members.
   */
  @Inject
  @Extension
  private JvmTypesBuilder _jvmTypesBuilder;
  
  @Inject
  private JvmTypesBuilder typeBuilder;
  
  @Inject
  @Extension
  private SnipDSLLocalScope _snipDSLLocalScope;
  
  @Inject
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider;
  
  @Inject
  @Extension
  private TypeReferenceSerializer _typeReferenceSerializer;
  
  @Inject
  private SnipDSLSpecificCompiler snipCompiler;
  
  protected void _infer(final entity element, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(element);
    JvmGenericType _class = this._jvmTypesBuilder.toClass(element, _fullyQualifiedName);
    IPostIndexingInitializing<JvmGenericType> _accept = acceptor.<JvmGenericType>accept(_class);
    final Procedure1<JvmGenericType> _function = new Procedure1<JvmGenericType>() {
        public void apply(final JvmGenericType it) {
          String _documentation = SnipDSLJvmModelInferrer.this._jvmTypesBuilder.getDocumentation(element);
          SnipDSLJvmModelInferrer.this._jvmTypesBuilder.setDocumentation(it, _documentation);
          JvmTypeReference _jType = element.getJType();
          boolean _notEquals = (!Objects.equal(_jType, null));
          if (_notEquals) {
            EList<JvmTypeReference> _superTypes = it.getSuperTypes();
            JvmTypeReference _jType_1 = element.getJType();
            JvmTypeReference _cloneWithProxies = SnipDSLJvmModelInferrer.this._jvmTypesBuilder.cloneWithProxies(_jType_1);
            SnipDSLJvmModelInferrer.this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes, _cloneWithProxies);
          }
          EList<XExpression> _features = element.getFeatures();
          for (final XExpression feature : _features) {
            boolean _matched = false;
            if (!_matched) {
              if (feature instanceof attributeDeclaration) {
                final attributeDeclaration _attributeDeclaration = (attributeDeclaration)feature;
                _matched=true;
                boolean _or = false;
                String _name = _attributeDeclaration.getName();
                boolean _contains = _name.contains("freeName");
                if (_contains) {
                  _or = true;
                } else {
                  String _name_1 = _attributeDeclaration.getName();
                  boolean _contains_1 = _name_1.contains("newName");
                  _or = (_contains || _contains_1);
                }
                if (_or) {
                  String _name_2 = _attributeDeclaration.getName();
                  String _name_3 = _attributeDeclaration.getName();
                  int _indexOf = _name_3.indexOf("(");
                  int _plus = (_indexOf + 1);
                  String _name_4 = _attributeDeclaration.getName();
                  int _indexOf_1 = _name_4.indexOf(")");
                  String _substring = _name_2.substring(_plus, _indexOf_1);
                  String _plus_1 = ("${" + _substring);
                  String _plus_2 = (_plus_1 + "}");
                  _attributeDeclaration.setName(_plus_2);
                }
                JvmTypeReference _jType_2 = _attributeDeclaration.getJType();
                boolean _equals = Objects.equal(_jType_2, null);
                if (_equals) {
                  JvmTypeReference _newTypeRef = SnipDSLJvmModelInferrer.this.typeBuilder.newTypeRef(_attributeDeclaration, Object.class, null);
                  _attributeDeclaration.setJType(_newTypeRef);
                }
                EList<JvmMember> _members = it.getMembers();
                String _name_5 = _attributeDeclaration.getName();
                JvmTypeReference _jType_3 = _attributeDeclaration.getJType();
                JvmField _field = SnipDSLJvmModelInferrer.this._jvmTypesBuilder.toField(_attributeDeclaration, _name_5, _jType_3);
                SnipDSLJvmModelInferrer.this._jvmTypesBuilder.<JvmField>operator_add(_members, _field);
              }
            }
            if (!_matched) {
              if (feature instanceof method) {
                final method _method = (method)feature;
                _matched=true;
                EList<JvmMember> _members = it.getMembers();
                String _name = _method.getName();
                JvmTypeReference _xifexpression = null;
                JvmTypeReference _jType_2 = _method.getJType();
                boolean _notEquals_1 = (!Objects.equal(_jType_2, null));
                if (_notEquals_1) {
                  JvmTypeReference _jType_3 = _method.getJType();
                  _xifexpression = _jType_3;
                } else {
                  JvmTypeReference _newTypeRef = SnipDSLJvmModelInferrer.this._jvmTypesBuilder.newTypeRef(it, Void.TYPE);
                  _xifexpression = _newTypeRef;
                }
                final Procedure1<JvmOperation> _function = new Procedure1<JvmOperation>() {
                    public void apply(final JvmOperation it) {
                      String _documentation = SnipDSLJvmModelInferrer.this._jvmTypesBuilder.getDocumentation(_method);
                      SnipDSLJvmModelInferrer.this._jvmTypesBuilder.setDocumentation(it, _documentation);
                      EList<JvmFormalParameter> _params = _method.getParams();
                      for (final JvmFormalParameter p : _params) {
                        EList<JvmFormalParameter> _parameters = it.getParameters();
                        String _name = p.getName();
                        JvmTypeReference _parameterType = p.getParameterType();
                        JvmFormalParameter _parameter = SnipDSLJvmModelInferrer.this._jvmTypesBuilder.toParameter(p, _name, _parameterType);
                        SnipDSLJvmModelInferrer.this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
                      }
                      XExpression _body = _method.getBody();
                      SnipDSLJvmModelInferrer.this._jvmTypesBuilder.setBody(it, _body);
                    }
                  };
                JvmOperation _method_1 = SnipDSLJvmModelInferrer.this._jvmTypesBuilder.toMethod(_method, _name, _xifexpression, _function);
                SnipDSLJvmModelInferrer.this._jvmTypesBuilder.<JvmOperation>operator_add(_members, _method_1);
              }
            }
          }
        }
      };
    _accept.initializeLater(_function);
  }
  
  public void infer(final EObject element, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    if (element instanceof entity) {
      _infer((entity)element, acceptor, isPreIndexingPhase);
      return;
    } else if (element != null) {
      _infer(element, acceptor, isPreIndexingPhase);
      return;
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(element, acceptor, isPreIndexingPhase).toString());
    }
  }
}
