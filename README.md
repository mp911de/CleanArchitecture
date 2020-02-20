Sampler for Clean Architecture/Onion-Architecture [![Build Status](https://snap-ci.com/mp911de/CleanArchitecture/branch/master/build_image)](https://snap-ci.com/mp911de/CleanArchitecture/branch/master)
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fmp911de%2FCleanArchitecture.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2Fmp911de%2FCleanArchitecture?ref=badge_shield)
========================
Author: Mark Paluch<br/>
Technologies: CDI, JSF, JPA, EJB, JPA, JAX-RS<br/>
Summary: Example Application built using an Onion-Architecture that incorporates multiple technologies<br/>
Source: <https://github.com/mp911de/CleanArchitecture><br/>

More Information: <br/>
* https://www.paluch.biz/blog/80-clean-your-architecture-databases-the-web-and-service-interfaces-are-just-plugins.html
* https://www.paluch.biz/blog/83-clean-architecture-code-examples-for-an-onion-architecture.html

What is it?
-----------
This simple application consists of a few use cases. The purpose of the application is to show how to
apply clean architecture patterns in a Multi-Module Maven/Java environment.

It all starts with the data structures/entities/application model. These models are independent of business logic and delivery mechanisms. The models are specific to your domain, but not necessary specific to your application. They live within the `application-model` module. Business rules and use cases, the specific things your application does, reside within the `use-cases` module. They depend on the `application-model` and perhaps on external things that are represented by boundaries, located in `contracts`. Those boundaries are an agreement between the use case and the other side that provides a specific implementation. The `contracts` depend only on the `application-model`. No ORM entities or external-specific API/entities.
ORM, caching implementations, clients to external services implement a contract that is located in `external` and its sub-modules.

All parts are tied together by the delivery mechanism that
integrates the externals and connects the use cases by supplying dependencies to come the system to life.

If you need a different implementation for any external, so you can easily change that specific part without
affecting other parts of the system.

These patterns are verified by real life projects.

A word on Clean Architecture
----------------------------

As soon as you dig into the code, you'll notice comments on the one or other class. Subject of these comments is to help
to understand the structure and the different styles, which are possible.

You'll notice soon, there are many different styles and ways to approach the Clean Architecture style. There are use cases
which are built much more simple, e. g. without input/output boundaries and direct usage of dependency injection and
there are use-cases which implement input boundaries and use output boundaries. 

In the end it's up to you, how much you're willing to invest in your architecture. This is, however, only a variety of
examples to give you an impression, how to express Clean Architecture with Java.


What does it?
---------
The use cases are:

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

These use cases can be accessed by REST or Web UI (JSF) and are persisted using JPA within an in-memory H2 Database
(everything you need is included).

Requirements to run the App
-------------------

All you need to build this project is Java 6.0 (Java SDK 1.6) or better, Maven 3.0 or better.

The application this project produces is designed to be run on JBoss AS7, WildFly 8 or better.
You could easily change the delivery mechanism to a console application with only providing a new delivery mechanism
and a different approach how to wire the dependencies.


Build and Deploy the Quickstart
-------------------------

_NOTE: The following build command assumes you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line._

1. Open a command line and navigate to the root directory of this project.
2. Type this command to build and deploy the archive:

        mvn clean package wildfly:run

3. This will start a WildFly 10 instance and deploy `target/clean-architecture.war` to the newly started instance.
 

Access the application 
-------------------------

The application will be running at the following URL: <http://localhost:8080/clean-architecture/>.



## License
[![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fmp911de%2FCleanArchitecture.svg?type=large)](https://app.fossa.io/projects/git%2Bgithub.com%2Fmp911de%2FCleanArchitecture?ref=badge_large)