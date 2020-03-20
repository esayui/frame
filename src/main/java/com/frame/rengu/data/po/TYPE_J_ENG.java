package com.frame.rengu.data.po;

import java.io.Serializable;
import java.util.UUID;

public class TYPE_J_ENG implements Serializable {

    private String id = UUID.randomUUID().toString();


    public TYPE_J_ENG() {
    }

    public TYPE_J_ENG(String id, int electric) {
        this.id = id;
        this.electric = electric;
    }
    //电量
    private int electric;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getElectric() {
        return electric;
    }

    public void setElectric(int electric) {
        this.electric = electric;
    }
}
