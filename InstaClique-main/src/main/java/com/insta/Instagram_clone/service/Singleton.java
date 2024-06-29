package com.insta.Instagram_clone.service;

public class Singleton {

    // Private static instance variable
    private static Singleton instance;
    // Fields for username and password
    private String username;
    private String password;
    private String userID;

    // Private constructor to prevent instantiation from outside
    private Singleton() {
    }

    // Public static method to get the singleton instance
    public static synchronized Singleton getInstance() {
        // Lazy initialization: create the instance only when needed
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // Setter method for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter method for username
    public String getUsername() {
        return username;
    }

    public void setUserID(String userid) {
        this.userID = userid;
    }

    public String getuserID() {
        return userID;
    }

    // Setter method for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter method for password
    public String getPassword() {
        return password;
    }
}
