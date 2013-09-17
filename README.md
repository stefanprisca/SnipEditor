<<<<<<< HEAD
SnipMatch Editor and teamplates project
=====================

This repository contains the snippet editor and the grammar that stands behind it<br>

The meaning of the projects is the following:<br>

<p>
-> o.e.r.snipmatch.feature: feature project for the snipmatch editor and view<br>
-> o.e.r.snipmatch.rcp.repository: the updatesite project for o.e.r.snipmatch.rcp. Because of gitignore file,<br>
this repository can be found here [1]<br>
->o.e.r.snipmatch.rcp: the snipmatch project, contains the multi page editor, the view and other necessary packages<br>
like content assist, quick fixes, etc.<br>
->o.e.r.templates : is the project that contains the grammar, code compiler, type computer, and other core elements<br>
-> o.e.r.templates.rcp: is a basic ui for o.e.r.templates. it contains a simple editor that allows manipulation <br>
of .cSnip files. (file extension used for code snippets)<br>
->o.e.r.templates.tests: the test project for o.e.r.templates.<br>

</p>

<h1>Installation and build instructions:</h1>
In order to install it as an eclipse update site, follow the next steps:<br>
<p>
1) clone/download the updatesite repository<br>
2) add the repository folder as local update site in eclipse<br>
3) confirm.<br>
</p>

In order to build it with maven, follow the next steps:<br>
<p>
1)clone the repository<br>
2) cd to the cloned repository location<br>
3) run the following command in the main folder of the project:<br>
<p>
$ set MAVEN_OPTS=-Xmx512m -XX:MaxPermSize=128m<br>
$ mvn clean install -Dlicense.failIfMissing=false<br>
</p>
</p>


<h2>How to use:</h2>

The plug-in from the update site contains only the features for snipmatch<br> project. This means the multipage editor, snippet view and a preference page.<br>

First of all, a set of snippets is needed. This can be found here[1]. Clone this repository on the local machine.<br>
After the installation, and once the snippets are cloned, follow the next steps to properly set up and use this plug-in:<br>
<p>
<b>The Preference Page set-up:<b/>
<p>
1) From the eclipse IDE, go to : Window->Preferences->SnipMatch Preferences<br>
2) Set the path to the local code snippets in the Local Snippets Directory.<br>
3) Click the Generate Index File button<br>
4) Select the index file that was generated (this is found in the same folder as the snippets)<br>
</p>
</p>
<br>
<p>
<b>The Snippets view:</b>
<br>
This contains all the snippets that are on the path selected in the preference <br>page. In order to use it:<br>
<p>
1) Go to Window -> Show View -> Other -> Other -> Snippets View and open it<br>
2) If the snippets do not appear in the list, hit the refresh button.<br>
3) double click on any snippet and the editor will pop-up.
</p>
</p>
<br>
<p><b>The Editor:</b><br>
This a a multi-page editor that allows to modify both the snippet code<br>
as well as the snippets metadata (name, search phrases, etc).<br>
It supports the jFace template language syntax [3], and makes use of <br>
a bunch of XText tools to allow an easy edition of code snippets.<br>

An example of a code snippet that includes some template syntax is <br>
the following:<br>
<code>
${imp:import (org.eclipse.swt.widgets.Button)}<br>
${array_type} arr = new ${array_type}();<br>
arr.get(0);<br>
${iterable_type} iter = new ${iterable_type}();<br>
iter = ${iterable}<br>
Object [] p = ${array}.toArray();<br>
System.out.println(${iterable});<br>
${collection}.isEmpty();<br>
${type:newType(array)} elemnt;<br>
p.add(${arr:var(java.lang.reflect.AccessibleObject)});<br>
${type:elemType(java.lang.String)} elem = new <br><br>${type:elemType(java.lang.Integer)}(new ${type:newType(java.lang.String)}("This is my name"))<br>
else{<br>
 	this.main();<br>
 }<br>
for(${iterator}:${iterable}){<br>
<br>
}<br>
<br>
 catch(Exception e){<br>
 	<br>
 }<br>
</code>
</p>

[1]  https://github.com/stefanprisca/SnipMatch_UpdateSite<br>
[2] https://git.eclipse.org/c/recommenders.incubator/org.eclipse.recommenders.snipmatch.snippets.git/tree/

[3] http://help.eclipse.org/kepler/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Fconcepts%2Fconcept-template-variables.htm
=======
SnipEditor_MavenBuild
=====================
Working maven build and most of the JFace grammar. <br>
The update site can be found here[1]. In order to use, please follow the instructions provided<br>
in the update site readme file. It does not work directly from eclipse IDE(yet).<br>  
This build supports syntax for class declarations, method declarations, attribute declarations and assignments and <br>
basic operations. Examples can be found in the update site repository.<br>
\<br>
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


<h1>The preference page and the view</h1>
<p>
	The prefetence page can be used to set the locations of the snippets. This can be accessed from<br>
Window -> Preferences -> SnipMatch Preferences.<br>
	
	In order to set it up:<br>
	<br>
	(1) Set the location of the local snippets in the <i>Local Snippets Directory</i> path location <br>
	(2) Click the <i>Generate Index File</i> button.<br>
	(3) Chose the index file in its alocated path.<br>
	<br>
	After the preference page is set up, you can access the local snippets through the snippets view:<br>
	<br>
	(1) Go to <i>Window -> Show View -> Other -> Other -> Snippets View </i> and open it.<br>
	(2) If the snippets do not appear in the list, press the refresh button.<br>
	(3) The snippets ahould be visible now, so in order to edit them, just press double click on the one <br>
	to be edited :) <br>
	<br>
</p>

<h2>The Editor</h2>
<p>
	In order to allow all functionalities of the editor, the files are copied in the local workspace.<br>
	Therefor, a new project will be created in the workpsace with the snippets. Here, .cSnip files <br>
	will be created from the code in the .json files.<br>
</p>
<p>
Some instructions on how to use the editor:<br>
<br>
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
      ~ ${freeName(varName)} / ${varName:freeName(varName)} <br>
      ~ ${newName(varName)} / ${varName:newName(varName)} <br>
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
      ~ ${elemType(type_name)} / ${typeName:elemType(type_name)} <br>
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
>>>>>>> master
