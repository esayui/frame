package com.frame.rengu.data.po;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@TableName(value = "tb_pos")
public class TYPE_J_POS implements Serializable {


    @TableId(type = IdType.UUID)
    private String id  = UUID.randomUUID().toString();

    public TYPE_J_POS() {

    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public TYPE_J_POS(String id, String x, String y, String fvm) {
        this.id = id;
        X = x;
        Y = y;
        this.fvm = fvm;
    }

    private String X ;

    private String Y;

    private Date createTime = new Date();

    //航速
    private String fvm;

    //路程
    private String voyage;


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

    public String getVoyage() {
        return voyage;
    }

    public void setVoyage(String voyage) {
        this.voyage = voyage;
    }
}
