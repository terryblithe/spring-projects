package com.tb.test.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class AwareTestBean implements BeanFactoryAware {

    private BeanFactory beanFactory;

    // 声明 bean 的时候，spring 会自动注入 BeanFactory 实例
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void testAware() {
        // 通过 hello 这个 bean id 从 BeanFactory 获取实例
        Hello hello = (Hello) beanFactory.getBean("hello");
        hello.say();
    }
}
