package com.tb.test;

import com.tb.test.aware.AwareTestBean;
import com.tb.test.bean.*;
import com.tb.test.factorybean.Car;
import com.tb.test.factorybean.CarFactoryBean;
import com.tb.test.schema.User;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class BeanFactoryTest {

    @Test
    public void testXmlBeanFactory() {
        XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("config/spring.xml"));
        MyTestBean bean = (MyTestBean) beanFactory.getBean("myTestBean");
        System.out.println(bean.getTestStr());

        // 获取 <meta /> 标签中配置数据
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("myTestBean");
        String metaValue = (String) beanDefinition.getAttribute("testStr");
        System.out.println("mata value: " + metaValue);
    }

    /**
     * lookup-method 被称为获取器注入
     * 1. 抽象方法还没被实现，但可以直接调用
     * 2. lookup-method 子元素这个配置完成的功能是动态将 teacher 所代表的 bean 作为 getBean() 的返回值
     * 3. 当我们业务变更或者在其他情况下，teacher 里面的业务逻辑已经不再符合我们的业务要求，我们可以通过增加新的逻辑类，同时修改配置文件
     */
    @Test
    public void testLookupMethod() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        GetBeanTest beanTest = (GetBeanTest) context.getBean("getBeanTest");
        beanTest.showMe();
    }

    /**
     * replaced-method 方法替换，可以在运行时用新的方法替换现有的方法。
     * 与 lookup-method 不同的是，replaced-method 不但可以动态地替换返回实体 bean，而且还能动态地更改原有方法的逻辑。
     */
    @Test
    public void testReplaceMethod() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        TestChangeMethod testChangeMethod = (TestChangeMethod) context.getBean("testChangeMethod");
        testChangeMethod.changeMe();
    }

    @Test
    public void testConstructorArg() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        ConstructorBean constructorBean = (ConstructorBean) context.getBean("constructorBean");
        System.out.println(constructorBean.toString());
    }

    @Test
    public void testProperty() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        PropertyBean propertyBean = (PropertyBean) context.getBean("propertyBean");
        System.out.println(propertyBean);
    }

    @Test
    public void testQualifier() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        QualifierBean qualifierBean = (QualifierBean) context.getBean("qualifierBean");
        System.out.println(qualifierBean);
    }

    @Test
    public void testImport() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        MyTestBean testBean = (MyTestBean) context.getBean("testImport");
        System.out.println(testBean);
    }

    /**
     * 测试自定义标签，实现自定义标签步骤：
     * 1. 创建一个需要扩展的组件
     * 2. 定义一个 XSD 文件描述组件内容
     * 3. 创建一个文件，实现 BeanDefinitionParser 接口，用来解析 XSD 文件中的定义和组件定义
     * 4. 创建一个 Handler 文件，扩展自 NamespaceHandlerSupport，目的是将组件注册到 Spring 容器
     * 5. 编写 spring.handlers 和 spring.schemas 文件
     */
    @Test
    public void testCustomTag() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        User user = (User) context.getBean("testUser");
        System.out.println(user);
    }

    @Test
    public void testFactoryBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        Car car = (Car) context.getBean("car");
        System.out.println(car);

        // 想要获取 CarFactoryBean 的实例，需要在 beanName 前加上 "&" 前缀
        CarFactoryBean carFactoryBean = (CarFactoryBean) context.getBean("&car");
        System.out.println(carFactoryBean.getCarInfo());
    }

    /**
     * 构造器循环依赖没有办法解决，只能抛出异常
     */
    @Test
    public void testConstructorCircleDependency() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
    }

    /**
     * 属性注入循环依赖，Spring 通过提前暴露 ObjectFactory 来解决
     */
    @Test
    public void testSetterCircleDependency() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        System.out.println(context.getBean("testAA"));
        System.out.println(context.getBean("testBB"));
        System.out.println(context.getBean("testCC"));
    }

    /**
     * 原型模式循环依赖，Spring 无法处理，抛出异常
     */
    @Test
    public void testPrototypeCircleDependency() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        System.out.println(context.getBean("testAAA"));
        System.out.println(context.getBean("testBBB"));
        System.out.println(context.getBean("testCCC"));
    }

    @Test
    public void testBeanFactoryAware() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring.xml");
        AwareTestBean awareTestBean = (AwareTestBean) context.getBean("awareTestBean");
        awareTestBean.testAware();
    }
}
