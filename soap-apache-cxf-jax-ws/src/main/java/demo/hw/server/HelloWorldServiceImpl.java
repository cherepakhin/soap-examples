package demo.hw.server;

import javax.jws.WebService;
import java.util.LinkedHashMap;
import java.util.Map;

// Публикация веб-сервиса, реализующий интерфейс `demo.hw.server.HelloWorld` с именем `HelloWorld`
// Затем этот веб-сервис будет привязан к URL-адресу `http://localhost:9000/helloWorld` ы Server.java
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
