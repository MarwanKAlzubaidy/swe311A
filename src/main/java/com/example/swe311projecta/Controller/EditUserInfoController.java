package com.example.swe311projecta.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import com.example.swe311projecta.Model.SharedResources;
import com.example.swe311projecta.Model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditUserInfoController {
  private final SharedResources sharedResources = SharedResources.getInstance();
  
  @FXML
  private ResourceBundle resources;
  
  @FXML
  private URL location;
  
  @FXML
  private TextField ip;
  
  @FXML
  private TextField name;
  
  @FXML
  private TextField port;
  
  @FXML
  private Button setButton;
  
  @FXML
  void editInfo(ActionEvent event) {
    sharedResources.getUser().setName(name.getText());
    sharedResources.getUser().setIp(ip.getText());
    sharedResources.getUser().setPort(Integer.parseInt(port.getText()));
  }
  
  @FXML
  void initialize() {
    name.textProperty().bindBidirectional(
        new SimpleStringProperty(
            sharedResources.getUser().getName()
        ));
    ip.textProperty().bindBidirectional(
        new SimpleStringProperty(
            sharedResources.getUser().getIp()
        ));
    port.textProperty().bindBidirectional(
        new SimpleStringProperty(
            String.valueOf(sharedResources.getUser().getPort())
        ));
  }
  
}

