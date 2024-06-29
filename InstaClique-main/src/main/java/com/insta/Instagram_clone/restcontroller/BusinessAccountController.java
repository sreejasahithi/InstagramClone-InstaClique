package com.insta.Instagram_clone.restcontroller;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import com.insta.Instagram_clone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BusinessAccountController {

    private final BusinessAccountService businessAccountService;

    @Autowired
    public BusinessAccountController(BusinessAccountService businessAccountService) {
        this.businessAccountService = businessAccountService;
    }

    public AccountFactoryImpl fact;

    @GetMapping("/bus")
    public BusinessAccount getAccountDetails() {
        String USER_NAME = Singleton.getInstance().getUsername();
        System.out.println("Fetching for..." + "1");
        // what to do later
        // userId = getInstance();
        // BusinessAccount = businessAccountService.findByUserId(UserId)

        BusinessAccount account = businessAccountService.findByUserId(USER_NAME);
        // BusinessAccount account1 = fact.createAccount("Business");
        BusinessAccount account1 = account;
        account.setUsername("someone");
        account1.display();
        return account;
    }

    // @GetMapping("/list")
    // public List<Integer> getList() {
    // String USER_NAME = Singleton.getInstance().getUsername();
    // BusinessAccount account = businessAccountService.findByUserId(USER_NAME);
    // // account.posts.display();
    // System.out.println(account);
    // return account.posts.getPostList();
    // }

    @GetMapping("/pay/{userId}")
    public float getBill(@PathVariable("UserId") String UserId) {
        // String USER_NAME = Singleton.getInstance().getUsername();
        String USER_NAME = Singleton.getInstance().getUsername();
        BusinessAccount account = businessAccountService.findByUserId(USER_NAME);
        float bill = account.bill;
        return bill;
    }

    @PostMapping("/busAcc")
    public ResponseEntity<String> submitForm(@RequestBody BusinessAccount formData) {
        // Save the BusinessAccount object to the database (MongoDB or other)
        String USER_NAME = Singleton.getInstance().getUsername();
        formData.setUserId(USER_NAME);
        businessAccountService.saveBusinessAccount(formData);
        return ResponseEntity.ok("Form submitted successfully");
    }

    @PostMapping("/updateBill")
    public ResponseEntity<String> updateBill(@RequestParam("amt") float amt) {
        try {
            // Retrieve BusinessAccount by UserId "1"
            String USER_NAME = Singleton.getInstance().getUsername();
            BusinessAccount businessAccount = businessAccountService.findByUserId(USER_NAME);

            if (businessAccount != null) {
                // Get current bill amount
                float currentBill = businessAccount.getBill();
                // Calculate new bill amount after payment
                float newBill = Math.max(0, currentBill - amt);
                // Update bill amount
                businessAccount.setBill(newBill);

                // Save the updated BusinessAccount object
                businessAccountService.saveBusinessAccount(businessAccount);

                return ResponseEntity.ok("Bill updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating bill: " + e.getMessage());
        }
    }
}
