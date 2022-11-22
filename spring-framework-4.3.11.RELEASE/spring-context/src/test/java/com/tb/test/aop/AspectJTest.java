package com.tb.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 创建 AspectJ 切面
 *
 * @author wp
 * @since 2022/11/21
 */
@Aspect
public class AspectJTest {

    @Pointcut("execution(* *.test(..))")
    public void test() {
    }

    @Pointcut("@annotation(com.tb.test.aop.TTT)")
    public void test2() {

    }

    @Before("test2()")
    public void beforeTest2() {
        System.out.println("before test2");
    }

    @Before("test()")
    public void beforeTest() {
        System.out.println("before test");
    }

    @After("test()")
    public void afterTest() {
        System.out.println("after test");
    }

    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint p) {
        System.out.println("before1");
        Object o = null;
        try {
            o = p.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after1");
        return o;
    }

    @AfterReturning("test()")
    public void returnTest() {
        System.out.println("return");
    }
}
