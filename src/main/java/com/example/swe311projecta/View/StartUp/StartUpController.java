package com.example.swe311projecta.View.StartUp;

import com.example.swe311projecta.Core.ViewHandler;
import com.example.swe311projecta.View.UserViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;

public class StartUpController {
    private StartUpViewModel startUpViewModel;
    private ViewHandler viewHandler;
    private File file;
    @FXML TextField name;
    @FXML TextField ip;
    @FXML TextField adminIP;
    @FXML TextField port;
    @FXML TextField adminPort;
    @FXML PasswordField password;
    @FXML
    Label fileName;
    public  void init(ViewHandler viewHandler, StartUpViewModel startUpViewModel) {
        this.viewHandler=viewHandler;
        this.startUpViewModel=startUpViewModel;
        name.textProperty().bindBidirectional(startUpViewModel.nameProperty() );
        ip.textProperty().bindBidirectional(startUpViewModel.ipProperty());
        adminIP.textProperty().bindBidirectional(startUpViewModel.adminIpProperty());
        StringConverter s=new IntegerStringConverter();
        port.textProperty().bindBidirectional(startUpViewModel.portProperty(), s);
        adminPort.textProperty().bindBidirectional(startUpViewModel.adminPortProperty(),s );
        password.textProperty().bindBidirectional(startUpViewModel.passwordProperty());
    }

    public void CreateNewUser(ActionEvent actionEvent) {

        startUpViewModel.CreateUser();
        viewHandler.openUserView();
    }

    public void loadUser(ActionEvent actionEvent) {
        startUpViewModel.loadUser();
        viewHandler.openUserView();
    }

    public void setFile(ActionEvent actionEvent) {
        File file=new FileChooser().showOpenDialog(new Popup());
        if(file==null)
            fileName.setText("no file selected");
        else {
            startUpViewModel.setFile(file);
            fileName.setText("filename: " + file.getName());
        }
    }
}
