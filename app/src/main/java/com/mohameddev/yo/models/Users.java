package com.mohameddev.yo.models;

import java.util.HashMap;

public class Users {
    private String id;
    private String username;
    private String user_profile;
    private String status;
    private String show_status;
    private HashMap<String,String> chat;

    public Users() {
    }

    public Users(String id, String username, String user_profile, String status, String show_status, HashMap<String, String> chat) {
        this.id = id;
        this.username = username;
        this.user_profile = user_profile;
        this.status = status;
        this.show_status = show_status;
        this.chat = chat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_profile() {
        return user_profile;
    }

    public void setUser_profile(String user_profile) {
        this.user_profile = user_profile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getShow_status() {
        return show_status;
    }

    public void setShow_status(String show_status) {
        this.show_status = show_status;
    }

    public HashMap<String, String> getChat() {
        return chat;
    }

    public void setChat(HashMap<String, String> chat) {
        this.chat = chat;
    }
}
