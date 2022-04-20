package com.example.swe311projecta.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
@Data
public class User implements Serializable {
   private ArrayList<Contact> contacts ;
    private ArrayList<Contact> approvedContacts ;
    private transient String   password;
    private  MessageReciver messageReciver;
    private int port;
    private String adminIp;
    private int adminPort;




}
