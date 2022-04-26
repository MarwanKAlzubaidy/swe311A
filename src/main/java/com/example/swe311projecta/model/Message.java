package com.example.swe311projecta.model;

import java.io.File;
import java.io.Serializable;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import lombok.*;
@Data
public class Message implements Serializable {

     private String textContent;
     private String    file;
     private String sender;
     private String ip;
     private String timestamp;
    private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public Message(String textContent, String file, String sender, String ip) {
        this.textContent = textContent;
        this.file = file;
        this.sender = sender;
        this.ip = ip;
        this.timestamp = sdf3.format(new Timestamp(System.currentTimeMillis()));
    }


    public Message(String high_world, String file) {

    }

}
