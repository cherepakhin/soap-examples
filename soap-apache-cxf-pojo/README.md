Java First POJO DEMO
====================

This demo illustrates how to develop a service using the "code first", pojo
based approach. This demo uses the JAXB Data binding by default, but you can
use Aegis Data binding by uncommenting the four lines below:

(Client.java)
```java
//import org.apache.cxf.aegis.databinding.AegisDatabinding;
//factory.getServiceFactory().setDataBinding(new AegisDatabinding());

```

(Server.java)

```java
//import org.apache.cxf.aegis.databinding.AegisDatabinding;
//svrFactory.getServiceFactory().setDataBinding(new AegisDatabinding());

```

Building and running the demo using Maven
-----------------------------------------

From the base directory of this sample (i.e., where this README file is
located), the pom.xml file is used to build and run the demo. 

Using either UNIX or Windows:

  mvn install   (builds the demo)
  mvn -Pserver  (from one command line window)
  mvn -Pclient  (from a second command line window)

```text
Nov 11, 2026 5:33:51 PM org.apache.cxf.wsdl.service.factory.ReflectionServiceFactoryBean buildServiceFromClass
INFO: Creating Service {http://server.hw.demo/}HelloWorld from class demo.hw.server.HelloWorldService
Invoke sayHi()....
Hello vasi
```

'vasi' - username received from the server.

To remove the code generated from the WSDL file and the .class
files, run "mvn clean".

По интерфейсу HelloWorldService клиент находит службу на сервере:

```java
  ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
  factory.setAddress("http://localhost:9000/Hello");
  HelloWorldService remoteService = factory.create(HelloWorldService.class);
```

и вызывает метод remoteService.sayHi(), определенный в интерфейсе:

```java
    String response = remoteService.sayHi(System.getProperty("user.name"));
    System.out.println(response);
```

_Примечание: запрос к SOAP службе делается через POST-запрос._:

```text
org.apache.cxf.ext.logging.slf4j.Slf4jEventSender send
INFO: REQ_IN
    Address: http://localhost:9000/Hello
    HttpMethod: POST
    Content-Type: text/xml; charset=UTF-8
    ExchangeId: 10544cf2-fa39-4a4e-9675-8d6b3a5bc127
    ServiceName: HelloWorldService
    PortName: HelloWorldServicePort
    PortTypeName: HelloWorldServicePortType
    Headers: {SOAPAction="", Accept=*/*, Cache-Control=no-cache, User-Agent=Apache-CXF/3.3.2, connection=keep-alive, content-type=text/xml; charset=UTF-8, Host=localhost:9000, Pragma=no-cache, Content-Length=215}
    Payload: <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/"><soap:Body><ns1:sayHi xmlns:ns1="http://server.hw.demo/"><arg0 xmlns="http://server.hw.demo/">vasi</arg0></ns1:sayHi></soap:Body></soap:Envelope>
```
