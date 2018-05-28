package hyena.nio.test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientSocket {


    public static void main(String[] args) throws IOException {
        //create socket
        Socket client = new Socket();
        // bind client-socket ip-port
        client.bind(new InetSocketAddress("localhost", 9000));
        // connect
//        client.connect(new InetSocketAddress("localhost", 8080));
        client.connect(new InetSocketAddress("localhost", 8080));
    }
}
