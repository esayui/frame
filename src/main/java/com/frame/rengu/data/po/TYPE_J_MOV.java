package com.frame.rengu.data.po;

import java.io.Serializable;
import java.util.UUID;

public class TYPE_J_MOV implements Serializable {
    private String id = UUID.randomUUID().toString();

    /**
     *  turn：0 = 减速  1 = 加速
     */
    private int turn;

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
