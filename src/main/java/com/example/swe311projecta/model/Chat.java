package com.example.swe311projecta.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class Chat implements Serializable {
    boolean newMessage;
    ArrayList<Message> messages ;

}
