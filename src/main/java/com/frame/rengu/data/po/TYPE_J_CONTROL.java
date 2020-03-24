package com.frame.rengu.data.po;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@TableName(value = "tb_control")
public class TYPE_J_CONTROL implements Serializable {


    @TableId(type = IdType.UUID)
    private String id  = UUID.randomUUID().toString();

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

    public int getControl() {
        return Control;
    }

    public void setControl(int control) {
        Control = control;
    }


    @Override
    public String toString() {
        return "TYPE_J_CONTROL{" +
                "id='" + id + '\'' +
                ", Control=" + Control +
                '}';
    }
}
