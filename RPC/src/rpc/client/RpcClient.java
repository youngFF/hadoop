package rpc.client;


import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RpcClient<T> {

    public static <T> T getRemoteProxy(final Class<?> serviceInterface, final InetSocketAddress addr) {


        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface},
                new InvocationHandler() {

                    @Override
                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

                        Socket socket = null ;
                        ObjectOutputStream output = null ;
                        ObjectInputStream input = null ;
                        try {
                            socket = new Socket();
                            socket.connect(addr);


                            output = new ObjectOutputStream(socket.getOutputStream());
                            output.writeUTF(serviceInterface.getName());
                            output.writeUTF(method.getName());
                            output.writeObject(method.getParameterTypes());
                            output.writeObject(objects);

                            input = new ObjectInputStream(socket.getInputStream());
                            return input.readObject();
                        }finally{

                        }

                    }
                });



    }
}
