package org.eclipse.recommenders.snipeditor.jvmmodel;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.recommenders.snipeditor.snipDSL.attribute;
import org.eclipse.recommenders.snipeditor.snipDSL.entity;
import org.eclipse.recommenders.snipeditor.snipDSL.method;
import org.eclipse.recommenders.snipeditor.snipDSL.parameter;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.XExpression;
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
public class SnipDslJvmModelInferrer extends AbstractModelInferrer {
  /**
   * convenience API to build and initialize JVM types and their members.
   */
  @Inject
  @Extension
  private JvmTypesBuilder _jvmTypesBuilder;
  
  @Inject
  @Extension
  private IQualifiedNameProvider _iQualifiedNameProvider;
  
  /**
   * The dispatch method {@code infer} is called for each instance of the
   * given element's type that is contained in a resource.
   * 
   * @param element
   *            the model to create one or more
   *            {@link org.eclipse.xtext.common.types.JvmDeclaredType declared
   *            types} from.
   * @param acceptor
   *            each created
   *            {@link org.eclipse.xtext.common.types.JvmDeclaredType type}
   *            without a container should be passed to the acceptor in order
   *            get attached to the current resource. The acceptor's
   *            {@link IJvmDeclaredTypeAcceptor#accept(org.eclipse.xtext.common.types.JvmDeclaredType)
   *            accept(..)} method takes the constructed empty type for the
   *            pre-indexing phase. This one is further initialized in the
   *            indexing phase using the closure you pass to the returned
   *            {@link org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor.IPostIndexingInitializing#initializeLater(org.eclipse.xtext.xbase.lib.Procedures.Procedure1)
   *            initializeLater(..)}.
   * @param isPreIndexingPhase
   *            whether the method is called in a pre-indexing phase, i.e.
   *            when the global index is not yet fully updated. You must not
   *            rely on linking using the index if isPreIndexingPhase is
   *            <code>true</code>.
   */
  protected void _infer(final entity element, final IJvmDeclaredTypeAcceptor acceptor, final boolean isPreIndexingPhase) {
    QualifiedName _fullyQualifiedName = this._iQualifiedNameProvider.getFullyQualifiedName(element);
    JvmGenericType _class = this._jvmTypesBuilder.toClass(element, _fullyQualifiedName);
    IPostIndexingInitializing<JvmGenericType> _accept = acceptor.<JvmGenericType>accept(_class);
    final Procedure1<JvmGenericType> _function = new Procedure1<JvmGenericType>() {
        public void apply(final JvmGenericType it) {
          String _documentation = SnipDslJvmModelInferrer.this._jvmTypesBuilder.getDocumentation(element);
          SnipDslJvmModelInferrer.this._jvmTypesBuilder.setDocumentation(it, _documentation);
          JvmTypeReference _superType = element.getSuperType();
          boolean _notEquals = (!Objects.equal(_superType, null));
          if (_notEquals) {
            EList<JvmTypeReference> _superTypes = it.getSuperTypes();
            JvmTypeReference _superType_1 = element.getSuperType();
            JvmTypeReference _cloneWithProxies = SnipDslJvmModelInferrer.this._jvmTypesBuilder.cloneWithProxies(_superType_1);
            SnipDslJvmModelInferrer.this._jvmTypesBuilder.<JvmTypeReference>operator_add(_superTypes, _cloneWithProxies);
          }
          EList<XExpression> _features = element.getFeatures();
          for (final XExpression feature : _features) {
            boolean _matched = false;
            if (!_matched) {
              if (feature instanceof attribute) {
                final attribute _attribute = (attribute)feature;
                _matched=true;
                EList<JvmMember> _members = it.getMembers();
                String _name = _attribute.getName();
                JvmTypeReference _jType = _attribute.getJType();
                JvmField _field = SnipDslJvmModelInferrer.this._jvmTypesBuilder.toField(_attribute, _name, _jType);
                SnipDslJvmModelInferrer.this._jvmTypesBuilder.<JvmField>operator_add(_members, _field);
                EList<JvmMember> _members_1 = it.getMembers();
                String _name_1 = _attribute.getName();
                JvmTypeReference _jType_1 = _attribute.getJType();
                JvmOperation _getter = SnipDslJvmModelInferrer.this._jvmTypesBuilder.toGetter(_attribute, _name_1, _jType_1);
                SnipDslJvmModelInferrer.this._jvmTypesBuilder.<JvmOperation>operator_add(_members_1, _getter);
                EList<JvmMember> _members_2 = it.getMembers();
                String _name_2 = _attribute.getName();
                JvmTypeReference _jType_2 = _attribute.getJType();
                JvmOperation _setter = SnipDslJvmModelInferrer.this._jvmTypesBuilder.toSetter(_attribute, _name_2, _jType_2);
                SnipDslJvmModelInferrer.this._jvmTypesBuilder.<JvmOperation>operator_add(_members_2, _setter);
              }
            }
            if (!_matched) {
              if (feature instanceof method) {
                final method _method = (method)feature;
                _matched=true;
                EList<JvmMember> _members = it.getMembers();
                String _name = _method.getName();
                JvmTypeReference _jType = _method.getJType();
                final Procedure1<JvmOperation> _function = new Procedure1<JvmOperation>() {
                    public void apply(final JvmOperation it) {
                      String _documentation = SnipDslJvmModelInferrer.this._jvmTypesBuilder.getDocumentation(_method);
                      SnipDslJvmModelInferrer.this._jvmTypesBuilder.setDocumentation(it, _documentation);
                      EList<parameter> _params = _method.getParams();
                      for (final parameter p : _params) {
                        EList<JvmFormalParameter> _parameters = it.getParameters();
                        String _name = _method.getName();
                        JvmTypeReference _jType = _method.getJType();
                        JvmFormalParameter _parameter = SnipDslJvmModelInferrer.this._jvmTypesBuilder.toParameter(p, _name, _jType);
                        SnipDslJvmModelInferrer.this._jvmTypesBuilder.<JvmFormalParameter>operator_add(_parameters, _parameter);
                      }
                      XExpression _body = _method.getBody();
                      SnipDslJvmModelInferrer.this._jvmTypesBuilder.setBody(it, _body);
                    }
                  };
                JvmOperation _method_1 = SnipDslJvmModelInferrer.this._jvmTypesBuilder.toMethod(_method, _name, _jType, _function);
                SnipDslJvmModelInferrer.this._jvmTypesBuilder.<JvmOperation>operator_add(_members, _method_1);
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