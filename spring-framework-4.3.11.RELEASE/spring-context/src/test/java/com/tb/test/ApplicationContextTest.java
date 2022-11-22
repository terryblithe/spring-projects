package com.tb.test;

import com.tb.test.aop.AopService;
import com.tb.test.bean.MyTestBean;
import com.tb.test.beanfactory.HelloMessage;
import com.tb.test.context.MyApplicationContext;
import com.tb.test.context.UserManager;
import com.tb.test.converter.String2DateConverter;
import com.tb.test.listener.TestEvent;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ApplicationContextTest {

    @Test
    public void testMyApplicationContext() {
        ApplicationContext context = new MyApplicationContext("classpath:config/spring.xml");
        Environment environment = context.getEnvironment();
        for (String activeProfile : environment.getActiveProfiles()) {
            System.out.println(activeProfile);
        }
    }

    /**
     * 测试属性编辑器
     */
    @Test
    public void testPropertyEditor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        UserManager userManager = (UserManager) context.getBean("userManager");
        System.out.println(userManager);
    }

    /**
     * BeanFactoryPostProcessor 应用，方式 1
     */
    @Test
    public void testBeanFactoryPostProcessor1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        HelloMessage message = (HelloMessage) context.getBean("message");
        System.out.println(message);
    }

    /**
     * BeanFactoryPostProcessor 应用，方式 2
     */
    @Test
    public void testBeanFactoryPostProcessor2() {
        ConfigurableListableBeanFactory bf = new XmlBeanFactory(new ClassPathResource("config/spring.xml"));
        BeanFactoryPostProcessor bfpp = (BeanFactoryPostProcessor) bf.getBean("bfpp");
        bfpp.postProcessBeanFactory(bf);
        System.out.println(bf.getBean("simpleBean"));
    }

    /**
     * 国际化测试
     */
    @Test
    public void testMessageSource() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        Object[] params = {"Join", new GregorianCalendar().getTime(), 1.0E3};

        String msg1 = context.getMessage("test", params, Locale.US);
        String msg2 = context.getMessage("test", params, Locale.CHINA);

        System.out.println(msg1);
        System.out.println(msg2);
    }

    /**
     * 测试监听器
     */
    @Test
    public void testListener() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        TestEvent event = new TestEvent("hello", "msg");
        context.publishEvent(event);
    }

    /**
     * Converter 测试
     */
    @Test
    public void testConverter() {
        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new String2DateConverter());
        String dateStr = "2022-01-01 00:00:00";
        Date date = conversionService.convert(dateStr, Date.class);
        System.out.println(date);
    }

    /**
     * 测试 AspectJ
     */
    @Test
    public void testAspectJ() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        MyTestBean testBean = (MyTestBean) context.getBean("myTestBean");
        testBean.test();
    }

    @Test
    public void testExposeProxy() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        AopService aopService = (AopService) context.getBean("aopService");
        aopService.a();
    }
}
