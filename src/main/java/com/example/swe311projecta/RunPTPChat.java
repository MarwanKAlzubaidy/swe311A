package com.example.swe311projecta;

import javafx.application.Application;

public class RunPTPChat {


    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.keyStore","myKeyStore.jks");

        System.setProperty("javax.net.ssl.trustStore","myTrustStore.jts");

        // System.setProperty("javax.net.debug","all");
        System.setProperty("javax.net.ssl.trustStorePassword","123456");
        System.setProperty("javax.net.ssl.keyStorePassword","123456");
        Application.launch(PTPChat.class);
    }
}