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

        HelloWorldService hw = factory.create(HelloWorldService.class);

        String answer = hw.sayHi("World");
        System.out.println(answer);

        User user = new UserImpl("World");
        System.out.println(hw.sayHiToUser(user));

        //say hi to some more users to fill up the map a bit
        user = new UserImpl("Galaxy");
        System.out.println(hw.sayHiToUser(user));

        user = new UserImpl("Universe");
        System.out.println(hw.sayHiToUser(user));

        System.out.println();
        System.out.println("Users: ");
        Map<Integer, User> users = hw.getUsers();

        for (Map.Entry<Integer, User> e : users.entrySet()) {
            System.out.println("  " + e.getKey() + ": " + e.getValue().getName());
        }
    }
}
