package com.example.swe311projecta.ViewModel;

import com.example.swe311projecta.Model.Contact;
import com.example.swe311projecta.Model.FileIO;
import com.example.swe311projecta.Model.Message;
import com.example.swe311projecta.Model.User;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class UserViewModel {
    private FileIO fileIO;
    private User user;
    private StringProperty name=new SimpleStringProperty();
    private IntegerProperty port=new SimpleIntegerProperty();
    private StringProperty ip=new SimpleStringProperty();
    private IntegerProperty adminPort=new SimpleIntegerProperty();
    private StringProperty adminIp=new SimpleStringProperty();
    private StringProperty password=new SimpleStringProperty();



    private ObservableList<ContactViewModel> contacts = FXCollections.observableArrayList();

    public UserViewModel(User user,FileIO fileIO) {
        this.user = user;
        this.fileIO=fileIO;
        refresh();
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setUserValues(){

        user.setName(name.getValue());
        user.setIp(ip.getValue());
        user.setAdminIp(adminIp.getValue());
        user.setPort(adminPort.getValue());
        user.setAdminPort(adminPort.getValue());
        user.setPassword(password.getValue());


    }
    public void refresh(){
        name.setValue(user.getName());
        ip.setValue(user.getIp());
        port.setValue(user.getPort());
        adminIp.setValue(user.getAdminIp());
        adminPort.setValue(user.getAdminPort());
        password.setValue(user.getPassword());
        updateConatcts();


    }
    
    public void goToEditInfoScene() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/EditUserInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Edit info");
        stage.setScene(scene);
        stage.show();
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

    public ObservableList<ContactViewModel> getContacts() {
        return contacts;
    }

    public void sendMessage(Contact contact, String text,String file,String fileName) {
        Message message=new Message(text,file, user.getName(), user.getIp(),fileName);
        user.sendMessage(contact,message);
    }
    public void setSaveLocation(File file){
        fileIO.setFile(file);
    }


    public void updateConatcts(){
        contacts.clear();
        user.getContacts().forEach(contact -> {
            contacts.add(new ContactViewModel(contact));
        });
        contacts.forEach(contactViewModel -> contactViewModel.updateMessages());
    }
    public void sendForm(){
        user.sendForm();
    }

    public String getFileSaveLocation() {
        return fileIO.getFile().getPath();
    }
    
    public void exitApp() {
        Platform.exit();
    }
    
}
