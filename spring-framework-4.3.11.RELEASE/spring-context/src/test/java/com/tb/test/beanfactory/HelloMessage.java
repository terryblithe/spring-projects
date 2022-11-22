package com.tb.test.beanfactory;

public class HelloMessage {

    private String mes;

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        return "HelloMessage{" +
                "mes='" + mes + '\'' +
                '}';
    }
}
