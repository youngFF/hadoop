package hyena.proxy.service.impl;

import hyena.proxy.service.Service;

public class ServiceImpl implements Service {

    @Override
    public void sayHello() {
        System.out.println("hello world");
    }
}
