Java First demo using jax-ws APIs and jsr-181
=============================================

This demo illustrates how to develop a service use the "code first"
approach using the JAX-WS APIs.

Building and running the demo using Maven
---------------------------------------
From the base directory of this sample (i.e., where this README file is
located), the pom.xml file is used to build and run the demo. 

Used Java 8:

```shell
export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
```

Using either UNIX or Windows:

  mvn clean install   (builds the demo and creates a WAR file for optional Tomcat deployment)
  mvn -Pserver  (from one command line window -- only if using a non-WAR standalone service)
  mvn -Pclient  (from a second command line window)

Client protocol:

````text
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running demo.hw.server.HelloWorldImplTest
sayHiToUser called
sayHiToUser called
getUsers called
sayHi called
sayHiToUser called
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.132 sec

Results :

Tests run: 3, Failures: 0, Errors: 0, Skipped: 0

[INFO] 
[INFO] --- exec-maven-plugin:3.6.3:java (default) @ soap-apache-cxf-jax-ws ---
Nov 11, 2026 5:28:23 PM org.apache.cxf.wsdl.service.factory.ReflectionServiceFactoryBean buildServiceFromClass
INFO: Creating Service {http://server.hw.demo/}HelloWorld from class demo.hw.server.HelloWorldService
Nov 11, 2026 5:28:24 PM org.apache.cxf.wsdl.service.factory.ReflectionServiceFactoryBean buildServiceFromClass
INFO: Creating Service {http://server.hw.demo/}HelloWorld from class demo.hw.server.HelloWorldService
Hello World
Hello World
Hello Galaxy
Hello Universe

Users: 
  1: World
  2: Galaxy
  3: Universe
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running demo.hw.server.HelloWorldImplTest
sayHiToUser called
sayHiToUser called
getUsers called
sayHi called
sayHiToUser called
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.132 sec

Results :

Tests run: 3, Failures: 0, Errors: 0, Skipped: 0

[INFO] 
[INFO] --- exec-maven-plugin:3.6.3:java (default) @ soap-apache-cxf-jax-ws ---
Nov 11, 2026 5:28:23 PM org.apache.cxf.wsdl.service.factory.ReflectionServiceFactoryBean buildServiceFromClass
INFO: Creating Service {http://server.hw.demo/}HelloWorld from class demo.hw.server.HelloWorldService
Nov 11, 2026 5:28:24 PM org.apache.cxf.wsdl.service.factory.ReflectionServiceFactoryBean buildServiceFromClass
INFO: Creating Service {http://server.hw.demo/}HelloWorld from class demo.hw.server.HelloWorldService
Hello World
Hello World
Hello Galaxy
Hello Universe

Users: 
  1: World
  2: Galaxy
  3: Universe
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
````

To remove the code generated from the WSDL file and the .class
files, run "mvn clean".


Alternative: WAR deployment of service to Tomcat (either embedded or standalone)
---------------------------------------------------------------------------

1.) Update the endpointAddress value in the client.Client class to the WAR-hosted 
value, for running this sample on your local machine most likely:
"http://localhost:8080/java_first_jaxws/services/hello_world".

2.) Standalone Tomcat: Manually copy the generated WAR file to the Tomcat webapps folder, 
or, if you have Maven and Tomcat set up to use the Tomcat Maven Plugin 
(http://tomcat.apache.org/maven-plugin-2.2/tomcat7-maven-plugin/index.html) you can
use the mvn tomcat7:redeploy command instead.  

For embedded Tomcat 7: Just run mvn tomcat7:run-war from the project base folder.
For embedded Tomcat 8: Just run mvn cargo:run from the project base folder.

Prior to running the client (mvn -Pclient) it would be good to confirm the 
generated WSDL can be seen from a web browser at:
http://localhost:8080/java_first_jaxws/services/hello_world?wsdl
