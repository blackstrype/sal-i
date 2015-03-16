# Setting up the development environment #

## IDE with GWT ##

### 1.) Google Web Toolkit ###
For installing the Google Web Toolkit (GWT) download version 1.5 from http://code.google.com/webtoolkit/download.html and extract the archive.
(For more information see http://code.google.com/webtoolkit/gettingstarted.html)

### 2.) Eclipse ###
As IDE we have chosen Eclipse Ganymede (Version 3.4) with which can be downloaded from the Eclipse homepage (http://www.eclipse.org/downloads/).

### 3.) Eclipse WTP ###
For using GWT within Eclipse we first need the Web Tools Platform extension. It can be installed by using the Eclipse Software Updates (Help -> Software Updates...). In the "Available Software"-tab add the site "http://download.eclipse.org/webtools/updates/". After unfolding the child-nodes, chose "Web Tools Platform (WTP) 3.0.1" and press install.

### 4.) Cypal Studio ###
The second extension we need for using GWT within Eclipse is the Cypal Studio Plugin. First download version 1.0 from http://code.google.com/p/cypal-studio/downloads/list and extract the files into the "Plugins" directory of Eclipse. Start Eclipse and set the GWT directory under "Window -> Preferences -> Cypal Studio -> GWT Home".

Now Eclipse is ready for use with GWT.

### Tutorials ###
Here is list of tutorials referring to the installation and getting started with GWT:
  * GWT Homepage: http://code.google.com/webtoolkit/
  * Cypal Studio documentation: http://www.cypal.in/studiodocs
  * Introduction into GWT: http://www.ibm.com/developerworks/opensource/library/os-eclipse-ajaxcypal/

## Subversion Client ##
We are using the subversive plugin for Eclipse. To install go to "Help -> Software Updates... -> Available Software -> Manage Sites". Uncheck the  Ganymede update site and add http://download.eclipse.org/technology/subversive/0.7/update-site/ . Now chose after unfolding the "Subversive SVN Team Provider (Incubation)" and "Subversive SVN Team Provider Localization" and install.
Furthermore add the site "http://www.polarion.org/projects/subversive/download/eclipse/2.0/update-site/". Chose "Subversive SVN Connectors", "SVNKit 1.1.7 Implementation (Optional)" and "SVNKit 1.2.0 [Beta](Beta.md) Implementation (Optional)" and install.
After restarting Eclipse change the perspective to "SVN Repository Exploring" (Window -> Open Perspective -> Other...). Press the "New Repository Location" and enter the URL (https://sal-i.googlecode.com/svn/) and your username. The password is not your Google account password but it is generated by Google Code. You can find your password when you sign into our Google Code page and go to the "Source" tab. Press Finish and you can checkout our project.

## Geronimo Application Server ##
We are using the Apache Geronimo Application Server for out server side of SAL-I. Follow the [instructions provided here](http://cwiki.apache.org/GMOxDOC21/quick-start-fast-and-easy-development.html) to install it and integrate it into Eclipse. I was using the manual installation but the others should work as well. Geronimo can be [downloaded here](http://www.apache.org/dyn/closer.cgi/geronimo/2.1.2/geronimo-tomcat6-javaee5-2.1.2-bin.tar.gz).

## Configure Eclipse ##
Set up a Java EE Application project in Eclipse. Create a EJB and a WEB project in your Java EE Application. The Eclipse wizard will guide you through that process. For detailed information about Java EE Applications in Eclipse see [the Eclipse documentation](http://help.eclipse.org/help33/index.jsp?topic=/org.eclipse.wst.doc.user/topics/overview.html). The wizard should also guide you through the set up of a new server runtime environment. Create a Apache Geronimo v2.1 runtime environment and provide the application server install directory. Also create a new dynamic WEB project and add it to your Java EE application. You can do that either in Eclipse or with the tools provided by GWT. The advantage of the GWT tool is that it creates a some useful build and deploy scripts. See [GWT documentation](http://code.google.com/docreader/#p=google-web-toolkit-doc-1-5&s=google-web-toolkit-doc-1-5&t=DevGuideCommandLineTools) for details.
Download all the source files from our repository (.../tunk/src/-project-) in the appropriate folders of the different projects. (Check if the Servlets are configured in the WEB project; if not use the import function of Eclipse).
The last step is to add the SALClientAPI to the classpath of the server, otherwise RMI will throw a ClassNotFound Exception. To do that open the Runtime Configuration of the server, go to the classpath section and add the SALCLientAPI.jar file to the classpath.

That's it, have fun.