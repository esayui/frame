package com.frame.rengu.data.po;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@TableName(value = "tb_mov")
public class TYPE_J_MOV implements Serializable {

    @TableId(type = IdType.UUID)
    private String id  = UUID.randomUUID().toString();

    /**
     *  turn：0 = 减速  1 = 加速
     */
    private int turn;

    private Date createTime = new Date();

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public TYPE_J_MOV(String id, int turn) {
        this.id = id;
        this.turn = turn;
    }

    public TYPE_J_MOV(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
