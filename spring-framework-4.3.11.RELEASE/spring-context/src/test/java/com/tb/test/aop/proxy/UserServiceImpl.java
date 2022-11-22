package com.tb.test.aop.proxy;

/**
 * 业务接口实现类
 *
 * @author wp
 * @since 2022/11/21
 */
public class UserServiceImpl implements UserService {
    @Override
    public void add() {
        System.out.println("----------add-------------");
    }
}
