package com.example.swe311projecta.model;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;


public class MessageSender {
    private static final String[] protocols = new String[]{"TLSv1.3"};
    private static final String[] cipher_suites = new String[]{"TLS_AES_128_GCM_SHA256"};
    private SSLSocket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;


    char[] pwdArray = "password".toCharArray();


    public void startConnection() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException {

        clientSocket = (SSLSocket) SSLSocketFactory.getDefault().createSocket("127.0.0.1",4000);
        clientSocket.setEnabledCipherSuites(cipher_suites);
        clientSocket.setEnabledProtocols(protocols);
        clientSocket.setWantClientAuth(false);

        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());
    }

    public Object sendMessage() throws IOException, ClassNotFoundException {
        out.writeObject("ASAAA");
       return in.readObject();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException, CertificateException, KeyStoreException, NoSuchAlgorithmException {
        System.setProperty("javax.net.ssl.keyStore","myKeyStore.jks");

        System.setProperty("javax.net.ssl.trustStore","myTrustStore.jts");

       // System.setProperty("javax.net.debug","all");
        System.setProperty("javax.net.ssl.trustStorePassword","123456");
        System.setProperty("javax.net.ssl.keyStorePassword","123456");
        MessageReciver receiver=new MessageReciver();
        receiver.start(4000);
        receiver.start();

        MessageSender messageSender=new MessageSender();
        messageSender.startConnection();
        Object o=messageSender.sendMessage();
        messageSender.stopConnection();
        System.out.println(o);
        //launch();
    }

}
