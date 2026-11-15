package demo.hw.server;

import javax.jws.WebService;
import java.util.LinkedHashMap;
import java.util.Map;

// Публикация веб-сервиса, реализующий интерфейс `demo.hw.server.HelloWorld` с именем `HelloWorldService`
// Затем этот веб-сервис будет привязан к URL-адресу `http://localhost:9000/helloWorld` в Server.java
//        HelloWorldService implementor = new HelloWorldServiceImpl();
//        String address = "http://localhost:9000/helloWorld";
//        Endpoint.publish(address, implementor, new org.apache.cxf.ext.logging.LoggingFeature());

@WebService(endpointInterface = "demo.hw.server.HelloWorldService", serviceName = "HelloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService {

    private Map<Integer, User> users = new LinkedHashMap<>();

    public String sayHi(String text) {
        System.out.println("sayHi called");
        return "Hello " + text;
    }

    public String sayHiToUser(User user) {
        System.out.println("sayHiToUser called");
        users.put(users.size() + 1, user);
        return "Hello " + user.getName();
    }

    public Map<Integer, User> getUsers() {
        System.out.println("getUsers called");
        return users;
    }

}
