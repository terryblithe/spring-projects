package com.tb.test.bean;

import java.util.List;

public class PropertyBean {

    private List<String> names;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "PropertyBean{" +
                "names=" + names +
                '}';
    }
}
