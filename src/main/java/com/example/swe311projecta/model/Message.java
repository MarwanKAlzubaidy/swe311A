package com.example.swe311projecta.model;

import java.io.File;
import java.io.Serializable;
import lombok.*;
@Data
public class Message implements Serializable {

     private String textContent;
     private File    file;
     private String sender;
     private String ip;


    public Message(File file) {
        this.file = file;

    }

    public Message(String textContent, File file) {
        this.textContent = textContent;
        this.file = file;

    }

    public Message(String textContent) {
        this.textContent = textContent;
        ;
    }
}
