package com.example.swe311projecta.model;

import lombok.Data;

import java.io.Serializable;

@Data

public class Contact implements Serializable {
    private String ip;
    private String name;
    private int port;
    private transient boolean Online;
    private Chat chat;

    public Contact(String ip, String name, int port) {
        this.ip = ip;
        this.name = name;
        this.port = port;
    }
}
