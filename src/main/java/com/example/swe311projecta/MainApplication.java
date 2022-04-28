package com.example.swe311projecta;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainApplication extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("/fxml/StartUpView.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    stage.setResizable(false);
    stage.setTitle("Chat App");
    stage.setScene(scene);
    stage.show();
  }
  
  public static void main(String[] args) {
    System.setProperty("javax.net.ssl.keyStore","myKeyStore.jks");
    System.setProperty("javax.net.ssl.trustStore","myTrustStore.jts");
    System.setProperty("javax.net.ssl.trustStorePassword","123456");
    System.setProperty("javax.net.ssl.keyStorePassword","123456");
    
    launch();
  }
}
