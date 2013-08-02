clean-architecture: Example Application built using an Onion-Architecture.
========================
Author: Mark Paluch
Level: Intermediate
Technologies: CDI, JSF, JPA, EJB, JPA, JAX-RS
Summary: Example Application built using an Onion-Architecture that incorporates multiple technologies
Source: <https://github.com/mp911de/CleanArchitecture>

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

This project does not meet highest clean code requirements, yet it is much cleaner than most applications in production. It's mainly intended to demonstrate how a clean architecture is built.

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

_NOTE: The following build command assumes you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line. _

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean package jboss-as:deploy

4. This will deploy `target/clean-architecture.war` to the running instance of the server.
 

Access the application 
---------------------

The application will be running at the following URL: <http://localhost:8080/clean-architecture/>.


Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn jboss-as:undeploy

Run the Quickstart in JBoss Developer Studio or Eclipse
-------------------------------------
You can also start the server and deploy the quickstarts from Eclipse using JBoss tools. 

Debug the Application
------------------------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following commands to pull them into your local repository. The IDE should then detect them.

    mvn dependency:sources
    mvn dependency:resolve -Dclassifier=javadoc
