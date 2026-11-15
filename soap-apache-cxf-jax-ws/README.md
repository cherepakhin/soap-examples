Java First demo using jax-ws APIs and jsr-181
=============================================

This demo illustrates how to develop a service use the "code first"
approach using the JAX-WS APIs.

Building and running the demo using Maven
---------------------------------------
From the base directory of this sample (i.e., where this README file is
located), the pom.xml file is used to build and run the demo. 

__Used Java 8 (!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!) (don't use 11):__
(javax.xml.ws.*) удалён из стандартной библиотеки.

Gigacod: "Итоговый набор проблем и решений:

JAVA_HOME Java 11 — в Java 11+ JAX-WS API (javax.xml.ws.*) удалён из стандартной библиотеки.
→ Исправил на JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

Maven-профиль убивает фоновый сервер — mvn -Pserver & завершается сразу после запуска main-класса, не дожидаясь Thread.sleep().
→ Запускаю сервер напрямую: $JAVA_HOME/bin/java -cp "$CP" demo.hw.server.Server

Неправильный QName в Service.create() — первый параметр QName должен быть WSDL-namespace (http://server.hw.demo/), а не Java-пакетом.
→ Исправил namespace

NPE в URLConnectionHTTPConduit.createConnection() — стандартный JAX-WS Service.create() некорректно работает с CXF при ручном создании Service.
→ Заменил на CXF JaxWsProxyFactoryBean (как в рабочем примере soap-apache-cxf-pojo)
"

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

### Проведение теста

Запустить сервер:

````shell
./run_server.sh
````
Ари запуске будет выведено:

```text
...
Server ready...
========================================================
```

Сервер будет принимать запросы по адресу:  http://localhost:9000/helloWorld

Запустить клиент:

````shell
./run_client.sh
````

В консоли клиента будет выведено:
````text
Using Java: openjdk version "1.8.0_504"
Starting server...
Server PID: 38593
Server is ready on port 9000
Running client...
Nov 15, 2026 1:52:13 PM org.apache.cxf.wsdl.service.factory.ReflectionServiceFactoryBean buildServiceFromClass
INFO: Creating Service {http://server.hw.demo/}HelloWorldServiceService from class demo.hw.server.HelloWorldService
Hello World
Hello World
Hello Galaxy
Hello Universe

Users: 
  1: World
  2: Galaxy
  3: Universe
````

В консоли сервера будет выведено: 

````text
Nov 15, 2026 1:52:15 PM org.apache.cxf.ext.logging.slf4j.Slf4jEventSender send
INFO: REQ_IN
    Address: http://127.0.0.1:9000/helloWorld
    HttpMethod: POST
    Content-Type: text/xml; charset=UTF-8
    ExchangeId: f623bc4d-f713-4d7a-9b34-cb17519713be
    ServiceName: HelloWorldService
    PortName: HelloWorldServiceImplPort
    PortTypeName: HelloWorldService
    Headers: {SOAPAction="", Accept=*/*, Cache-Control=no-cache, User-Agent=Apache-CXF/3.3.2, connection=keep-alive, content-type=text/xml; charset=UTF-8, Host=127.0.0.1:9000, Pragma=no-cache, Content-Length=185}
    Payload: <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns2:sayHi xmlns:ns2="http://server.hw.demo/"><arg0>World</arg0></ns2:sayHi></soap:Body></soap:Envelope>

sayHi called
Nov 15, 2026 1:52:15 PM org.apache.cxf.ext.logging.slf4j.Slf4jEventSender send
INFO: RESP_OUT
    Content-Type: text/xml
    ResponseCode: 200
    ExchangeId: f623bc4d-f713-4d7a-9b34-cb17519713be
    ServiceName: HelloWorldService
    PortName: HelloWorldServiceImplPort
    PortTypeName: HelloWorldService
    Headers: {}
    Payload: <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns2:sayHiResponse xmlns:ns2="http://server.hw.demo/"><return>Hello World</return></ns2:sayHiResponse></soap:Body></soap:Envelope>

Nov 15, 2026 1:52:15 PM org.apache.cxf.ext.logging.slf4j.Slf4jEventSender send
INFO: REQ_IN
    Address: http://127.0.0.1:9000/helloWorld
    HttpMethod: POST
    Content-Type: text/xml; charset=UTF-8
    ExchangeId: bd81d1a1-ed61-4278-b256-19ac9a981179
    ServiceName: HelloWorldService
    PortName: HelloWorldServiceImplPort
    PortTypeName: HelloWorldService
    Headers: {SOAPAction="", Accept=*/*, Cache-Control=no-cache, User-Agent=Apache-CXF/3.3.2, connection=keep-alive, content-type=text/xml; charset=UTF-8, Host=127.0.0.1:9000, Pragma=no-cache, Content-Length=210}
    Payload: <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns2:sayHiToUser xmlns:ns2="http://server.hw.demo/"><arg0><name>World</name></arg0></ns2:sayHiToUser></soap:Body></soap:Envelope>

sayHiToUser called
Nov 15, 2026 1:52:15 PM org.apache.cxf.ext.logging.slf4j.Slf4jEventSender send
INFO: RESP_OUT
    Content-Type: text/xml
    ResponseCode: 200
    ExchangeId: bd81d1a1-ed61-4278-b256-19ac9a981179
    ServiceName: HelloWorldService
    PortName: HelloWorldServiceImplPort
    PortTypeName: HelloWorldService
    Headers: {}
    Payload: <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns2:sayHiToUserResponse xmlns:ns2="http://server.hw.demo/"><return>Hello World</return></ns2:sayHiToUserResponse></soap:Body></soap:Envelope>

Nov 15, 2026 1:52:15 PM org.apache.cxf.ext.logging.slf4j.Slf4jEventSender send
INFO: REQ_IN
    Address: http://127.0.0.1:9000/helloWorld
    HttpMethod: POST
    Content-Type: text/xml; charset=UTF-8
    ExchangeId: ceae4756-e0cb-4900-b6bb-f127b19c0c7e
    ServiceName: HelloWorldService
    PortName: HelloWorldServiceImplPort
    PortTypeName: HelloWorldService
    Headers: {SOAPAction="", Accept=*/*, Cache-Control=no-cache, User-Agent=Apache-CXF/3.3.2, connection=keep-alive, content-type=text/xml; charset=UTF-8, Host=127.0.0.1:9000, Pragma=no-cache, Content-Length=211}
    Payload: <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns2:sayHiToUser xmlns:ns2="http://server.hw.demo/"><arg0><name>Galaxy</name></arg0></ns2:sayHiToUser></soap:Body></soap:Envelope>

sayHiToUser called
Nov 15, 2026 1:52:15 PM org.apache.cxf.ext.logging.slf4j.Slf4jEventSender send
INFO: RESP_OUT
    Content-Type: text/xml
    ResponseCode: 200
    ExchangeId: ceae4756-e0cb-4900-b6bb-f127b19c0c7e
    ServiceName: HelloWorldService
    PortName: HelloWorldServiceImplPort
    PortTypeName: HelloWorldService
    Headers: {}
    Payload: <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns2:sayHiToUserResponse xmlns:ns2="http://server.hw.demo/"><return>Hello Galaxy</return></ns2:sayHiToUserResponse></soap:Body></soap:Envelope>

Nov 15, 2026 1:52:15 PM org.apache.cxf.ext.logging.slf4j.Slf4jEventSender send
INFO: REQ_IN
    Address: http://127.0.0.1:9000/helloWorld
    HttpMethod: POST
    Content-Type: text/xml; charset=UTF-8
    ExchangeId: 728979e4-f391-44ad-b80a-24d1953f4ebc
    ServiceName: HelloWorldService
    PortName: HelloWorldServiceImplPort
    PortTypeName: HelloWorldService
    Headers: {SOAPAction="", Accept=*/*, Cache-Control=no-cache, User-Agent=Apache-CXF/3.3.2, connection=keep-alive, content-type=text/xml; charset=UTF-8, Host=127.0.0.1:9000, Pragma=no-cache, Content-Length=213}
    Payload: <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns2:sayHiToUser xmlns:ns2="http://server.hw.demo/"><arg0><name>Universe</name></arg0></ns2:sayHiToUser></soap:Body></soap:Envelope>

sayHiToUser called
Nov 15, 2026 1:52:15 PM org.apache.cxf.ext.logging.slf4j.Slf4jEventSender send
INFO: RESP_OUT
    Content-Type: text/xml
    ResponseCode: 200
    ExchangeId: 728979e4-f391-44ad-b80a-24d1953f4ebc
    ServiceName: HelloWorldService
    PortName: HelloWorldServiceImplPort
    PortTypeName: HelloWorldService
    Headers: {}
    Payload: <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns2:sayHiToUserResponse xmlns:ns2="http://server.hw.demo/"><return>Hello Universe</return></ns2:sayHiToUserResponse></soap:Body></soap:Envelope>

Nov 15, 2026 1:52:15 PM org.apache.cxf.ext.logging.slf4j.Slf4jEventSender send
INFO: REQ_IN
    Address: http://127.0.0.1:9000/helloWorld
    HttpMethod: POST
    Content-Type: text/xml; charset=UTF-8
    ExchangeId: f7f5cc7d-efcb-4819-be99-ab8929b05129
    ServiceName: HelloWorldService
    PortName: HelloWorldServiceImplPort
    PortTypeName: HelloWorldService
    Headers: {SOAPAction="", Accept=*/*, Cache-Control=no-cache, User-Agent=Apache-CXF/3.3.2, connection=keep-alive, content-type=text/xml; charset=UTF-8, Host=127.0.0.1:9000, Pragma=no-cache, Content-Length=159}
    Payload: <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns2:getUsers xmlns:ns2="http://server.hw.demo/"/></soap:Body></soap:Envelope>

getUsers called
Nov 15, 2026 1:52:15 PM org.apache.cxf.ext.logging.slf4j.Slf4jEventSender send
INFO: RESP_OUT
    Content-Type: text/xml
    ResponseCode: 200
    ExchangeId: f7f5cc7d-efcb-4819-be99-ab8929b05129
    ServiceName: HelloWorldService
    PortName: HelloWorldServiceImplPort
    PortTypeName: HelloWorldService
    Headers: {}
    Payload: <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns2:getUsersResponse xmlns:ns2="http://server.hw.demo/"><return><entry><id>1</id><user><name>World</name></user></entry><entry><id>2</id><user><name>Galaxy</name></user></entry><entry><id>3</id><user><name>Universe</name></user></entry></return></ns2:getUsersResponse></soap:Body></soap:Envelope>
````