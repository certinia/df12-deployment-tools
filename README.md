# Deployment Tools

## Overview
* src, build.xml
    * A reference force.com application showing how the tools can be utilized
* lib
    * Contains the various jar files and Ant XML files that make up the tool
    * Also includes ant-salesforce.jar (v30.0), no need to copy this to your local Ant install!

## Update August 2014
- Added support in undeploy for Custom Actions
- Refactored the sample app and tools to avoid using relative paths and use ${basedir}

## Update June 2014
- Removes Case Escalations and Assignments components
- Does not attempt to remove Standard Applications
- Supports both standard DE orgs (from developer.salesforce.com) and Partner DE orgs
- 100% Ant native, removed requirement for custom Java class, ExecAnon
now implemented natively in Ant script
- Removed need for modified ant-salesforce.jar (PurgeOnDelete now
supported!)
- Resolved issue with deployment of some layouts (during undeploy phase) such as the Social layout
- Updated to use API v30.0

## Update July 2013
- Undeploy script update. New component types, deals with fieldsets and related list filters

## The reference example
The src and datasets directories and build.xml contains an example force.com application.

* **/src**
    * this directory holds the sample app used in to demonstrate the tools
* **/datasets**
    * this directory holds some samples of apex scripts used by the "Execute Anonymous" process.
* **/build.properties**
    * this file is where you will enter your salesforce user name and password
    * include your security token with the password, if your security settings require this
* **/build.xml**
    * this is a reference example of an ant build file that utilizes the tools, including cleaning an org!
    * If you already have a build script for your project, you can simply include the various xml files to add functionality to your build.
    * Note the four include file statements, these include the four basic ant targets covered in the session

To call the sample [build.xml](https://github.com/financialforcedev/df12-deployment-tools/blob/master/build.xml) provided, run the following command.

    ant RunEverything -Dsf.username=yourusername -Dsf.password=yourpasswordyourtoken
    
Or update the [build.properties](https://github.com/financialforcedev/df12-deployment-tools/blob/master/build.properties) file with your user name, password and token and just run...

    ant RunEverything

**NOTE:** This sample build script eventualy ends with test failures, this is intentional to demonstrate the RunTests target

### Using the build file (targets)
If you've included all for of the .xml files from the lib folder (as shown in the example build.xml) then you will have access to the following ant targets:

* **undeploy**       
    * used to remove all metadata from the org.
    * usage: ant undeploy
* **deploy**
    * used to deploy the application found in the "src" folder along side of the build.xml
    * usage: ant deploy
* **ExecAnon**
    * can be used to execute anonymous apex from the command line
    * usage: ant "-Dwhat=Account a = new Account();a.Name='test';insert a;" ExecAnon
    * usage: ant "-Dwhat=delete [SELECT Id FROM Object1__c];" ExecAnon
* **ExecAnonScript**
    * can be used to execute a anonymous apex script from the command line
    * usage: ant "-Dwhat=datasets\basic.txt" ExecAnonScript
    * Or create an ant target that maps to your dataset and excute that ant target (see the build.xml for 4 examples)
* **RunTests**
    * used to execute all unit tests and outputs the results to a file.
    * usage: ant RunTests

## lib
The lib folder contains all of the items required to use the deploy tools, including:
* Four Ant xml scripts to be included in your build file
* Various jar files needed to support some custom Ant tasks used by the scripts






