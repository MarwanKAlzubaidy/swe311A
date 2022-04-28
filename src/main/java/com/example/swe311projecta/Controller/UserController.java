package com.example.swe311projecta.Controller;

import com.example.swe311projecta.MainApplication;
import com.example.swe311projecta.Model.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class UserController  {
    SharedResources sharedResources = SharedResources.getInstance();
    private FileIO fileIO;
    private User user;
    
    public Button selectSaveLoc;
    public Button sendToAdminButton;
    public Label saveLocation;
    @FXML
    private PasswordField password;
    private String fileUTF="";
    String fileName="";
    Timeline timeline;
    
    @FXML TextField name;
    @FXML TextField ip;
    @FXML TextField adminIp;
    @FXML TextField port;
    @FXML TextField adminPort;
    @FXML TableView<Contact> contactTable;
    @FXML TableColumn<Contact, Integer> contPort;
    @FXML TableColumn<Contact, String> contName;
    @FXML TableColumn<Contact, String> contIp;
    @FXML TextArea messText ;
    @FXML ListView<Message> chatList;
    @FXML Button sendBT;
    @FXML Button selectFileBT;
    @FXML Button clearFileBT;
    @FXML Label fileNameLabel;
    @FXML Button saveFileContentBT;


    public void selectContact() {
        if(contactTable.getSelectionModel().getSelectedItem() != null){
            chatList.setItems(contactTable.getSelectionModel().getSelectedItem().getMessages());
        }
    }

    public void sendMessage(ActionEvent actionEvent) {
        if(contactTable.getSelectionModel().getSelectedItem() != null){
            user.sendMessage(contactTable.getSelectionModel().getSelectedItem(),
                messText.getText(),
                fileUTF,
                fileName);
            
            clearFile();
            messText.setText("");
        }
    }

    public void selectFile(ActionEvent actionEvent) {
        try {
            fileUTF="";
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Popup());
            if (file.exists()) {
                return;
             }
            
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] fileBytes = fileInputStream.readAllBytes();
            fileUTF = Base64.getEncoder().encodeToString(fileBytes);
            fileInputStream.close();
            
            
            fileNameLabel.setText("File Name: " + file.getName());
            fileName = file.getName();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearFile() {
        fileUTF = "";
        fileNameLabel.setText("File Name:");
    }

    
    // what's j and what's i????
    public void refresh() {
        int i = contactTable.getSelectionModel().getFocusedIndex();
        sharedResources.getUser().updateContacts();
        
        int j = chatList.getSelectionModel().getSelectedIndex();

        if(i != -1) {
            contactTable.getSelectionModel().select(i);
            selectContact();
        }
        
        if(j != -1) {
            chatList.getSelectionModel().select(j);
        }
    }

    public void saveMessage(ActionEvent actionEvent) throws FileNotFoundException {
        Message message = chatList.getSelectionModel().getSelectedItem();
        
        if(message != null){
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(new Popup());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
            
            byte[] fileBytes = Base64.getDecoder().decode(message.getFileContent());
            
            try {
                dataOutputStream.write(fileBytes);
                dataOutputStream.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    
    public void selectSaveLocation(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showSaveDialog(new Popup());

        sharedResources.setFileIO(new FileIO(file));
        saveLocation.setVisible(true);
        saveLocation.setText("save Location: "+ file.getPath());
    }
    
    @FXML
    void goToEditInfoScene(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml/EditUserInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Edit info");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    void exitApp(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML
    public void initialize() {
        
        contPort.setCellValueFactory(new PropertyValueFactory<>("port"));
        contName.setCellValueFactory(new PropertyValueFactory<>("name"));
        contIp.setCellValueFactory(new PropertyValueFactory<>("ip"));
    
        
        ObservableList<Contact> contactList =
            FXCollections.observableArrayList(sharedResources.getUser().getContacts());
        contactTable.setItems(contactList);
        
//        password.textProperty().bindBidirectional(
//            new SimpleStringProperty(
//                sharedResources.getUser().getPassword()
//            ));
        
//        saveLocation.setText("Save Location " + sharedResources.getFileIO().getFile().getPath());
//
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> refresh()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        
//        sharedResources.getUser().listen();
    }
}
