package com.frame.rengu.data.po;

import java.io.Serializable;
import java.util.UUID;

public class TYPE_J_CONTROL implements Serializable {

    private String id = UUID.randomUUID().toString();

    public TYPE_J_CONTROL() {
    }

    public TYPE_J_CONTROL(String id, int control) {
        this.id = id;
        this.Control = control;
    }

    /**
     *  Control:  0 = 结束  1 = 开始
     */
    private int Control;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getControl() {
        return Control;
    }

    public void setControl(int control) {
        Control = control;
    }
}
