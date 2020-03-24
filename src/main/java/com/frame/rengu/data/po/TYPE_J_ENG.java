package com.frame.rengu.data.po;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@TableName(value = "tb_eng")
public class TYPE_J_ENG implements Serializable {

    @TableId(type = IdType.UUID)
    private String id  = UUID.randomUUID().toString();


    public TYPE_J_ENG() {
    }

    public TYPE_J_ENG(String id, int electric) {
        this.id = id;
        this.electric = electric;
    }
    //电量
    private int electric;

    private Date createTime = new Date();

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

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
