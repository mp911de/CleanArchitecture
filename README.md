Build Status: [![Build Status](https://snap-ci.com/qyI_noSnlwCtc2tVxJUWfx1TfQql5Gl18GXHShyCtGQ/build_image)](https://snap-ci.com/projects/mp911de/CleanArchitecture/build_history)

Example Application built using an Onion-Architecture.
========================
Author: Mark Paluch<br/>
Level: Intermediate<br/>
Technologies: CDI, JSF, JPA, EJB, JPA, JAX-RS<br/>
Summary: Example Application built using an Onion-Architecture that incorporates multiple technologies<br/>
Source: <https://github.com/mp911de/CleanArchitecture><br/>

More Information: <br/>
https://www.paluch.biz/blog/80-clean-your-architecture-databases-the-web-and-service-interfaces-are-just-plugins.html <br/>
https://www.paluch.biz/blog/83-clean-architecture-code-examples-for-an-onion-architecture.html<br/>

What is it?
-----------
This is a simple application consisting of a few use cases

* CreateOrUpdateItem
* CreateOrUpdateUser
* ListItems
* ListOrders
* PlaceOrder
* PlaceOrderValidator

and a few business entities:

* User
* OrderItem
* Order
* Item

These usecases can be accessed by REST or Web UI (JSF) and are persisted using JPA within an in-memory H2 Database (everything you need is included).

This project demonstrates how to apply clean architecture patterns in a Multi-Module Maven/Java environment. These patterns
are verified by real life projects.

System requirements
-------------------

All you need to build this project is Java 6.0 (Java SDK 1.6) or better, Maven 3.0 or better.

The application this project produces is designed to be run on JBoss Enterprise Application Platform 6 or JBoss AS 7. 

Start JBoss Enterprise Application Platform 6 or JBoss AS 7 with the Web Profile
-------------------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat

 
Build and Deploy the Quickstart
-------------------------

_NOTE: The following build command assumes you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line._

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean package jboss-as:deploy

4. This will deploy `target/clean-architecture.war` to the running instance of the server.
 

Access the application 
-------------------------

The application will be running at the following URL: <http://localhost:8080/clean-architecture/>.


Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn jboss-as:undeploy

