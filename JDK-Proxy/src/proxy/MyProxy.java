package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxy {

    // 指定代理对象
    private static Object target ;


    public static Object getProxy(Object obj) {
        target = obj ;


        return  Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces()
                , new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("before");
                        Object result = method.invoke(obj, args);

                        System.out.println("after");
                        return null;
                    }
                });

    } ;


}
