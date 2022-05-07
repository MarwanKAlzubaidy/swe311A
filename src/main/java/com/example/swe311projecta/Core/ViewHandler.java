package com.example.swe311projecta.Core;

import com.example.swe311projecta.Controller.EditUserInfoController;
import com.example.swe311projecta.Controller.UserController;
import com.example.swe311projecta.Controller.StartUpController;
import com.example.swe311projecta.ViewModel.EditUserInfoViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ViewHandler {

    private final ViewModelFactory vmf;
    private final Stage stage;
    private StartUpController startUpController;
    private Scene StartUpScene;

    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
        stage = new Stage();
    }
    public void start(){
        openStartUpView();
        stage.show();
    }

    private void openStartUpView() {
        try {
        FXMLLoader loader=new FXMLLoader();

        loader.setLocation(getClass().getResource("/fxml/StartUpView-v2.fxml"));

            Parent root = loader.load();
            StartUpController startUpController=loader.getController();

            startUpController.init(this,vmf.getStartUpViewModel());
            StartUpScene=new Scene(root);
            stage.setScene(StartUpScene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void openUserView(){
        try {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/UserView-v2.fxml"));
            Parent root = loader.load();
        UserController userController=loader.getController();

        userController.init(this,vmf.getUserViewModel());



            Scene userViewScene=new Scene(root);
            stage.setScene(userViewScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }
    
    public void openEditUserInfoView(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/EditUserInfo.fxml"));
            Parent root = loader.load();
            EditUserInfoController EditUserInfoController = loader.getController();
    
            EditUserInfoController.init(this, vmf.getEditUserInfoViewModel());
            
            Scene userViewScene = new Scene(root);
            Stage stage1=new Stage();
            stage1.setScene(userViewScene);
            stage1.show();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
