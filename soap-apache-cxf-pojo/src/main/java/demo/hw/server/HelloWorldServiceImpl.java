package demo.hw.server;

public class HelloWorldServiceImpl implements HelloWorldService {

    public String sayHi(String text) {
        System.out.println("sayHi called");
        return "Hello " + text;
    }
}

