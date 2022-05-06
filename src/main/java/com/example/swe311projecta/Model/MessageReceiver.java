package com.example.swe311projecta.Model;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MessageReceiver extends Thread{
    private static final String[] protocols = new String[]{"TLSv1.3"};
    private static final String[] cipher_suites = new String[]{"TLS_AES_128_GCM_SHA256"};
    private SSLServerSocket sslServerSocket;
    private User user;

    public MessageReceiver(User user) {
        this.user = user;
    }

    public void start(int port) throws IOException {
        sslServerSocket = (SSLServerSocket) SSLServerSocketFactory.getDefault().createServerSocket(port);
        sslServerSocket.setEnabledCipherSuites(cipher_suites);
        sslServerSocket.setEnabledProtocols(protocols);
        sslServerSocket.setNeedClientAuth(false);
       }
       public void run(){
        System.out.println("Starting Listening At: "+ user.getIp() +":"+user.getPort());
           while (true) {
               try {
                   new EchoClientHandler((SSLSocket) sslServerSocket.accept()).start();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }


    private  class EchoClientHandler extends Thread {
        private SSLSocket clientSocket;
        private ObjectOutputStream out;
        private ObjectInputStream in;

        public EchoClientHandler(SSLSocket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {


            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            Object o=in.readObject();

            if(o instanceof Message)
            {
            user.receiveMessage((Message) o);

            }
            else {

                ArrayList<Contact> adminNewList=(ArrayList<Contact>)o;
                if(user.getAdminIp().equals(clientSocket.getInetAddress().getHostAddress()))
                    user.setApprovedContacts(adminNewList);

            }
            out.writeObject("OK");


            in.close();
            out.close();
            clientSocket.close();}
            catch (Exception e){

                e.printStackTrace();

            }
        }
    }



}

