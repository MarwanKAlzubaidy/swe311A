package com.example.swe311projecta;

import com.example.swe311projecta.Core.ViewHandler;
import com.example.swe311projecta.View.ContactViewModel;
import com.example.swe311projecta.View.MessageViewModel;
import com.example.swe311projecta.View.UserViewModel;
import com.example.swe311projecta.model.Contact;
import com.example.swe311projecta.model.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;


public class UserController  {
    //ViewMOdel
    private UserViewModel userViewModel;
    private ViewHandler viewHandler;
    @FXML TextField name;
    @FXML TextField ip;
    @FXML TextField adminIp;
    @FXML TextField port;
    @FXML TextField adminPort;
    @FXML TableView<ContactViewModel> contactTable;
    @FXML TableColumn<ContactViewModel, Integer> contPort;

    @FXML TableColumn<ContactViewModel, String> contName;
    @FXML TableColumn<ContactViewModel, String> contIp;


    @FXML
    TextArea messText ;
    @FXML
    ListView<MessageViewModel> chatList;
    @FXML
    Button sendBT;
    @FXML
    Button selectFileBT;
    @FXML
    Button clearFileBT;

    String file="";
    @FXML Button refresh;

    public void init(ViewHandler viewHandler,UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
        this.viewHandler=viewHandler;
        name.textProperty().bindBidirectional(userViewModel.nameProperty() );
        ip.textProperty().bindBidirectional(userViewModel.ipProperty());
        adminIp.textProperty().bindBidirectional(userViewModel.adminIpProperty());
        StringConverter s=new IntegerStringConverter();
        port.textProperty().bindBidirectional(userViewModel.portProperty(), s);
        adminPort.textProperty().bindBidirectional(userViewModel.adminPortProperty(),s );
        contPort.setCellValueFactory(new PropertyValueFactory<>("port"));
        contName.setCellValueFactory(new PropertyValueFactory<>("name"));
        contIp.setCellValueFactory(new PropertyValueFactory<>("ip"));
        contactTable.setItems(userViewModel.getContacts());



    }


    public void setValues(ActionEvent actionEvent) {
        userViewModel.setUserValues();
    }


    public void selectContact(MouseEvent mouseEvent) {
        if(contactTable.getSelectionModel().getSelectedItem()!=null)
        chatList.setItems(contactTable.getSelectionModel().getSelectedItem().getMessages());
        System.out.println("done");

    }

    public void sendMessage(ActionEvent actionEvent) {
        if(contactTable.getSelectionModel().getSelectedItem()!=null)
        userViewModel.sendMessage(contactTable.getSelectionModel().getSelectedItem().getContact(),messText.getText(),file);

    }

    public void selectFile(ActionEvent actionEvent) {
    }

    public void clearFile(ActionEvent actionEvent) {
    }

    public void refresh(ActionEvent actionEvent) {
        userViewModel.updateConatcts();

    }
}
