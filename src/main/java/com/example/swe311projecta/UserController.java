package com.example.swe311projecta;

import com.example.swe311projecta.Core.ViewHandler;
import com.example.swe311projecta.View.ContactViewModel;
import com.example.swe311projecta.View.UserViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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



}
