package demo.hw.server;

import javax.xml.ws.Endpoint;

import org.apache.cxf.ext.logging.LoggingFeature;

public class Server {

    public Server() {
        System.out.println("Server: Starting Server");
        System.out.println("Server: ========================================================");
        HelloWorldService helloWorldServiceImpl = new HelloWorldServiceImpl();
        String address = "http://localhost:9000/helloWorld";
        // запросы по адресу http://localhost:9000/helloWorld будут отрабатываться helloWorldServiceImpl
        // Сервис будет представлен в виде интерфейса HelloWorldService
        Endpoint.publish(address, helloWorldServiceImpl, new LoggingFeature());
        System.out.println("Server: end publish");
    }
}
