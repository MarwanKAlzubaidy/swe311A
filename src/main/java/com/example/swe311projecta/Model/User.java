package com.example.swe311projecta.Model;

import lombok.Data;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Random;


@Data
public class User implements Serializable {
    private String name;
    private String ip;
    private ArrayList<Contact> contacts;

    public void setApprovedContacts(ArrayList<Contact> approvedContacts) {
        this.approvedContacts = approvedContacts;
        approvedContacts.forEach(contact ->{if(!contacts.contains(contact))contacts.add(contact);});
    }

    private ArrayList<Contact> approvedContacts;
    private transient String password;
    private transient MessageReceiver messageReceiver;
    private int port;
    private String adminIp;
    private int adminPort;



    public User(String name, String ip, String password, int port, String adminIp, int adminPort) {
        this.name = name;
        this.ip = ip;
        this.password = password;
        this.port = port;
        this.adminIp = adminIp;
        this.adminPort = adminPort;
        messageReceiver = new MessageReceiver(this);
        contacts=new ArrayList<>();
        approvedContacts=new ArrayList<>();
    


    }

    public void init(String password) {
        messageReceiver = new MessageReceiver(this);
        this.password = password;
    }





    public void sendMessage(Contact receiver,Message message) throws InterruptedException {
        System.out.println((approvedContacts.contains(receiver)));
        approvedContacts.forEach(contact -> System.out.println(contact));
        if(approvedContacts.contains(receiver)) {
            message.setIp(ip);
            message.setSender(name);

            ObjectSender sender = new ObjectSender(message, receiver.getIp(), receiver.getPort());
            sender.start();
            Thread t = new Thread() {
                public void run() {
                    try {
                        sender.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (sender.getAnswer().equals("OK"))
                        addMessageToContactChat(receiver, message);
                }
            };
            t.start();

        }else
            System.out.println("not an approved Contact");




    }
    public void addMessageToContactChat(Contact contact,Message message){
        contact.getChat().getMessages().add(message);


    }
    public void receiveMessage(Message message){
        Contact sender=matchMessageSender(message.getIp(), message.getSender());
        if(sender!=null)
        if (contacts.contains(sender)){

            sender=contacts.get(contacts.indexOf(sender));

            sender.getChat().getMessages().add(message);

        }

    }
    public Contact matchMessageSender(String ip,String name){
        Contact c=new Contact(ip,name,0);
        if (approvedContacts.contains(c))
            return approvedContacts.get(approvedContacts.indexOf(c));
        else
            return null;

    }
    public void listen(){
        try {
            messageReceiver.start(port);
            messageReceiver.setDaemon(true);
            messageReceiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendForm(){
        Contact self=new Contact(ip,name,port);
        ObjectSender objectSender=new ObjectSender(self,adminIp,adminPort);
        objectSender.start();
    }
    public void addApprovedContact(Contact contact){
        approvedContacts.add(contact);
        contacts.add(contact);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", ip='" + ip + '\'' +
                ", password='" + password + '\'' +
                ", port=" + port +
                ", adminIp='" + adminIp + '\'' +
                ", adminPort=" + adminPort +
                '}';
    }
}




