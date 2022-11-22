package com.tb.test.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class QualifierBean {

    @Autowired
    // @Qualifier("action")
    @Qualifier("main")
    private NewPropertyBean npb;

    public NewPropertyBean getNpb() {
        return npb;
    }

    public void setNpb(NewPropertyBean npb) {
        this.npb = npb;
    }

    @Override
    public String toString() {
        return "QualifierBean{" +
                "npb=" + npb +
                '}';
    }
}
