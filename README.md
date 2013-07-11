SnipEditor_MavenBuild
=====================
Working maven build and most of the JFace grammar. The grammar has some issues regarding the usage of Jvm Types and <br>
the editor has problems telling the difference between a method and an entity.<br>
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
<<<<<<< HEAD
 
 
=======

[1]https://github.com/stefanprisca/SnipEditor_UpdateSite
>>>>>>> 4ce47ddfa933575c35a555a9ead1083b71151625
