package demo.hw.client;

import demo.hw.server.HelloWorldService;
import demo.hw.server.User;
import demo.hw.server.UserImpl;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import java.util.Map;

public final class Client {

    public static void main(String[] args) throws Exception {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(HelloWorldService.class);
        factory.setAddress("http://127.0.0.1:9000/helloWorld");

        HelloWorldService helloWorldService = factory.create(HelloWorldService.class);
        System.out.println("Start---------------------------------");

        System.out.println("hw.sayHi(\"World\"):");
        String answer = helloWorldService.sayHi("World");
        System.out.println(answer);
        System.out.println("--------------------------------------");

        System.out.println("hw.sayHiToUser(user) for User=\"World\":");
        User user = new UserImpl("World");
        System.out.println(helloWorldService.sayHiToUser(user));
        System.out.println("--------------------------------------");

        //say hi to some more users to fill up the map a bit
        System.out.println("hw.sayHiToUser(user) for User=\"Galaxy\":");
        user = new UserImpl("Galaxy");
        System.out.println(helloWorldService.sayHiToUser(user));
        System.out.println("--------------------------------------");

        System.out.println("hw.sayHiToUser(user) for User=\"Universe\":");
        user = new UserImpl("Universe");
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
