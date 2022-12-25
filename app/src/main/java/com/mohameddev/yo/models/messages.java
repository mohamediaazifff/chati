package com.mohameddev.yo.models;

import com.google.type.DateTime;

import java.util.Date;

public class messages {
    String msg, date, time, status, sender_id, reciver_id, type;

    public messages(String msg, String date, String time, String status, String sender_id, String reciver_id, String type) {
        this.msg = msg;
        this.date = date;
        this.time = time;
        this.status = status;
        this.sender_id = sender_id;
        this.reciver_id = reciver_id;
        this.type = type;
    }

    public messages() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        date= String.valueOf(new Date().getTime());
        return date;
    }

    public void setDate(String date) {
        date= String.valueOf(new Date().getTime());
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getReciver_id() {
        return reciver_id;
    }

    public void setReciver_id(String reciver_id) {
        this.reciver_id = reciver_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
