import hyena.proxy.service.Service;
import hyena.proxy.service.impl.ServiceImpl;
import proxy.MyProxy;

public class Test {


    public static void main(String[] args) {
        Service service = new ServiceImpl();
        Service proxy = (Service) MyProxy.getProxy(service);

        proxy.sayHello();

    }

}
