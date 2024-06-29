package com.insta.Instagram_clone.model;
// import com.insta.insta.models.Account;

import com.insta.Instagram_clone.model.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("BusinessAccount")
public class BusinessAccount extends Account1 implements BusinessAcc {
    public float bill;
    public String company;

    // Constructor
    public BusinessAccount() {
        this.bill = 1000; // Default value for bill
    }

    // Getter for bill
    public float getBill() {
        return bill;
    }

    // Setter for bill
    public void setBill(float bill) {
        this.bill = bill;
    }

    // Getter for company
    public String getCompany() {
        return company;
    }

    // Setter for company
    public void setCompany(String company) {
        this.company = company;
    }

    // Other methods (display, payBill, updateBill, shop, displayPosts)...

    public void display() {
        System.out.println(this.company + " " + this.username + " " + this.UserId + " " + this.password + " "
                + this.password + " " + this.bill);
        // this.posts.dispay();
    }

    @Override
    public void payBill() {
        System.out.println("Paying bill");
    }

    public void updateBill() {
        System.out.println("Updating bill");
    }

    @Override
    public void shop() {
        System.out.println("Shop!");
    }

    @Override
    public void displayPosts() {
        System.out.println("Displaying posts");
    }
}
