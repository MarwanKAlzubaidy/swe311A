package com.example.swe311projecta.Model;

public class SharedResources {
  private User user;
  private FileIO fileIO;
  public static SharedResources instance = new SharedResources();
  
  private SharedResources() {}
  
  public static SharedResources getInstance() {
    return instance;
  }
  
  public User getUser() {
    return user;
  }
  
  public void setUser(User user) {
    this.user = user;
  }
  
  public FileIO getFileIO() {
    return fileIO;
  }
  
  public void setFileIO(FileIO fileIO) {
    this.fileIO = fileIO;
  }
}
