package com.example.swe311projecta;

import com.example.swe311projecta.Core.ModelFactory;
import com.example.swe311projecta.Core.ViewHandler;
import com.example.swe311projecta.Core.ViewModelFactory;
import com.example.swe311projecta.Model.FileIO;
import com.example.swe311projecta.Model.User;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PTPChat extends Application {
    ModelFactory mf = new ModelFactory();

    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.keyStore","myKeyStore.jks");

        System.setProperty("javax.net.ssl.trustStore","myTrustStore.jts");

        // System.setProperty("javax.net.debug","all");
        System.setProperty("javax.net.ssl.trustStorePassword","123456");
        System.setProperty("javax.net.ssl.keyStorePassword","123456");
        PTPChat.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler viewHandler = new ViewHandler(vmf);
        viewHandler.start();
    }
    @Override
    public void stop(){

        System.out.println("Stage is closing");
        FileIO fileIO = mf.getFileIO();
        User user = mf.getUser();
        try {
            if(fileIO.getFile().exists()){
                fileIO.saveUser(user,user.getPassword());
            }
            else {
                System.out.println("Can't save user");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}
