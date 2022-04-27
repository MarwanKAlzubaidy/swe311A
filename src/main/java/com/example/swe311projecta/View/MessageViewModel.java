package com.example.swe311projecta.View;

import com.example.swe311projecta.model.Message;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class MessageViewModel {
    private Message message;
    private StringProperty textContent=new SimpleStringProperty();
    private StringProperty sender=new SimpleStringProperty();
    private StringProperty fileContent=new SimpleStringProperty();
    private StringProperty date=new SimpleStringProperty();
    private StringProperty fileName=new SimpleStringProperty();

    public MessageViewModel(Message message) {
        this.message = message;
        textContent.setValue(message.getTextContent());
        sender.setValue(message.getSender());
        fileContent.setValue(message.getFile());
        date.setValue(message.getTimestamp());
        fileName.setValue(message.getFileName());
    }

    public String getTextContent() {
        return textContent.get();
    }

    public StringProperty textContentProperty() {
        return textContent;
    }

    public String getSender() {
        return sender.get();
    }

    public StringProperty senderProperty() {
        return sender;
    }

    public String getFileContent() {
        return fileContent.get();
    }

    public StringProperty fileContentProperty() {
        return fileContent;
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public String getFileName() {
        return fileName.get();
    }

    public StringProperty fileNameProperty() {
        return fileName;
    }

    @Override
    public String toString() {
        if (fileName.get().equals(""))
        return String.format("[%s] %s: %s",getDate(),getSender(),getTextContent());
        else
            return String.format("[%s][%s] %s: %s",getDate(),getFileName(),getSender(),getTextContent());
    }
}
