package com.insta.Instagram_clone.model;
// import com.insta.insta.models.Account;

import com.insta.Instagram_clone.model.*;

public class UserId {

    private static UserId instance;

    // Private constructor to prevent instantiation from outside
    private UserId() {
        // Constructor implementation (if needed)
    }

    // Static method to get the singleton instance of UserId
    public static UserId getInstance() {
        if (instance == null) {
            synchronized (UserId.class) {
                if (instance == null) {
                    instance = new UserId();
                }
            }
        }
        return instance;
    }

    // Method to return the fixed user ID string "6"
    public String getUserId() {
        return "6";
    }
}
