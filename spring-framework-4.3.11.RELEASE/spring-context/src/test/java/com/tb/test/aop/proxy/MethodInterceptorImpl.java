package com.tb.test.aop.proxy;


import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB demo
 *
 * @author wp
 * @since 2022/11/22
 */
public class MethodInterceptorImpl implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before invoke: " + method);
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("After invoke: " + method);
        return result;
    }
}
