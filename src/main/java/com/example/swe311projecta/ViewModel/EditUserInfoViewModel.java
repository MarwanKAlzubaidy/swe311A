package com.example.swe311projecta.ViewModel;

import com.example.swe311projecta.Core.ViewModelFactory;
import com.example.swe311projecta.Model.FileIO;
import com.example.swe311projecta.Model.User;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditUserInfoViewModel {
  private ViewModelFactory viewModelFactory;
  private FileIO fileIO;
  private User user;
  private StringProperty name = new SimpleStringProperty();
  private IntegerProperty port = new SimpleIntegerProperty();
  private StringProperty ip = new SimpleStringProperty();
  private StringProperty password=new SimpleStringProperty();
  private StringProperty adminIp=new SimpleStringProperty();
  private IntegerProperty adminPort=new SimpleIntegerProperty();

  
  public EditUserInfoViewModel(ViewModelFactory viewModelFactory,FileIO fileIO, User user) {
    this.fileIO = fileIO;
    this.user = user;
    this.viewModelFactory = viewModelFactory;
    name.setValue(user.getName());
    ip.setValue(user.getIp());
    port.setValue(user.getPort());
    password.setValue(user.getPassword());
    adminIp.setValue(user.getAdminIp());
    adminPort.setValue(user.getAdminPort());
  }
  
  public void editUserInfo() {
    user.setName(name.getValue());
    user.setIp(ip.getValue());
    user.setPort(port.getValue());
    user.setPassword(password.getValue());
    user.setAdminIp(adminIp.getValue());
    user.setAdminPort(adminPort.get());
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

  public String getPassword() {
    return password.get();
  }

  public StringProperty passwordProperty() {
    return password;
  }

  public String getAdminIp() {
    return adminIp.get();
  }

  public StringProperty adminIpProperty() {
    return adminIp;
  }

  public int getAdminPort() {
    return adminPort.get();
  }

  public IntegerProperty adminPortProperty() {
    return adminPort;
  }
}
