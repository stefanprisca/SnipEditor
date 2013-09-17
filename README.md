<<<<<<< HEAD
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
=======
SnipMatch
=========

To build this project, you first need to checkout and build the main Eclipse Code
Recommenders project once. This installs the necessary parent POMs and related artifacts
into your local Maven repository (~/.m2/repository).

   git clone http://git.eclipse.org/gitroot/recommenders/org.eclipse.recommenders.git
   mvn clean install -f org.eclipse.recommenders/pom.xml

Thereafter, you can build this project by simply running

   mvn clean install

in the project's root directory.
>>>>>>> 0ac2a109039d15c21cabe034c4b249fc5db7cf7a
