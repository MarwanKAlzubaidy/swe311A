package com.example.swe311projecta.ViewModel;

import com.example.swe311projecta.Model.Contact;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactViewModel {
    private Contact contact;
    private StringProperty name=new SimpleStringProperty();
    private IntegerProperty port=new SimpleIntegerProperty();
    private StringProperty  ip=new SimpleStringProperty();
    private ObservableList<MessageViewModel> messages=  FXCollections.observableArrayList();

    public ObservableList<MessageViewModel> getMessages() {
        return messages;
    }

    public ContactViewModel(Contact contact) {
        this.contact = contact;
        name.setValue(contact.getName());
        port.setValue(contact.getPort());
        ip.setValue(contact.getIp());
        contact.getChat().getMessages().forEach(message -> {messages.add(new MessageViewModel(message));});


    }

    public Contact getContact() {
        return contact;
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

    public void updateMessages(){
        messages.clear();
        contact.getChat().getMessages().forEach(message -> {
            messages.add(new MessageViewModel(message));
        });

    }
}
