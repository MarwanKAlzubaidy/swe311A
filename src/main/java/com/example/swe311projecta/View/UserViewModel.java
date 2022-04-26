package com.example.swe311projecta.View;

import com.example.swe311projecta.model.User;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserViewModel {
    private User user;
    private StringProperty name=new SimpleStringProperty();
    private IntegerProperty port=new SimpleIntegerProperty();
    private StringProperty ip=new SimpleStringProperty();
    private IntegerProperty adminPort=new SimpleIntegerProperty();
    private StringProperty adminIp=new SimpleStringProperty();



    private ObservableList<ContactViewModel> contacts = FXCollections.observableArrayList();

    public UserViewModel(User user) {
        this.user = user;

        refresh();
    }

    public void setUserValues(){
        System.out.println(user);
        user.setName(name.getValue());
        user.setIp(ip.getValue());
        user.setAdminIp(adminIp.getValue());
        user.setPort(adminPort.getValue());
        user.setAdminPort(adminPort.getValue());
        System.out.println(user);

    }
    public void refresh(){
        name.setValue(user.getName());
        ip.setValue(user.getIp());
        port.setValue(user.getPort());
        adminIp.setValue(user.getAdminIp());
        adminPort.setValue(user.getAdminPort());
        user.getContacts().forEach(contact -> {
            contacts.add(new ContactViewModel(contact));
        });


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
}
