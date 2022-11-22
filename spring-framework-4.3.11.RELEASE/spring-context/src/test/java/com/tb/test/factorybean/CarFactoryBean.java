package com.tb.test.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * FactoryBean 使用
 * 通过实现该接口定制实例化 bean 的逻辑。
 * 当配置文件中的 class 属性配置的实现类式 FactoryBean 时，通过 getBean() 方法返回的不是 FactoryBean 本身，
 * 而是 FactoryBean#getObject() 方法所返回的对象，相当于 FactoryBean#getObject() 代理了 getBean() 方法。
 * 该示例：通过逗号分隔符的方式一次性地为 Car 的所有属性指定值。
 */
public class CarFactoryBean implements FactoryBean<Car> {

    private String carInfo;

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        String[] infos = carInfo.split(",");
        car.setBrand(infos[0]);
        car.setMaxSpeed(Integer.valueOf(infos[1]));
        car.setPrice(Double.valueOf(infos[2]));
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
    }
}
