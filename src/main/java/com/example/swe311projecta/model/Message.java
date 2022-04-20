package com.example.swe311projecta.model;

import java.io.File;
import java.io.Serializable;
import lombok.*;
@Data
public class Message implements Serializable {

     private String textContent;
     private File    file;
     private Contact sender;
     private Contact receiver;

    public Message(File file, Contact sender, Contact receiver) {
        this.file = file;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Message(String textContent, File file, Contact sender, Contact receiver) {
        this.textContent = textContent;
        this.file = file;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Message(String textContent, Contact sender, Contact receiver) {
        this.textContent = textContent;
        this.sender = sender;
        this.receiver = receiver;
    }
}
