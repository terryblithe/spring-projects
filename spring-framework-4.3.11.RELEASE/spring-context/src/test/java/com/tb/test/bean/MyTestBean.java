package com.tb.test.bean;

public class MyTestBean {

    private String testStr = "testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public void test() {
        System.out.println("test");
    }

    @Override
    public String toString() {
        return "MyTestBean{" +
                "testStr='" + testStr + '\'' +
                '}';
    }
}
