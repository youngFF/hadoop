package hyena.nio.selector;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SelectorTest {

    public static void main(String[] args) throws IOException {

        // create a selector ----> waiter
        Selector selector = Selector.open();

        // create ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open() ;

        // bind ip-port  : local:8080
        serverSocketChannel.bind(new InetSocketAddress(8080));

        // config non-block mode
        serverSocketChannel.configureBlocking(false);


        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_READ );

        int interestOps = selectionKey.interestOps();

        System.out.println(interestOps);


    }



}
