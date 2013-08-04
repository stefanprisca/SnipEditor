package org.eclipse.recommenders.snipeditor.jvmmodel

import com.google.inject.Inject
import org.eclipse.recommenders.snipeditor.compiler.SnipDSLSpecificCompiler
import org.eclipse.recommenders.snipeditor.scoping.SnipDSLLocalScope
import org.eclipse.recommenders.snipeditor.snipDSL.attributeDeclaration
import org.eclipse.recommenders.snipeditor.snipDSL.entity
import org.eclipse.recommenders.snipeditor.snipDSL.method
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.xtext.xbase.compiler.TypeReferenceSerializer
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder

//import org.eclipse.recommenders.snipeditor.snipDSL.XAssignment

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
	@Inject JvmTypesBuilder typeBuilder;
	
	@Inject	extension SnipDSLLocalScope
	@Inject extension IQualifiedNameProvider
	@Inject extension TypeReferenceSerializer
	@Inject SnipDSLSpecificCompiler snipCompiler
	
   	def dispatch void infer(entity element, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
   		// Here you explain how your model is mapped to Java elements, by writing the actual translation code.
   		
   	acceptor.accept( element.toClass( element.fullyQualifiedName )
   			 ).initializeLater [
     			 documentation = element.documentation
      				if (element.JType != null)
        			superTypes += element.JType.cloneWithProxies 
   				for (feature : element.features) {
   					switch feature{
   						attributeDeclaration //case feature.JType.type!=null
   								:{ 
   									if(feature.name.contains("freeName") || feature.name.contains("newName")){
   										feature.setName(
   											'${'+feature.name.substring(feature.name.indexOf('(')+1,
   												feature.name.indexOf(')')) +'}'
   										)
   									}
   									members+=feature.toField(feature.name, feature.JType)
   								}
   						method 
   								:{
   								//if(feature.JType!=null){
          						  	members += feature.toMethod(feature.name, 
          						  		 if (feature.JType != null) feature.JType 
          						  		 else newTypeRef(Void::TYPE)
          						  	) [
              						documentation = feature.documentation
              						for (p : feature.params) {
                					parameters += p.toParameter(p.name, p.JType)
              						}
             	 					body =feature.body  /*[	
             	 						for(blkFeature : feature.body.eContents)
             	 						{
             	 							switch(blkFeature){
             	 									
             	 								XExpression:
             	 									snipCompiler.toJavaStatement(blkFeature, it, false)
             	 									
             	 								XVariableDeclarationImpl:
             	 									{
             	 										snipCompiler.toJavaStatement(blkFeature, it, true)
             	 										
             	 									//	JvmTypesBuilder.declaredFields.add(blkFeature.toField(blkFeature.name.name, blkFeature.JType.type));
             	 									}
             	 								
             	 							}
             	 							//feature.body.getScope()
             	 							
             	 						}
             	 						//it.closeScope
             	 						//if(feature.JType.type!=null)
             	 						//	snipCompiler.compile(feature.body, it, feature.JType.type)
             	 					]*/
             	 				]//else{
   									//TODO: members += feature.toMethod(feature.name, null ) [
              						//documentation = feature.documentation
              						/*for (p : feature.params) {
                					parameters += p.toParameter(p.name, p.JType.type)
              						}*/
             	 				//body = feature.body ]
             	 				//}
   								}
   						
   						}
   					
   				}
   		
   			]
   	}
   
}

