package com.example.swe311projecta.Controller;

import com.example.swe311projecta.Core.ViewHandler;
import com.example.swe311projecta.ViewModel.StartUpViewModel;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import java.io.File;

public class StartUpController {
    
    private StartUpViewModel startUpViewModel;
    private ViewHandler viewHandler;
    private File file;
    
    @FXML AnchorPane anchorPane;
    @FXML TextField name;
    @FXML TextField ip;
    @FXML TextField adminIP;
    @FXML TextField port;
    @FXML TextField adminPort;
    @FXML PasswordField password;
    @FXML Label fileName;
    @FXML Label fileErrorMsg;
    
    
    public  void init(ViewHandler viewHandler, StartUpViewModel startUpViewModel) {
        this.viewHandler = viewHandler;
        this.startUpViewModel = startUpViewModel;
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
        anchorPane.getScene().getWindow().hide();
        viewHandler.openUserView();
    }

    public void loadUser(ActionEvent actionEvent) {
        try {
            startUpViewModel.loadUser();
            viewHandler.openUserView();
            anchorPane.getScene().getWindow().hide();
        } catch(Exception e){
    
            // The error message will be shown for 10 seconds
            fileErrorMsg.setVisible(true);
            
            PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(10)
            );
            visiblePause.setOnFinished(
                event -> fileErrorMsg.setVisible(false)
            );
            
            visiblePause.play();
            
        }
    }

    public void setFile(ActionEvent actionEvent) {
        File file=new FileChooser().showOpenDialog(new Popup());
        if(file==null) {
            fileName.setVisible(true);
            fileName.setText("no file selected");
        } else {
            startUpViewModel.setFile(file);
            fileName.setVisible(true);
            fileName.setText("filename: " + file.getName());
        }
    }
}
