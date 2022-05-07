package com.example.swe311projecta.Core;

import com.example.swe311projecta.Model.FileIO;
import com.example.swe311projecta.Model.User;
import lombok.Setter;

@Setter
public class ModelFactory {
    private FileIO fileIO;
    private User user;

    public User getUser(){

        return user;
    }
    public FileIO getFileIO(){
        if (fileIO == null) {
            fileIO=new FileIO()   ;
        }
        return fileIO;
    }

    public void setUser(User user) {
        this.user = user;
        getFileIO();
        user.listen();

    }
}
