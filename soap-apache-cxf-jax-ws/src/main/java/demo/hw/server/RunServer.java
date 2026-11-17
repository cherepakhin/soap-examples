package demo.hw.server;

public class RunServer {

    public static void main(String[] args) throws Exception {
        System.out.println("Server: start main");
        Server server = new demo.hw.server.Server();
        System.out.println("Server: Server ready. Waiting for requests...");
        System.out.println("Server: ========================================================");

        Thread.sleep(5 * 60 * 1000); // Hold on for 5 minutes
        System.out.println("Server exiting");
        System.exit(0);
    }
}
