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
