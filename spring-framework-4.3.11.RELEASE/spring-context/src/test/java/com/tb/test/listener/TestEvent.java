package com.tb.test.listener;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义监听事件
 *
 * @author wp
 * @since 2022/11/19
 */
public class TestEvent extends ApplicationEvent {

    private static final long serialVersionUID = -1L;

    public String msg;

    public TestEvent(Object source) {
        super(source);
    }

    public TestEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public void print() {
        System.out.println(msg);
    }

}
