package com.tb.test.schema;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * 实现 BeanDefinitionParser 接口，用来解析 XSD 文件中的定义和组件定义
 *
 * @author wp
 * @since 2022/11/14
 */
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    // Element 对应的类
    @Override
    protected Class<?> getBeanClass(Element element) {
        return User.class;
    }

    // 从 element 中解析并提取对应的元素
    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String userName = element.getAttribute("userName");
        String email = element.getAttribute("email");

        // 将提取的数据放入到 BeanDefinitionBuilder 中，待到完成所有 bean 的解析后统一注册到 BeanFactory 中
        if (StringUtils.hasText(userName)) {
            builder.addPropertyValue("userName", userName);
        }
        if (StringUtils.hasText(email)) {
            builder.addPropertyValue("email", email);
        }
    }
}
