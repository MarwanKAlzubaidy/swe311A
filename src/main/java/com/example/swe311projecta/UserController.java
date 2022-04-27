package com.example.swe311projecta;

import com.example.swe311projecta.Core.ViewHandler;
import com.example.swe311projecta.View.ContactViewModel;
import com.example.swe311projecta.View.MessageViewModel;
import com.example.swe311projecta.View.UserViewModel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.util.Base64;


public class UserController  {
    public Button selectSaveLoc;
    public Button sendToAdminButton;
    public Label saveLocation;
    public PasswordField password;
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

    String fileUTF="";
    @FXML
    Label fileNameLabel;
    String fileName="";
    @FXML
    Button saveFileContentBT;
    Timeline timeline;

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
        password.textProperty().bindBidirectional(userViewModel.passwordProperty());
        saveLocation.setText("Save Location"+userViewModel.getFileSaveLocation());

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae) {
                        refresh();
                    }
                }
        ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }


    public void setValues(ActionEvent actionEvent) {
        userViewModel.setUserValues();
    }


    public void selectContact() {
        if(contactTable.getSelectionModel().getSelectedItem()!=null)
        chatList.setItems(contactTable.getSelectionModel().getSelectedItem().getMessages());
        System.out.println("done");

    }

    public void sendMessage(ActionEvent actionEvent) {
        if(contactTable.getSelectionModel().getSelectedItem()!=null)
        userViewModel.sendMessage(contactTable.getSelectionModel().getSelectedItem().getContact(),messText.getText(),fileUTF, fileName);
        clearFile();
        messText.setText("");
    }

    public void selectFile(ActionEvent actionEvent) {
        try {
            fileUTF="";
        FileChooser fileChooser=new FileChooser();
        File file=fileChooser.showOpenDialog(new Popup());
        if (file==null) {
            return;
        }

        FileInputStream fileInputStream=new FileInputStream(file);
        byte[] fileBytes=fileInputStream.readAllBytes();
            fileUTF=Base64.getEncoder().encodeToString(fileBytes);

            fileInputStream.close();
            fileNameLabel.setText("File Name:"+ file.getName());
            fileName=file.getName();

        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearFile() {
        fileUTF="";
        fileNameLabel.setText("File Name:");
    }

    public void refresh() {
        int i=contactTable.getSelectionModel().getFocusedIndex();
        userViewModel.updateConatcts();
        int j=chatList.getSelectionModel().getSelectedIndex();

        if(i!=-1)
        {contactTable.getSelectionModel().select(i);
        selectContact();
        }
        if(j!=-1){
            chatList.getSelectionModel().select(j);
        }




    }

    public void saveMessage(ActionEvent actionEvent) throws FileNotFoundException {
            MessageViewModel message=chatList.getSelectionModel().getSelectedItem();
        if(message!=null){
            FileChooser fileChooser=new FileChooser();

            File file=fileChooser.showSaveDialog(new Popup());
            FileOutputStream fileOutputStream= new FileOutputStream(file);
            DataOutputStream dataOutputStream=new DataOutputStream(fileOutputStream);
            byte[] fileBytes=Base64.getDecoder().decode(message.getFileContent());
            try {
                dataOutputStream.write(fileBytes);

                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void selectSaveLocation(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser();
        File file=fileChooser.showSaveDialog(new Popup());
        userViewModel.setSaveLocation(file);
        saveLocation.setText("save Location:"+ file.getPath());
    }
}
