package com.insta.Instagram_clone.restcontroller;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import com.insta.Instagram_clone.service.*;
// import com.insta.insta.service.AccountService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class Account1Controller {

    @Autowired
    private Account1Service accountService;
    public AccountFactoryImpl fact;

    @PostMapping("/create")
    public String createAccount(@RequestBody Account1 account) {
        Account1 acc = fact.createAccount("Personal");
        acc = account;
        // acc.setFollowers();
        // acc.addFol("3");
        // acc.addFol("4");
        // acc.addFol("5");
        accountService.saveAccount(account);
        return "Account created successfully";
    }

    @GetMapping("/pers")
    public Account1 getAccountDetails() {
        System.out.println("Fetching for..." + "3");
        // what to do later
        // userId = getInstance();
        // BusinessAccount = businessAccountService.findByUserId(UserId)
        String USER_NAME = Singleton.getInstance().getUsername();
        Account1 account = accountService.findByUserId(USER_NAME);
        // Account1 account1 = account;
        System.out.println("ACC");
        System.out.println(USER_NAME);
        // System.out.println(account.UserId);
        System.out.println(account);

        account.display();
        return account;
    }

}
