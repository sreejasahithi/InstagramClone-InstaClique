package com.insta.Instagram_clone.service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessAccountService {

    private final BusinessAccountRepository businessAccountRepository;

    @Autowired
    public BusinessAccountService(BusinessAccountRepository businessAccountRepository) {
        this.businessAccountRepository = businessAccountRepository;
    }

    // Method to save or update a BusinessAccount
    public void saveBusinessAccount(BusinessAccount businessAccount) {
        businessAccountRepository.save(businessAccount);
    }

    // Method to find a BusinessAccount by userId
    public BusinessAccount findByUserId(String UserId) {
        System.out.println("Fetching....." + UserId);
        return businessAccountRepository.findByUserId(UserId);
    }

    // Additional service methods can be added here for specific business logic

}
