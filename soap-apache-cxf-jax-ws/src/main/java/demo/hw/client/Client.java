package demo.hw.client;

import demo.hw.server.HelloWorldService;
import demo.hw.server.User;
import demo.hw.server.UserImpl;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.util.Map;

public final class Client {

    public static void main(String[] args) throws Exception {
        // создание фабрики для создания прокси для сервиса
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        // настройка factory для создания сервиса
        factory.setServiceClass(HelloWorldService.class);
        // Указание адреса сервиса для factory
        // see in Server.java:
        //    String address = "http://localhost:9000/helloWorld";
        //    javax.xml.ws.Endpoint.publish(address, implementor, new org.apache.cxf.ext.logging.LoggingFeature());
        String address = "http://localhost:9000/helloWorld";
        factory.setAddress(address);

        // factory создает прокси для вызова удаленного сервиса
        HelloWorldService helloWorldService = factory.create(HelloWorldService.class);
        System.out.println("Start---------------------------------");

        System.out.println("Call service. hw.sayHi(\"World\"):");
        // вызов удаленного метода!!!
        String answer = helloWorldService.sayHi("World");
        System.out.println("Answer received: " + answer);
        System.out.println("--------------------------------------");

        System.out.println("hw.sayHiToUser(user) for User=\"Request 1\":");
        User user = new UserImpl("Request 1");
        System.out.println(helloWorldService.sayHiToUser(user));
        System.out.println("--------------------------------------");

        //say hi to some more users to fill up the map a bit
        System.out.println("hw.sayHiToUser(user) for User=\"Request 2\":");
        user = new UserImpl("Request 2");
        System.out.println(helloWorldService.sayHiToUser(user));
        System.out.println("--------------------------------------");

        System.out.println("hw.sayHiToUser(user) for User=\"Request 3\":");
        user = new UserImpl("Request 3");
        System.out.println(helloWorldService.sayHiToUser(user));
        System.out.println("--------------------------------------");

        System.out.println();
        System.out.println("Users: ");
        Map<Integer, User> users = helloWorldService.getUsers();

        for (Map.Entry<Integer, User> e : users.entrySet()) {
            System.out.println("  " + e.getKey() + ": " + e.getValue().getName());
        }
    }
}
