package md.leonis.soap;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Arrays;

@WebService(endpointInterface = "md.leonis.soap.HelloWorldInterface")
public class HelloWorldWS implements HelloWorldInterface {

    @Override
    public String helloWorldWebMethod(String name) {
        System.out.println("Method helloWorldWebMethod received: " + name + "");
        return "From WS: Hello World JAX-WS " + name;
    }

    @Override
    public ArrayList<String> helloWorldArrayListWebMethod(String name) {
        System.out.println("Method helloWorldArrayListWebMethod received: " + name + "");
        String nameFromMethod = helloWorldWebMethod(name);
        System.out.println("nameFromMethod:" + nameFromMethod);
        return new ArrayList<>(Arrays.asList("Response", nameFromMethod));
    }
}
