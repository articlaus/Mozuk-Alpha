/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model.other;

/**
 *
 * @author Enkhbat
 */
public class CodeMessage {

    private Integer index;
    private String code;
    private String msg;

    public CodeMessage() {
    }

    public CodeMessage(String msg) {
        this.msg = msg;
    }

    public CodeMessage(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMessage(Integer index, String code, String msg) {
        this.index = index;
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
