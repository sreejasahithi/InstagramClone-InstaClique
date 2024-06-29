package com.insta.Instagram_clone.restcontroller;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import com.insta.Instagram_clone.service.*;
import com.insta.Instagram_clone.service.Singleton.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/general-manager")
public class GeneralManagerController {
    @Autowired
    private GeneralManagerService generalManagerService;
    Singleton singleton = Singleton.getInstance();
    String username = singleton.getUsername();

    @PostMapping("/disable-account/{accountId}")
    public ResponseEntity<String> disableAccount(@PathVariable String accountId, @RequestBody String reason) {
        // String accountId = Singleton.getInstance().getuserID();
        String message = generalManagerService.disableAccount(accountId, reason);
        // Singleton singleton = Singleton.getInstance();
        // String username = singleton.getUsername();
        // String password = singleton.getPassword();

        // Print the retrieved values
        System.out.println("Username: " + username);
        // return ResponseEntity.status(HttpStatus.OK).body("Account disabled
        // successfully");
        return ResponseEntity.ok(message);
    }

    @PostMapping("/enable-account/{accountId}")
    public ResponseEntity<String> enableAccount(@PathVariable String accountId) {
        // String accountId = Singleton.getInstance().getuserID();
        String message = generalManagerService.enableAccount(accountId);
        // return ResponseEntity.status(HttpStatus.OK).body("Account enabled
        // successfully");
        return ResponseEntity.ok(message);
    }

    @PostMapping("/check-and-disable-account/{accountId}")
    public ResponseEntity<String> checkAndDisableAccount(@PathVariable String accountId) {
        // String accountId = Singleton.getInstance().getuserID();

        generalManagerService.checkAndDisableAccount(accountId);
        return ResponseEntity.status(HttpStatus.OK).body("Account checked and disabled if needed");
    }

    @PostMapping("/report-account/{accountId}")
    public ResponseEntity<String> reportAccount(@PathVariable String accountId) {
        // String accountId = Singleton.getInstance().getuserID();
        generalManagerService.reportAccount(accountId);
        return ResponseEntity.status(HttpStatus.OK).body("Account reported successfully");
    }

    @GetMapping("/account-details/{accountId}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable String accountId) {
        // String accountId = Singleton.getInstance().getuserID();
        Account account = generalManagerService.getAccountDetails(accountId);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
