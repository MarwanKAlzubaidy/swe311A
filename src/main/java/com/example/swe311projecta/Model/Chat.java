package com.example.swe311projecta.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class Chat implements Serializable {
    boolean newMessage;
    ObservableList<Message> messages ;

    public Chat() {
        this.newMessage = false;
        this.messages= FXCollections.observableArrayList();
    }
}
