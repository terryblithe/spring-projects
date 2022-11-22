package com.tb.test.circledependency;

public class TestB {

    private TestC testC;

    public TestB() {
    }

    public TestB(TestC testC) {
        this.testC = testC;
    }

    public void b() {
        testC.c();
    }

    public TestC getTestC() {
        return testC;
    }

    public void setTestC(TestC testC) {
        this.testC = testC;
    }
}
