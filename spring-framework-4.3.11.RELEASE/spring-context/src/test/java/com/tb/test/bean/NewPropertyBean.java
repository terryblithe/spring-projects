package com.tb.test.bean;

import java.util.List;

public class NewPropertyBean {

    private List<String> names;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "NewPropertyBean{" +
                "names=" + names +
                '}';
    }
}
