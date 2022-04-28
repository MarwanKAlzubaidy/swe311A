package com.example.swe311projecta.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import lombok.Data;
import javax.crypto.SealedObject;

@Data
public class FileIO {
    private  File file;
    
    public FileIO(File file) {
        this.file = file;
    }

    public User fileToUser(String password) throws Exception {
        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream(file));
        SealedObject sealedObject= (SealedObject) objectInputStream.readObject();
        objectInputStream.close();

        return  encryption.decryptUser(password,sealedObject);
    }
    
    
    public void saveUser(User user,String password) throws Exception {
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream(file));
        SealedObject sealedObject=encryption.encryptUser(password,user);
        objectOutputStream.writeObject(sealedObject);
        objectOutputStream.close();

    }

}
