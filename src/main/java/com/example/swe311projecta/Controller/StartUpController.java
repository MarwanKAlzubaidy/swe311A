package com.example.swe311projecta.Controller;

import com.example.swe311projecta.Model.FileIO;
import com.example.swe311projecta.MainApplication;
import com.example.swe311projecta.Model.SharedResources;
import com.example.swe311projecta.Model.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class StartUpController {
    SharedResources sharedResources = SharedResources.getInstance();
    private File file;
    private FileIO fileIO;
    private User user;
    @FXML private AnchorPane anchorPane;
    @FXML TextField name;
    @FXML TextField ip;
    @FXML TextField adminIP;
    @FXML TextField port;
    @FXML TextField adminPort;
    @FXML PasswordField password;
    @FXML Label fileName;

    public void CreateNewUser(ActionEvent actionEvent) throws IOException {
        user = new User(name.getText(),
            ip.getText(),
            password.getText(),
            Integer.parseInt(port.getText()),
            adminIP.getText(),
            Integer.parseInt(adminPort.getText())
        );
        sharedResources.setUser(user);
        sharedResources.setFileIO(new FileIO(new File("user.data")));

        anchorPane.getScene().getWindow().hide();
        // Opening another view
        openUserView();
    }

    public void loadUser(ActionEvent actionEvent) throws IOException {
        try {
            sharedResources.setUser(fileIO.fileToUser(password.getText()));
            sharedResources.setUser(new User(password.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Platform.exit();
        openUserView();
    }

    public void setFile(ActionEvent actionEvent) {
        File file = new FileChooser().showOpenDialog(new Popup());
        if(file == null){
            fileName.setVisible(true);
            fileName.setText("no file selected");
        }
        else {
            sharedResources.setFileIO(new FileIO(file));
            fileName.setVisible(true);
            fileName.setText("filename: " + file.getName());
        }
    }
    
    private void openUserView() throws IOException {
        // Opening another view
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml/UserView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Chat App");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void initialize() {
//        name.textProperty().bindBidirectional(
//            new SimpleStringProperty(user.getName()));
//        ip.textProperty().bindBidirectional(
//            new SimpleStringProperty(user.getIp()));
//        adminIP.textProperty().bindBidirectional(
//            new SimpleStringProperty(user.getAdminIp()));
//
//        port.textProperty().bindBidirectional(
//            new SimpleStringProperty(String.valueOf(user.getPort())));
//        adminPort.textProperty().bindBidirectional(
//            new SimpleStringProperty(String.valueOf(user.getAdminPort())));
//        password.textProperty().bindBidirectional(
//            new SimpleStringProperty(user.getPassword())
//        );
    }
}
