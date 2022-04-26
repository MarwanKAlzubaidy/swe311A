package com.example.swe311projecta.View;

import com.example.swe311projecta.model.Contact;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ContactViewModel {
    private Contact contact;
    private StringProperty name=new SimpleStringProperty();
    private IntegerProperty port=new SimpleIntegerProperty();
    private StringProperty  ip=new SimpleStringProperty();

    public ContactViewModel(Contact contact) {
        this.contact = contact;
        name.setValue(contact.getName());
        port.setValue(contact.getPort());
        ip.setValue(contact.getIp());
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
