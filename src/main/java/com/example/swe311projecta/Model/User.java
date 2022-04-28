package com.example.swe311projecta.Model;

import lombok.Data;
import javax.crypto.SealedObject;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


@Data
public class User implements Serializable {
    private String name;
    private String ip;
    private transient String password;
    private int port;
    private String adminIp;
    private int adminPort;
    
    private ArrayList<Contact> contacts;
    private ArrayList<Contact> approvedContacts;
    private transient MessageReceiver messageReceiver;
    
    
    public User(String name,
                String ip,
                String password,
                int port,
                String adminIp,
                int adminPort) {
        this.name = name;
        this.ip = ip;
        this.password = password;
        this.port = port;
        this.adminIp = adminIp;
        this.adminPort = adminPort;
        messageReceiver = new MessageReceiver(this);
        contacts = new ArrayList<>();
        approvedContacts = new ArrayList<>();
        
        addApprovedContact(new Contact("127.0.0.1","nnn",1000));
        addApprovedContact(new Contact("127.0.0.1","mmm",1005));
    }

    public User(String password) {
        messageReceiver = new MessageReceiver(this);
        this.password = password;
    }

    public SealedObject EncUser() {
        try {
            return encryption.encryptUser(password, this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public User Decrypt(SealedObject sealedObject){
        try {
            return encryption.decryptUser(password,sealedObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public void sendMessage(Contact receiver, Message message ){
        message.setIp(ip);
        message.setSender(name);
        ObjectSender sender =
            new ObjectSender(message, receiver.getIp(), receiver.getPort());
        
        sender.start();
        addMessageToContactChat(receiver,message);
    }
    
    
    public void addMessageToContactChat(Contact contact, Message message){
        contact.getChat().getMessages().add(message);
    }
    
    
    public void receiveMessage(Message message){
        Contact receiver = matchMessageSender(message.getIp(), message.getSender());
        
        if(receiver != null && contacts.contains(receiver)) {
            receiver.getChat().getMessages().add(message);
        }
    }
    
    
    public Contact matchMessageSender(String ip,String name) {
        Contact contact = new Contact(ip, name, 0);
        
        if (approvedContacts.contains(contact)) {
            return approvedContacts.get(approvedContacts.indexOf(contact));
        } else {
            return null;
        }
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
        Contact self = new Contact(ip, name, port);
        ObjectSender objectSender =
            new ObjectSender(self, adminIp, adminPort);
    }
    
    
    public void addApprovedContact(Contact contact){
        approvedContacts.add(contact);
        contacts.add(contact);
    }
    
    
    public void setApprovedContacts(ArrayList<Contact> approvedContacts) {
        this.approvedContacts = approvedContacts;
        approvedContacts.forEach(contact -> {
            if(!contacts.contains(contact)){
                contacts.add(contact);
            }
        });
    }
    
    public void sendMessage(Contact contact, String text, String fileUTF, String fileName) {
        Message message = new Message(text, fileUTF, name, ip, fileName);
        this.sendMessage(contact, message);
    }
    
    public void updateContacts(){
        contacts.clear();
        this.getContacts().forEach(contact -> {
            contacts.add(
                new Contact(contact.getIp(), contact.getName(), contact.getPort()));
        });
        
        contacts.forEach(Contact::updateMessages);
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