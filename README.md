# DF12 Deployment Tools

## Overview
* DF12APP   
    * A reference force.com application showing how the tools can be utilized
* DF12Tools
    * A Java Project that utilizes the API and extends the deploy task
* lib
    * Contains the various java jar files, WSDLs, and ant XML files that make up the tool
* build.xml
    * An ant build file used to compile the WSDLs into a java jar file.



## DF12APP -- The reference example
The DF12APP directory contains an example force.com application.

* datasets
    * this directory holds some samples of apex scripts used by the "Execute Anonymous" process.
* build.properties
    * this file is where you will enter your salesforce user name and password
    * include your security token with the password, if your security settings require this
* build.xml
    * this is a reference example of an ant build file that utilizes the tools.  
    * If you already have a build script for your project, you can simply include the various xml files to add functionality to your build.
    * Note the four include file statements, these include the four basic ant targets covered in the session

### Using the build file (targets)
If you've included all for of the .xml files from the lib folder (as shown in the example build.xml) then you will have access to the following ant targets:

* undeploy       
    * used to remove all metadata from the org.
    * usage: ant undeploy
    * Note: This requires a customised ant-salesforce.jar, [vote](https://success.salesforce.com/ideaView?id=08730000000kqeFAAQ) now to have Salesforce support this properly.
* deploy
    * used to deploy the application found in the "src" folder along side of the build.xml
    * usage: ant deploy
* ExecAnon
    * can be used to execute anonymous apex from the command line
    * usage: ant "-Dwhat=Account a = new Account();a.Name='test';insert a;" ExecAnon
    * usage: ant "-Dwhat=delete [SELECT Id FROM Object1__c];" ExecAnon
* ExecAnonScript
    * can be used to execute a anonymous apex script from the command line
    * usage: ant "-Dwhat=datasets\basic.txt" ExecAnonScript
    * Or create an ant target that maps to your dataset and excute that ant target (see the build.xml for 4 examples)
* RunTests
    * used to execute all unit tests and outputs the results to a file.
    * usage: ant RunTests


## DF12Tools
The DF12Tools directory contains a java project that utilizes the partner and apex api (from the WSDLs) via the SFDC.jar
The project contains two classes (in two packages)
* ExecuteAnonymous.java
    * Uses a partner connection and the apex api to connect to the org, and execute anonymous apex.
* SFDCDeployPurge.java
    * This class extends the "DeployTask" of the force.com com ant migration tool to expose a setPurgeOnDelete option.  
    * The PurgeOnDelete option causes objects and fields to be completely deleted so they dont remain in the list of deleted objects/fields.

This project was exported to DF12Tools.jar which is included in the lib folder, and can be used as is, but is provided to allow you to enhance or extend it.

Note: This project depends on these 5 jars, depending on where you clone the repo, you'll need to import these jars to the project.
After you've "imported" the project into eclipse [file\import]
Edit the project properties, go to "java build path" --> Libraries tab --> Add External Jars and add them
* ant.jar  -- found in your ant installation's lib directory
* ant-salesforce.jar -- in the provided lib directory
* DF12.jar -- in the provided lib directory
* axis.jar -- in the provided lib directory
* jaxrpc.jar -- in the provided lib directory



## lib
The lib folder contains all of the items required to use the deploy tools, including:
* the 4 ant xml scripts to be included in your build file
* the 4 WSDL files (which you can update by downloading copies from your own org.  This is especially important for the enterprise.wsdl)
* the various java jar files needed to support compiling the WSDLs, and for hte DF12Tools project






