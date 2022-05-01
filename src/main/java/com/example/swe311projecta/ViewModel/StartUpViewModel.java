package com.example.swe311projecta.ViewModel;

import com.example.swe311projecta.Core.ViewModelFactory;
import com.example.swe311projecta.Model.FileIO;
import com.example.swe311projecta.Model.User;
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
        viewModelFactory.getModelFactory().setUser(user);
    }
    
    /*
     TODO: delete these explicit Exceptions and replace them with one Exception.
      They are only good for debugging
    */
    public void loadUser() throws IOException, InvalidKeyException, BadPaddingException, InvalidKeySpecException,
    NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException,
    InvalidAlgorithmParameterException, ClassNotFoundException {
        
            user = fileIO.fileToUser(password.get());
            user.init(getPassword());
            viewModelFactory.getModelFactory().setUser(user);
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
