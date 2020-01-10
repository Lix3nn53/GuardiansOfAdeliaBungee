import io.github.lix3nn53.guardiansofadelia.bungee.socket.MySocketServer;

public class Test {

    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 9092;
        String password = "sad12312d121232f131232131f2421";

        MySocketServer mySocketServer = new MySocketServer(hostname, port, password);
        mySocketServer.start();
    }
}
