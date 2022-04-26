package com.example.swe311projecta.View.StartUp;

import com.example.swe311projecta.Core.ModelFactory;
import com.example.swe311projecta.Core.ViewModelFactory;
import com.example.swe311projecta.model.FileIO;
import com.example.swe311projecta.model.User;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class StartUpViewModel {
    private ViewModelFactory viewModelFactory;
    private FileIO fileIO;
    private User user;
    private StringProperty name=new SimpleStringProperty();
    private IntegerProperty port=new SimpleIntegerProperty();
    private StringProperty ip=new SimpleStringProperty();
    private IntegerProperty adminPort=new SimpleIntegerProperty();
    private StringProperty adminIp=new SimpleStringProperty();
    private StringProperty password=new SimpleStringProperty();

    public StartUpViewModel(ViewModelFactory viewModelFactory,FileIO fileIO, User user) {
        this.fileIO = fileIO;
        this.user = user;
        this.viewModelFactory=viewModelFactory;
    }

    public void CreateUser() {
        user=new User(name.get(),ip.get(),password.get(),port.get(),adminIp.get(),adminPort.get());
        viewModelFactory.getMf().setUser(user);
    }

    public void loadUser(){

        try {
            user=fileIO.fileToUser(password.get());
            user.init(getPassword());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }
    public void setFile(File file){
        fileIO.setFile(file);
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getPort() {
        return port.get();
    }

    public IntegerProperty portProperty() {
        return port;
    }

    public String getIp() {
        return ip.get();
    }

    public StringProperty ipProperty() {
        return ip;
    }

    public int getAdminPort() {
        return adminPort.get();
    }

    public IntegerProperty adminPortProperty() {
        return adminPort;
    }

    public String getAdminIp() {
        return adminIp.get();
    }

    public StringProperty adminIpProperty() {
        return adminIp;
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
