package com.tb.test.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 自定义监听器
 *
 * @author wp
 * @since 2022/11/19
 */
public class TestListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof TestEvent) {
            TestEvent testEvent = (TestEvent) event;
            testEvent.print();
        }
    }
}
