package org.eclipse.recommenders.snipeditor.jvmmodel

import com.google.inject.Inject
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder
import org.eclipse.recommenders.snipeditor.snipDSL.domainmodel
<<<<<<< Updated upstream
=======
import org.eclipse.recommenders.snipeditor.snipDSL.entity
import org.eclipse.recommenders.snipeditor.snipDSL.attributeDeclaration
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.recommenders.snipeditor.snipDSL.method
import org.eclipse.xtext.xbase.compiler.TypeReferenceSerializer
import org.eclipse.xtext.xbase.XExpression
import org.eclipse.recommenders.snipeditor.snipDSL.XVariableDeclaration
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable
import org.eclipse.recommenders.snipeditor.snipDSL.arrayAssignment
import org.eclipse.xtext.xbase.compiler.XbaseCompiler
import org.eclipse.recommenders.snipeditor.generator.SnipDSLGenerator
import org.eclipse.recommenders.snipeditor.compiler.SnipDSLSpecificCompiler
import org.eclipse.recommenders.snipeditor.snipDSL.impl.XVariableDeclarationImpl
import org.eclipse.xtext.xbase.scoping.batch.IFeatureScopeSession
import org.eclipse.xtext.common.types.JvmIdentifiableElement
>>>>>>> Stashed changes

/**
 * <p>Infers a JVM model from the source model.</p> 
 *
 * <p>The JVM model should contain all elements that would appear in the Java code 
 * which is generated from the source model. Other models link against the JVM model rather than the source model.</p>     
 */
class SnipDSLJvmModelInferrer extends AbstractModelInferrer {

    /**
     * convenience API to build and initialize JVM types and their members.
     */
	@Inject extension JvmTypesBuilder
<<<<<<< Updated upstream

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
   	def dispatch void infer(domainmodel element, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
   		// Here you explain how your model is mapped to Java elements, by writing the actual translation code.
   		
   		// An implementation for the initial hello world example could look like this:
//   		acceptor.accept(element.toClass("my.company.greeting.MyGreetings"))
//   			.initializeLater([
//   				for (greeting : element.greetings) {
//   					members += greeting.toMethod("hello" + greeting.name, greeting.newTypeRef(typeof(String))) [
//   						body = [
//   							append('''return "Hello «greeting.name»";''')
//   						]
//   					]
//   				}
//   			])
=======
	@Inject extension IQualifiedNameProvider
	@Inject extension TypeReferenceSerializer
	@Inject SnipDSLSpecificCompiler snipCompiler
	@Inject SnipDSLGenerator myGenerator
	@SuppressWarnings("all")
   	def dispatch void infer(entity element, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
   		// Here you explain how your model is mapped to Java elements, by writing the actual translation code.
   		
   	acceptor.accept( element.toClass( element.fullyQualifiedName )
   			 ).initializeLater [
     			 documentation = element.documentation
      				if (element.JType != null)
        			superTypes += element.JType.type.cloneWithProxies 
   				for (feature : element.features) {
   					switch feature{
   						attributeDeclaration case feature.JType.type!=null
   								:{ 
   									members+=feature.toField(feature.name, feature.JType.type)
   								}
   						method 
   								:{
   								if(feature.JType!=null){
          						  	members += feature.toMethod(feature.name, feature.JType.type) [
              						documentation = feature.documentation
              						for (p : feature.params) {
                					parameters += p.toParameter(p.name, p.parameterType)
              						}
             	 					body = [
             	 						for(blkFeature: feature.body.eContents)
             	 						{
             	 							switch (blkFeature)
             	 							{
             	 								XVariableDeclarationImpl:
             	 									{
             	 										//it.generateCode(blkFeature)
			             	 							snipCompiler._toJavaStatement(blkFeature, it, true);
			             	 						}
             	 							}
             	 							
             	 						}
             	 						//snipCompiler.compile(feature, it, feature.JType.type)
             	 					]
             	 					
   								] }else{
   									//TODO: members += feature.toMethod(feature.name, null ) [
              						//documentation = feature.documentation
              						/*for (p : feature.params) {
                					parameters += p.toParameter(p.name, p.JType.type)
              						}*/
             	 				//body = feature.body ]
             	 				}
   								}
   						
   						}
   					
   				}
   		
   			]
>>>>>>> Stashed changes
   	}
	
	/*def void generateCode(ITreeAppendable it, XVariableDeclaration declaration){
		val newVarName = it.declareVariable(declaration, declaration.name)
        declaration.type.type.serialize(declaration, it)
        it.append(" " + newVarName +';' )
	}*/
	
	
	
	
}

