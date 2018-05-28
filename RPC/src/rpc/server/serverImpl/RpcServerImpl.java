package rpc.server.serverImpl;

import rpc.server.RpcServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServerImpl implements RpcServer {


    private static final HashMap<String, Class> serviceRegistry = new HashMap<>();
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static int port;
    private boolean isRunning = false;


    // constructor
    public RpcServerImpl(int port) {
        this.port = port;
    }


    @Override

    public void stop() {
        isRunning = false;
        executor.shutdown();
    }

    @Override
    public void start() throws IOException {
        //创建服务器对象
        ServerSocket serverSocket = new ServerSocket();
        //绑定端口
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("start server");
        try {
            while (true) {
                // 阻塞式
                Socket socket = serverSocket.accept();
                executor.execute(new Task(socket));
            }
        } finally {
            serverSocket.close();
        }
    }

    @Override
    public void register(Class serviceInterface, Class impl) {
        synchronized (serviceRegistry) {
            serviceRegistry.put(serviceInterface.getName(), impl);
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public int getPort() {
        return port;
    }


    // private inner static class
    private static class Task implements Runnable {

        private Socket client;

        public Task(Socket client) {
            this.client = client;
        }


        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream output = null;

            try {
                input = new ObjectInputStream(client.getInputStream());

                String serviceName = input.readUTF();

                String methodName = input.readUTF();

                Class<?>[] parameterTypes = (Class<?>[]) input.readObject();

                Object[] arguments = (Object[]) input.readObject();

                Class serviceClass = serviceRegistry.get(serviceName);

                if (serviceClass == null) {
                    throw new ClassNotFoundException();
                }


                Method method = serviceClass.getMethod(methodName, parameterTypes);

                Object result = method.invoke(serviceClass.newInstance(), arguments);

                // 反序列化
                output = new ObjectOutputStream(client.getOutputStream());

                output.writeObject(result);
            } catch (IOException e) {
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }


}
