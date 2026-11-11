package md.leonis.soap;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Arrays;

@WebService(endpointInterface = "md.leonis.soap.HelloWorldInterface")
public class HelloWorldWS implements HelloWorldInterface {

    @Override
    public String helloWorldWebMethod(String name) {
        System.out.println("HelloWorldWS.helloWorldWebMethod");
        return "Hello World JAX-WS " + name;
    }

    @Override
    public ArrayList<String> helloWorldArrayListWebMethod(String name) {
        System.out.println("HelloWorldWS.helloWorldArrayListWebMethod");
        return new ArrayList<>(Arrays.asList("Response", helloWorldWebMethod(name)));
    }
}
