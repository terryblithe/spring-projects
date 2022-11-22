package com.tb.test;

import com.tb.test.aop.proxy.MethodInterceptorImpl;
import com.tb.test.aop.proxy.MyInvocationHandler;
import com.tb.test.aop.proxy.UserService;
import com.tb.test.aop.proxy.UserServiceImpl;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class OtherTest {

    @Test
    public void testLocal() {
        // 带有语言和国家、地区信息的本地化对象
        Locale locale = new Locale("zh", "CN");

        // 只有语言信息的本地化对象
        Locale locale1 = new Locale("zh");

        // 等同于 Locale("zh", "CN")
        Locale locale2 = Locale.CHINA;

        // 等同于 Locale("zh")
        Locale locale3 = Locale.CHINESE;

        // 获取本地系统默认的本地化对象
        Locale locale4 = Locale.getDefault();
    }

    @Test
    public void testMessageFormat() {
        // 信息格式化串
        String pattern1 = "{0}, 你好！你于{1}在工商银行存入{2}元。";
        String pattern2 = "At {1,time,short} On {1,date,long}, {0} paid {2,number,currency}.";

        // 用于动态替换占位符的参数
        Object[] params = {"Join", new GregorianCalendar().getTime(), 1.0E3};

        // 使用默认本地化对象格式化信息
        String msg1 = MessageFormat.format(pattern1, params);

        // 使用指定的本地化对象格式化信息
        MessageFormat mf = new MessageFormat(pattern2, Locale.US);
        String msg2 = mf.format(params);

        System.out.println(msg1);
        System.out.println(msg2);
    }

    /**
     * 测试 JDK 动态代理
     */
    @Test
    public void testJDKProxy() {
        // 实例化目标对象
        UserService userService = new UserServiceImpl();
        // 实例化 InvocationHandler
        MyInvocationHandler invocationHandler = new MyInvocationHandler(userService);
        // 根据目标对象生成代理对象
        UserService proxy = (UserService) invocationHandler.getProxy();
        // 调用代理对象方法
        proxy.add();
    }

    @Test
    public void testCGLIBProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(OtherTest.class);
        enhancer.setCallback(new MethodInterceptorImpl());

        OtherTest demo = (OtherTest) enhancer.create();
        demo.test();
        System.out.println(demo);
    }

    public void test() {
        System.out.println("EnhancerDemo test()");
    }

}
