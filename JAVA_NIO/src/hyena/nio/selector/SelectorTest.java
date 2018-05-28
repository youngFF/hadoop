package hyena.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class SelectorTest {

    public static void main(String[] args) throws IOException {

        // create  a selector
        Selector selector = Selector.open();
        // create a serverSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // bind ip-port
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8080));

        serverSocketChannel.configureBlocking(false);
        // wait for connection
        serverSocketChannel.accept();


        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("accepted ready");
        // method select() blocks
        int select = selector.select();


        System.out.println("accepted successed");
        System.out.println(select);
    }



}
