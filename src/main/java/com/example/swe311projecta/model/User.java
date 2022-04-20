package com.example.swe311projecta.model;

import lombok.Data;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;


@Data
public class User implements Serializable {
    private ArrayList<Contact> contacts;
    private ArrayList<Contact> approvedContacts;
    private transient String password;
    private transient MessageReceiver messageReceiver;
    private int port;
    private String adminIp;
    private int adminPort;

    public void init(String password) {
        messageReceiver = new MessageReceiver(this);
        this.password = password;


    }


    public SealedObject EncUser() {
        try {
            return encryption.encryptUser(password, this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
    public User Decrypt(SealedObject sealedObject){

        try {
            return  (User) encryption.decUser(password,sealedObject);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IOException | InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void loadUser() {


    }
}




