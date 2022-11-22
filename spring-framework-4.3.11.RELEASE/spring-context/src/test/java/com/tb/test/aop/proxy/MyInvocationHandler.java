package com.tb.test.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 创建自定义 InvocationHandler，用于对接口提供的方法进行增强
 *
 * @author wp
 * @since 2022/11/21
 */
public class MyInvocationHandler implements InvocationHandler {

    // 目标对象
    private Object target;

    public MyInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    /**
     * 执行目标对象的方法
     *
     * @param proxy
     * @param method
     * @param args
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在目标对象的方法执行之前简单的打印一下
        System.out.println("----------------before------------------");

        // 执行目标对象的方法
        Object result = method.invoke(target, args);

        // 在目标对象的方法执行之后简单的打印一下
        System.out.println("-------------after--------------------");
        return result;
    }

    /**
     * 获取目标对象的代理对象
     *
     * @return 代理对象
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                target.getClass().getInterfaces(), this);
    }
}
