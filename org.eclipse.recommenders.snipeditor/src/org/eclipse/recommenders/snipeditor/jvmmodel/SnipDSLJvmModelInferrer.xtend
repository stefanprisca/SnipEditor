package org.eclipse.recommenders.snipeditor.jvmmodel

import com.google.inject.Inject
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder

import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.recommenders.snipeditor.snipDSL.attribute
import org.eclipse.recommenders.snipeditor.snipDSL.method
import org.eclipse.recommenders.snipeditor.snipDSL.entity

/**
 * <p>Infers a JVM model from the source model.</p> 
 *
 * <p>The JVM model should contain all elements that would appear in the Java code 
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>     
 */
class SnipDslJvmModelInferrer extends AbstractModelInferrer {

    /**
     * convenience API to build and initialize JVM types and their members.
     */
	@Inject extension JvmTypesBuilder
	@Inject extension IQualifiedNameProvider

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
   	def dispatch void infer(entity element, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
   		// Here you explain how your model is mapped to Java elements, by writing the actual translation code.
   		
   		// An implementation for the initial hello world example could look like this:
   		acceptor.accept(element.toClass(element.fullyQualifiedName))
   			.initializeLater([
   				documentation = element.documentation
   				if(element.superType!=null)
   					superTypes+=element.superType.cloneWithProxies
   			
   				for (feature : element.features) {
		   			switch feature
		   			{
		   				attribute :{
		   					members += feature.toField(feature.name, feature.JType);
		   					members += feature.toGetter(feature.name, feature.JType);
		   					members += feature.toSetter(feature.name, feature.JType);
		   				}
		   				method :{
		   					members += feature.toMethod(feature.name, feature.JType)[
		   						documentation = feature.documentation
		   						for(p:feature.params){
		   							parameters += p.toParameter(feature.name, feature.JType)
		   						}
		   						body=feature.body;
		   					]
		   				}
		   			}
		   	}
   	
   	])
   	}
}

