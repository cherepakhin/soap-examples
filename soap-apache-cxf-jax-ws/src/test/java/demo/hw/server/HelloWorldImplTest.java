package demo.hw.server;

import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HelloWorldImplTest {

    @Test
    public void testSayHi() {
        HelloWorldServiceImpl hwi = new HelloWorldServiceImpl();
        String response = hwi.sayHi("Bob");
        assertEquals("SayHi isn't returning expected string", "Hello Bob", response);
    }

    @Test
    public void testSayHiToUser() {
        HelloWorldServiceImpl hwi = new HelloWorldServiceImpl();
        User sam = new UserImpl("Sam");
        String response = hwi.sayHiToUser(sam);
        assertEquals("SayHiToUser isn't returning expected string", "Hello Sam", response);
    }

    @Test
    public void testGetUsers() {
        HelloWorldServiceImpl hwi = new HelloWorldServiceImpl();
        User mike = new UserImpl("Mike");
        hwi.sayHiToUser(mike);

        User george = new UserImpl("George");
        hwi.sayHiToUser(george);
        Map<Integer, User> userMap = hwi.getUsers();
        assertEquals("getUsers() not returning expected number of users", userMap.size(), 2);
        assertEquals("Expected user Mike not found", "Mike", userMap.get(1).getName());
        assertEquals("Expected user George not found", "George", userMap.get(2).getName());
    }

}
