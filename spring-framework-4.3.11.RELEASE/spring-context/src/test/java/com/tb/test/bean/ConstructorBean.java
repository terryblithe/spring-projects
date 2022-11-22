package com.tb.test.bean;

public class ConstructorBean {

    private String name;

    private int age;

    public ConstructorBean(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "ConstructorBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
