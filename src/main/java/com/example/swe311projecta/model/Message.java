package com.example.swe311projecta.model;

import java.io.File;
import java.io.Serializable;
import lombok.*;
@Data
public class Message implements Serializable {

     private String textContent;
     private String    file;
     private String sender;
     private String ip;




    public Message(String textContent, String file) {
        this.textContent = textContent;
        this.file = file;

    }


}
