import com.sun.security.ntlm.Server;
import org.junit.Test;
import rpc.client.RpcClient;
import rpc.model.HelloService;
import rpc.model.HelloServiceImpl;
import rpc.server.RpcServer;
import rpc.server.serverImpl.RpcServerImpl;

import java.io.IOException;
import java.net.InetSocketAddress;

public class RpcTest {


    @Test
    public void test() {

        new Thread(()->{
            // run method body
            RpcServer server = new RpcServerImpl(45000);
            server.register(HelloService.class, HelloServiceImpl.class);
            try {
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        HelloService service = RpcClient.getRemoteProxy(HelloService.class, new InetSocketAddress("localhost", 45000));
        System.out.println(service.sayHi("test"));



    }
}
