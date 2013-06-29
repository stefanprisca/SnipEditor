SnipEditor_MavenBuild
=====================
This is the first try at a maven build. It includes an update site from which the user can simply install the <br>
snippet editor.<br>
The meaning of the projects is teh following:<br>
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
 I encountered the following error when building the project:<br>
 
 
 Class<SnipDSLProposalProvider> cannot be resolved to a type <br>
---------- <br>
12 problems (12 errors)<br>
[INFO] ------------------------------------------------------------------------<br>
[INFO] Reactor Summary:<br>
[INFO]<br>
[INFO] parent ............................................ SUCCESS [1.705s]<br>
[INFO] MyDSL core language services ...................... SUCCESS [4.694s]<br>
[INFO] MyDSL core language services ...................... FAILURE [1.324s]<br>
[INFO] MyDSL Example tests ............................... SKIPPED<br>
[INFO] MyDSL core language services ...................... SKIPPED<br>
[INFO] MyDSL Example Update Site ......................... SKIPPED<br>
[INFO] ------------------------------------------------------------------------<br>
[INFO] BUILD FAILURE<br>
[INFO] ------------------------------------------------------------------------<br>
[INFO] Total time: 38.632s<br>
[INFO] Finished at: Sat Jun 29 09:41:29 EEST 2013<br>
[INFO] Final Memory: 89M/432M<br>
[INFO] ------------------------------------------------------------------------<br>
[ERROR] Failed to execute goal org.eclipse.tycho:tycho-compiler-plugin:0.15.0:co<br>
mpile (default-compile) on project org.eclipse.recommenders.snipeditor.ui: Compi<br>
lation failure: Compilation failure:<br>
[ERROR] D:\work\workspace\SnipMatchTextEditor\SnipEditor_MavenBuild\org.eclipse.<br>
recommenders.snipeditor.ui\src-gen\org\eclipse\recommenders\snipeditor\ui\Abstra<br>
ctSnipDSLUiModule.java:[83,0]<br>
[ERROR] return org.eclipse.recommenders.snipeditor.ui.labeling.SnipDSLLabelProvi<br>
der.class;<br>
[ERROR] ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<br>
[ERROR] org.eclipse.recommenders.snipeditor.ui.labeling cannot be resolved to a<br>
type<br>
[ERROR] D:\work\workspace\SnipMatchTextEditor\SnipEditor_MavenBuild\org.eclipse.<br>
recommenders.snipeditor.ui\src-gen\org\eclipse\recommenders\snipeditor\ui\Abstra<br>
ctSnipDSLUiModule.java:[83,0]<br>
[ERROR] return org.eclipse.recommenders.snipeditor.ui.labeling.SnipDSLLabelProvi<br>
der.class;<br>
[ERROR] ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<br>
^^<br>
[ERROR] Class<SnipDSLLabelProvider> cannot be resolved to a type<br>
[ERROR] D:\work\workspace\SnipMatchTextEditor\SnipEditor_MavenBuild\org.eclipse.<br>
recommenders.snipeditor.ui\src-gen\org\eclipse\recommenders\snipeditor\ui\Abstra<br>
ctSnipDSLUiModule.java:[88,0]<br>
[ERROR] binder.bind(org.eclipse.jface.viewers.ILabelProvider.class).annotatedWit<br>
h(org.eclipse.xtext.ui.resource.ResourceServiceDescriptionLabelProvider.class).t<br>
o(org.eclipse.recommenders.snipeditor.ui.labeling.SnipDSLDescriptionLabelProvide<br>
r.class);<br>
[ERROR] ^^<br>
[ERROR] The method to(Class<? extends ILabelProvider>) in the type LinkedBinding<br>
Builder<ILabelProvider> is not applicable for the arguments (Class<SnipDSLDescri<br>
ptionLabelProvider>)<br>
[ERROR] D:\work\workspace\SnipMatchTextEditor\SnipEditor_MavenBuild\org.eclipse.<br>
recommenders.snipeditor.ui\src-gen\org\eclipse\recommenders\snipeditor\ui\Abstra<br>
ctSnipDSLUiModule.java:[88,0]<br>
[ERROR] binder.bind(org.eclipse.jface.viewers.ILabelProvider.class).annotatedWit<br>
h(org.eclipse.xtext.ui.resource.ResourceServiceDescriptionLabelProvider.class).t<br>
o(org.eclipse.recommenders.snipeditor.ui.labeling.SnipDSLDescriptionLabelProvide<br>
r.class);<br>
[ERROR] ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<br>
[ERROR] org.eclipse.recommenders.snipeditor.ui.labeling cannot be resolved to a<br>
type<br>
[ERROR] D:\work\workspace\SnipMatchTextEditor\SnipEditor_MavenBuild\org.eclipse.<br>
recommenders.snipeditor.ui\src-gen\org\eclipse\recommenders\snipeditor\ui\Abstra<br>
ctSnipDSLUiModule.java:[93,0]<br>
[ERROR] return org.eclipse.recommenders.snipeditor.ui.outline.SnipDSLOutlineTree<br>
Provider.class;<br>
[ERROR] ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<br>
[ERROR] org.eclipse.recommenders.snipeditor.ui.outline cannot be resolved to a t<br>
ype<br>
[ERROR] D:\work\workspace\SnipMatchTextEditor\SnipEditor_MavenBuild\org.eclipse.<br>
recommenders.snipeditor.ui\src-gen\org\eclipse\recommenders\snipeditor\ui\Abstra<br>
ctSnipDSLUiModule.java:[93,0]<br>
[ERROR] return org.eclipse.recommenders.snipeditor.ui.outline.SnipDSLOutlineTree<br>
Provider.class;<br>
[ERROR] ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<br>
^^^^^^^<br>
[ERROR] Class<SnipDSLOutlineTreeProvider> cannot be resolved to a type<br>
[ERROR] D:\work\workspace\SnipMatchTextEditor\SnipEditor_MavenBuild\org.eclipse.<br>
recommenders.snipeditor.ui\src-gen\org\eclipse\recommenders\snipeditor\ui\Abstra<br>
ctSnipDSLUiModule.java:[98,0]<br>
[ERROR] return org.eclipse.recommenders.snipeditor.ui.outline.SnipDSLOutlineTree<br>
Provider.class;<br>
[ERROR] ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<br>
[ERROR] org.eclipse.recommenders.snipeditor.ui.outline cannot be resolved to a t<br>
ype<br>
[ERROR] D:\work\workspace\SnipMatchTextEditor\SnipEditor_MavenBuild\org.eclipse.<br>
recommenders.snipeditor.ui\src-gen\org\eclipse\recommenders\snipeditor\ui\Abstra<br>
ctSnipDSLUiModule.java:[98,0]<br>
[ERROR] return org.eclipse.recommenders.snipeditor.ui.outline.SnipDSLOutlineTree<br>
Provider.class;<br>
[ERROR] ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<br>
^^^^^^^<br>
[ERROR] Class<SnipDSLOutlineTreeProvider> cannot be resolved to a type<br>
[ERROR] D:\work\workspace\SnipMatchTextEditor\SnipEditor_MavenBuild\org.eclipse.<br>
recommenders.snipeditor.ui\src-gen\org\eclipse\recommenders\snipeditor\ui\Abstra<br>
ctSnipDSLUiModule.java:[103,0]<br>
[ERROR] return org.eclipse.recommenders.snipeditor.ui.quickfix.SnipDSLQuickfixPr<br>
ovider.class;<br>
[ERROR] ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<br>
[ERROR] org.eclipse.recommenders.snipeditor.ui.quickfix cannot be resolved to a<br>
type<br>
[ERROR] D:\work\workspace\SnipMatchTextEditor\SnipEditor_MavenBuild\org.eclipse.<br>
recommenders.snipeditor.ui\src-gen\org\eclipse\recommenders\snipeditor\ui\Abstra<br>
ctSnipDSLUiModule.java:[103,0]<br>
[ERROR] return org.eclipse.recommenders.snipeditor.ui.quickfix.SnipDSLQuickfixPr<br>
ovider.class;<br>
[ERROR] ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<br>
^^^^^<br>
[ERROR] Class<SnipDSLQuickfixProvider> cannot be resolved to a type<br>
[ERROR] D:\work\workspace\SnipMatchTextEditor\SnipEditor_MavenBuild\org.eclipse.<br>
recommenders.snipeditor.ui\src-gen\org\eclipse\recommenders\snipeditor\ui\Abstra<br>
ctSnipDSLUiModule.java:[108,0]<br>
[ERROR] return org.eclipse.recommenders.snipeditor.ui.contentassist.SnipDSLPropo<br>
salProvider.class;<br>
[ERROR] ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<br>
^^^^<br>
[ERROR] org.eclipse.recommenders.snipeditor.ui.contentassist.SnipDSLProposalProv<br>
ider cannot be resolved to a type<br>
[ERROR] D:\work\workspace\SnipMatchTextEditor\SnipEditor_MavenBuild\org.eclipse.<br>
recommenders.snipeditor.ui\src-gen\org\eclipse\recommenders\snipeditor\ui\Abstra<br>
ctSnipDSLUiModule.java:[108,0]<br>
[ERROR] return org.eclipse.recommenders.snipeditor.ui.contentassist.SnipDSLPropo<br>
salProvider.class;<br>
[ERROR] ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^<br>
^^^^^^^^^^<br>
[ERROR] Class<SnipDSLProposalProvider> cannot be resolved to a type<br>
[ERROR] 12 problems (12 errors)<br>
[ERROR] -> [Help 1]<br>
org.apache.maven.lifecycle.LifecycleExecutionException: Failed to execute goal o<br>
rg.eclipse.tycho:tycho-compiler-plugin:0.15.0:compile (default-compile) on proje<br>
ct org.eclipse.recommenders.snipeditor.ui: Compilation failure<br>
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor<br>
.java:213)<br>
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor<br>
.java:153)<br>
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor<br>
.java:145)<br>
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProje<br>
ct(LifecycleModuleBuilder.java:84)<br>
        at org.apache.maven.lifecycle.internal.LifecycleModuleBuilder.buildProje<br>
ct(LifecycleModuleBuilder.java:59)<br>
        at org.apache.maven.lifecycle.internal.LifecycleStarter.singleThreadedBu<br>
ild(LifecycleStarter.java:183)<br>
        at org.apache.maven.lifecycle.internal.LifecycleStarter.execute(Lifecycl<br>
eStarter.java:161)<br>
        at org.apache.maven.DefaultMaven.doExecute(DefaultMaven.java:320)<br>
        at org.apache.maven.DefaultMaven.execute(DefaultMaven.java:156)<br>
        at org.apache.maven.cli.MavenCli.execute(MavenCli.java:537)<br>
        at org.apache.maven.cli.MavenCli.doMain(MavenCli.java:196)<br>
        at org.apache.maven.cli.MavenCli.main(MavenCli.java:141)<br>
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)<br>
        at sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)<br>
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)<br>
        at java.lang.reflect.Method.invoke(Unknown Source)<br>
        at org.codehaus.plexus.classworlds.launcher.Launcher.launchEnhanced(Laun<br>
cher.java:290)<br>
        at org.codehaus.plexus.classworlds.launcher.Launcher.launch(Launcher.jav<br>
a:230)<br>
        at org.codehaus.plexus.classworlds.launcher.Launcher.mainWithExitCode(La<br>
uncher.java:409)<br>
        at org.codehaus.plexus.classworlds.launcher.Launcher.main(Launcher.java:<br>
352)<br>
Caused by: copied.org.apache.maven.plugin.CompilationFailureException: Compilati<br>
on failure<br>
        at copied.org.apache.maven.plugin.AbstractCompilerMojo.execute(AbstractC<br>
ompilerMojo.java:418)<br>
        at org.eclipse.tycho.compiler.AbstractOsgiCompilerMojo.execute(AbstractO<br>
sgiCompilerMojo.java:179)<br>
        at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo(Default<br>
BuildPluginManager.java:101)<br>
        at org.apache.maven.lifecycle.internal.MojoExecutor.execute(MojoExecutor<br>
.java:209)<br>
        ... 19 more<br>
[ERROR]<br>
[ERROR]<br>
[ERROR] For more information about the errors and possible solutions, please rea<br>
d the following articles:<br>
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureExc<br>
eption<br>
[ERROR]<br>
[ERROR] After correcting the problems, you can resume the build with the command<br>
<br>
[ERROR]   mvn <goals> -rf :org.eclipse.recommenders.snipeditor.ui<br>
D:\work\workspace\SnipMatchTextEditor\SnipEditor_MavenBuild\org.eclipse.recommen<br>
ders.snipeditor.releng><br>
