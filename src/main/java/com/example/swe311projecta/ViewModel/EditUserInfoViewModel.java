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
  
  public EditUserInfoViewModel(ViewModelFactory viewModelFactory,FileIO fileIO, User user) {
    this.fileIO = fileIO;
    this.user = user;
    this.viewModelFactory = viewModelFactory;
  }
  
  public void editUserInfo() {
    user.setName(name.getValue());
    user.setIp(ip.getValue());
    user.setPort(port.getValue());
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
}
