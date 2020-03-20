package com.frame.rengu.data.po;

import java.io.Serializable;
import java.util.UUID;

public class TYPE_J_POS implements Serializable {

    private String id = UUID.randomUUID().toString();

    public TYPE_J_POS() {
    }

    public TYPE_J_POS(String id, String x, String y, String fvm) {
        this.id = id;
        X = x;
        Y = y;
        this.fvm = fvm;
    }

    private String X ;

    private String Y;

    //航速
    private String fvm;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getX() {
        return X;
    }

    public void setX(String x) {
        X = x;
    }

    public String getY() {
        return Y;
    }

    public void setY(String y) {
        Y = y;
    }

    public String getFvm() {
        return fvm;
    }

    public void setFvm(String fvm) {
        this.fvm = fvm;
    }
}
