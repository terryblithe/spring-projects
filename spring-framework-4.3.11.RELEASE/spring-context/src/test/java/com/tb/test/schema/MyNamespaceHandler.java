package com.tb.test.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 扩展 NamespaceHandlerSupport，目的是将组件注册到 Spring 容器
 *
 * @author wp
 * @since 2022/11/14
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        // 该行代码的作用是当遇到自定义标签 <user:aaa> 这样类似于以 user 开头的元素，就会把这个元素扔给对应的 UserBeanDefinitionParser 去解析。
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }
}
