SnipEditor_MavenBuild
=====================
This is the first try at a maven build. It includes an update site from which the user can simply install the <br>
snippet editor.<br>
The meaning of the projects is the following:<br>
  -> org.eclipse.recommenders.snipeditor.releng: the release project. It contains the project pom file, describing <br>
  all the other projects.<br>
  -> org.eclipse.recommenders.snipeditor.updatesite: the update site for this project.<br>
  -> the rest of the projects are the XText projects. Note that this have not been modified, they are fresh projects.<br>
<br>
In order to open the projects in eclipse, simply clone the repo on the local computer and import the projects<br>
in eclipse.
<br>
<br>

In order to build this, follow the steps:<br>
 (1)clone the repo to the local computer<br>
 (2)open the command line<br>
 (3)go to ../org.eclipse.recommenders.snipeditor.releng<br>
 (4)run the command mvn clean install.<br>
<br>
 
 
