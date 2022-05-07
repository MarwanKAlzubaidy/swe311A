package com.example.swe311projecta.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.swe311projecta.Core.ViewHandler;
import com.example.swe311projecta.ViewModel.EditUserInfoViewModel;
import com.example.swe311projecta.ViewModel.StartUpViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

public class EditUserInfoController {
  public TextField password;
  public TextField adminIp;
  public TextField adminPort;
  ViewHandler viewHandler;
  EditUserInfoViewModel editUserInfoViewModel;
  @FXML TextField ip;
  @FXML TextField name;
  @FXML TextField port;
  @FXML Button setButton;
  
  public void init(ViewHandler viewHandler, EditUserInfoViewModel editUserInfoViewModel) {
    this.viewHandler = viewHandler;
    this.editUserInfoViewModel = editUserInfoViewModel;
    name.textProperty().bindBidirectional(editUserInfoViewModel.nameProperty() );
    ip.textProperty().bindBidirectional(editUserInfoViewModel.ipProperty());
    StringConverter s=new IntegerStringConverter();
    port.textProperty().bindBidirectional(editUserInfoViewModel.portProperty(), s);
    password.textProperty().bindBidirectional(editUserInfoViewModel.passwordProperty());
    adminIp.textProperty().bindBidirectional(editUserInfoViewModel.adminIpProperty());
    adminPort.textProperty().bindBidirectional(editUserInfoViewModel.adminPortProperty(), s);

  }
  
  @FXML
  void editInfo(ActionEvent event) {
    editUserInfoViewModel.editUserInfo();
  }
  
  
  
}