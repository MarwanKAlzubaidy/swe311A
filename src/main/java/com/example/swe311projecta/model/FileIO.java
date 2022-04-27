package com.example.swe311projecta.model;

import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.example.swe311projecta.model.encryption;
import lombok.Data;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;

@Data
public class FileIO {
    private  File file=new File("user.data");



    public  User fileToUser(String password) throws IOException, ClassNotFoundException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file));
        SealedObject sealedObject= (SealedObject) objectInputStream.readObject();
        objectInputStream.close();

        return  encryption.decUser(password,sealedObject);


    }
    public void saveUser(User user,String password) throws IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(file));
        SealedObject sealedObject=encryption.encryptUser(password,user);
        objectOutputStream.writeObject(sealedObject);
        objectOutputStream.close();


    }

}
