SnipEditor_MavenBuild
=====================
Working maven build and most of the JFace grammar. <br>
The update site can be found here[1]. In order to use, please follow the instructions provided<br>
in the update site readme file. It does not work directly from eclipse IDE(yet).<br>  
This build supports syntax for class declarations, method declarations, attribute declarations and assignments and <br>
basic operations. Examples can be found in the update site repository.<br>
The meaning of the projects is the following:<br>
  -> org.eclipse.recommenders.snipeditor.releng: the release project. It contains the project pom file, describing <br>
  all the other projects.<br>
  -> org.eclipse.recommenders.snipeditor.updatesite: the update site for this project.<br>
  -> the rest of the projects are the XText projects. Note that this have not been modified, they are fresh projects.<br>
<br>
In order to open the projects in eclipse, simply clone the repo on the local computer and import the projects<br>
in eclipse. You will also need to generate the language artifacts.
<br>
<br>

In order to build this, follow the steps:<br>
 (1)clone the repo to the local computer<br>
 (2)open the command line<br>
 (3)go to ../org.eclipse.recommenders.snipeditor.releng<br>
 (4)run the command mvn clean install.<br>
<br>


Some using instructions:<br>
<p>
  (1) imports have the following format:<br>
    <br>
    ~ ${import(library.you.want.to.import.*)}<br>
    ~ ${import(library.you.want.to.import.specificClass)}<br>
    <br>
</p>
<p>
  (2) variables and attributes can be declared like in java(with a simple name) or using one 
      of the following expressions:<br>
     <br>
      ~ varName <br>
      ~ ${varName} <br>
      ~ ${freeName(varName)} <br>
      ~ ${newName(varName)} <br>
    <br>
    Each of the last three will be translated to ${varName}, so the variable / attribute will be accessed<br> 
    through this id (new ids can be introduced at any time if needed) <br>
</p>
<p>
  (3) only java types are supported! in order to use a type, you must explicitly import it(except for basic String, Integer, etc.).<br>
  Some quick fixes will be available to ease codding.<br>
  Types can be accessed in the following manner:<br>
      <br>
      ~ type_name <br>
      ~ ${elemType(type_name)} <br>
    <br>
  Both access the same type ( type_name ). Maybe somewhere in the future, abstract types that will<br> 
    be deduced from context will be availabe.(e.g.: ${some_Array_type})<br>
</p>
<p>
(4) the rest of the expressions are similar to java, except for the for loop expression which supports only <br>
  the iterator syntax, the other one will come in a future update.<br>
</p>  
<br>
An example:
<pre>
${import(org.eclipse.xtext.xbase.lib.*)}
${import(java.util.Locale.Builder)}

class c1{
  public void m1(){
		String[] a=new String [2];
		${elemType(Builder)} ${freeName(b)}=new Builder();
		${b}.addUnicodeLocaleAttribute("MyBuilder");
		System.out.print(a);
		if(a instanceof String[]){
			super.toString();
		}
		for(${s} : a){
			System.out.println(${s});
			${cursor}
		}
		while('${dollar}' == '$' ){
			System.out.println(a);
		}
	} 
}  
</pre>
=======

[1]https://github.com/stefanprisca/SnipEditor_UpdateSite
>>>>>>> 4ce47ddfa933575c35a555a9ead1083b71151625
