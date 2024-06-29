package com.insta.Instagram_clone.service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Account1Service {

    @Autowired
    private Account1Repository accountRepository;

    public void saveAccount(Account1 account) {
        accountRepository.save(account);
    }

    public Account1 findByUserId(String UserId) {
        System.out.println("Fetching Account for UserId: " + UserId);
        return accountRepository.findByUserId(UserId);
    }
}
