package com.example.swe311projecta.model;

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

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("javax.net.ssl.keyStore","myKeyStore.jks");

        System.setProperty("javax.net.ssl.trustStore","myTrustStore.jts");

        // System.setProperty("javax.net.debug","all");
        System.setProperty("javax.net.ssl.trustStorePassword","123456");
        System.setProperty("javax.net.ssl.keyStorePassword","123456");
        User user1=new User("u1","127.0.0.1","1",5454,"1925",0);
        User user2=new User("u2","127.0.0.1","1",5434,"1925",0);
        ArrayList<Contact> contactArrayList=new ArrayList<>();
        Contact self1=new Contact(user1.ip, user1.name, user1.port);
        Contact self2=new Contact(user2.ip, user2.name, user2.port);

        contactArrayList.add(self1);
        contactArrayList.add(self2);
        user1.setApprovedContacts(contactArrayList);
        user2.setApprovedContacts(contactArrayList);
        user1.getContacts().forEach(contact -> System.out.println(contact.getName()+contact.getPort()));
        user1.listen();
        user2.listen();
        Message message=new Message("High world","");
        Thread.sleep(4000);
        user2.sendMessage(user2.getContacts().get(0),message);
        Thread.sleep(4000);
        user1.getContacts().get(1).getChat().getMessages().forEach(message1 -> System.out.println(message.getTextContent()));
        user2.getContacts().get(0).getChat().getMessages().forEach(message1 -> System.out.println(message.getTextContent()));

        System.out.println("After");

    }

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
        contacts.add(new Contact("192.152.12.1","place holder",912));
        System.out.println("mew");
    }

    public void init(String password) {
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
            return  (User) encryption.decUser(password,sealedObject);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IOException | InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void loadUser() {


    }
    public void sendMessage(Contact receiver,Message message ){

        message.setIp(ip);
        message.setSender(name);
        ObjectSender sender=new ObjectSender(message, receiver.getIp(), receiver.getPort());
        sender.start();
        addMessageToContactChat(receiver,message);




    }
    public void addMessageToContactChat(Contact contact,Message message){
        contact.getChat().getMessages().add(message);


    }
    public void receiveMessage(Message message){
        Contact reciver=matchMessageSender(message.getIp(), message.getSender());
        if(reciver!=null)
        if (contacts.contains(reciver))
            reciver.getChat().getMessages().add(message);

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
            messageReceiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendForm(){
        Contact self=new Contact(ip,name,port);
        ObjectSender objectSender=new ObjectSender(self,adminIp,adminPort);
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




