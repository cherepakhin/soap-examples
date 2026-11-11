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


Коротко о JAX-WS:

```text
JAX-WS (Java API for XML Web Services) — это прикладной программный интерфейс языка Java для создания веб-служб на основе XML. Технология входит в состав Jakarta EE (ранее Java EE) и представляет собой эволюцию предыдущего стандарта JAX-RPC. 
Цель JAX-WS — упростить процесс создания и использования веб-сервисов путём автоматизации большинства низкоуровневых задач. 
Ключевые компоненты архитектуры:
Endpoint — точка доступа, опубликованная сервером, доступная клиентам по URL;
Service — абстракция на стороне клиента, представляющая удалённый сервис;
Port — конкретная реализация сервиса;
WSDL — описание сервиса на языке XML, определяющее интерфейс;
SOAP — протокол обмена сообщениями, основанный на XML.
 
Технология JAX-WS — стандарт, описан в JSR 224. 
Принцип работы
Работа JAX-WS строится на концепции «контракта» — формализованного описания интерфейса сервиса, выраженного в WSDL-документе. Этот документ содержит полную информацию о доступных операциях, типах данных и протоколе взаимодействия. 
Процесс взаимодействия в JAX-WS: 
Клиент формирует запрос, который преобразуется в SOAP-сообщение и передаётся по сети.
Сервер принимает сообщение, преобразует его в Java-объекты, обрабатывает и формирует ответ, который также передаётся клиенту в виде SOAP-сообщения.
 
Особенности JAX-WS:
Возможность выбора между двумя путями разработки: «снизу вверх» (программист разрабатывает endpoint-классы сам) и «сверху вниз» (Java-классы генерируются по WSDL).
Поддержка стандартов WS-* (например, WS-Security, WS-ReliableMessaging).
 
Аннотации
JAX-WS использует аннотации, описанные в JSR 181, для отображения POJO-классов на WSDL. Некоторые из них: 
@WebService — указывает на то, что Java-класс (или интерфейс) является веб-службой;
@WebMethod — позволяет настроить, как будет отображаться метод класса на операцию веб-службы;
@WebParam — позволяет настроить, как будет отображаться конкретный параметр операции на WSDL-часть и XML-элемент;
@WebResult — позволяет настроить, как будет отображаться возвращаемое значение операции на WSDL-часть и XML-элемент;
@Oneway — указывает на то, что операция является односторонней, то есть не имеет выходных параметров;
@SOAPBinding — позволяет настроить, как будет отображаться веб-служба на протокол SOAP.
```