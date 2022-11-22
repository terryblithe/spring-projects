package com.tb.test.aop;

import org.springframework.aop.framework.AopContext;

public class AopServiceImpl implements AopService {

    @TTT
    @Override
    public void a() {
        System.out.println("a ======");
        // this.b();
        ((AopService) AopContext.currentProxy()).b();
    }

    @TTT
    @Override
    public void b() {
        System.out.println("b ======");
    }
}
