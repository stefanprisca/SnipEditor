package org.eclipse.recommenders.templates.jvmmodel

import com.google.inject.Inject
import org.eclipse.xtext.xbase.jvmmodel.AbstractModelInferrer
import org.eclipse.xtext.xbase.jvmmodel.IJvmDeclaredTypeAcceptor
import org.eclipse.xtext.xbase.jvmmodel.JvmTypesBuilder
import org.eclipse.recommenders.templates.snipDSL.domainmodel
import org.eclipse.xtext.naming.IQualifiedNameProvider
import org.eclipse.recommenders.templates.snipDSL.method
import org.eclipse.recommenders.templates.snipDSL.jFaceVariableDeclaration
import org.eclipse.recommenders.templates.snipDSL.entity

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
	@Inject extension IQualifiedNameProvider
		
	def dispatch void infer(domainmodel element, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase){
	//	scopeProvider.getScope(element, null);
		//println(this)
		var name=new String();
		if(element.entity !=null){
			name=element.entity.name
		}else
			name = "CodeSnippet_"+System.currentTimeMillis%100
	
		acceptor.accept(element.toClass(name)).initializeLater[
			if(element.body!=null){
				var dbody=element.body;
				for(feature : dbody.expressions){
			switch feature
			{
				/*attributeDeclaration: {
					if(feature.name.contains("newName")){
   										feature.setName(
   											'${'+feature.name.substring(feature.name.indexOf('{')+1,
   												feature.name.indexOf(':')) +'}'
   										)
   									}
   									println("Attribute type: "+feature.jfaceType)
   									if(feature.JType==null)
   									{
   										if(feature.jfaceType.type.contains("_type"))
   										{
   											feature.setJType(newTypeRef(feature, typeof(Object) , null).addArrayTypeDimension);
   											
   										}
   									}
   								members+=feature.toField(feature.name, feature.JType)
   							//	scope.getScope(element, feature.eContainmentFeature );
				}*/
				method :{
   								//if(feature.JType!=null){
          						  	members+=feature.toMethod(feature.name, 
          						  		 if (feature.JType.type != null) feature.JType.type 
          						  		 else newTypeRef(Void.TYPE)
          						  	) [
              						documentation = feature.documentation
              						for (p : feature.params) {
                					parameters += p.toParameter(p.name, p.parameterType)
              						}
             	 					body =feature.body  
             	 				]
             	 				}
             	
   						
   						}
				
			}
			members+=element.toMethod("main", newTypeRef(Void::TYPE))[
					body=element.body
				]
				
			}
		]
		
		
	}
	
	
   	def dispatch void infer(entity element, IJvmDeclaredTypeAcceptor acceptor, boolean isPreIndexingPhase) {
   		// Here you explain how your model is mapped to Java elements, by writing the actual translation code.
   		
   	acceptor.accept( element.toClass( element.fullyQualifiedName )
   			 ).initializeLater [
     			 documentation = element.documentation
      				if (element.JType != null)
        			superTypes += element.JType.type.cloneWithProxies 
   				for (feature : element.features) {
   					switch feature{
   						jFaceVariableDeclaration //sets the name of the feature accordingly
   								:{ 
   									if(feature.simpleName.contains("newName")){
   										feature.setSimpleName(
   											'${'+feature.simpleName.substring(feature.simpleName.indexOf('{')+1,
   												feature.simpleName.indexOf(':')) +'}'
   										)
   									}
   									if(feature.JType.type==null)
   									{
   										feature.JType.setType(newTypeRef(feature, typeof(Object) , null));
   									}
   									members+=feature.toField(feature.simpleName, feature.JType.type)
   								}
   						method 
   								:{
   								//if(feature.JType!=null){
          						  	members += feature.toMethod(feature.name, 
          						  		 if (feature.JType != null) feature.JType.type 
          						  		 else newTypeRef(Void::TYPE)
          						  	) [
              						documentation = feature.documentation
              						for (p : feature.params) {
                					parameters += p.toParameter(p.name, p.parameterType)
              						}
             	 					body =feature.body  
             	 				]
             	 				}
   						
   						}
   					
   				}
   		
   			]
   	}
   
}


