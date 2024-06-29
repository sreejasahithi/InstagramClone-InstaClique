package com.insta.Instagram_clone.service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralManagerService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private EmailService emailService;

    public String disableAccount(String accountId, String reason) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            return "Account with this userid does not exist ";

        }
        if (account != null && !account.isEnabled()) {
            return "Account is already disabled for ID: " + accountId;

        }

        account.setEnabled(false);
        account.setDisabledReason(reason);
        accountRepository.save(account);

        emailService.sendAccountDisabledEmail(account.getEmail(), account.getId(), account.getUsername());

        return "Account disabled successfully";
    }

    public String enableAccount(String accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account == null) {
            return "Account with this userid does not exist ";

        }
        if (account != null && account.isEnabled()) {
            return "Account is already enabled for ID: " + accountId;

        }
        account.setEnabled(true);
        accountRepository.save(account);

        emailService.sendAccountEnabledEmail(account.getEmail(), account.getId(), account.getUsername());
        return "Account enabled successfully";
    }

    public void checkAndDisableAccount(String accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null && shouldDisableAccount(account)) {
            disableAccount(accountId, "Account has been reported due to violations");
        }
    }

    private boolean shouldDisableAccount(Account account) {
        if (account.getReportCount() > 5) {
            return true;
        }
        return false;
    }

    public void reportAccount(String accountId) {
        Account account = accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            // Increment report count
            int currentReportCount = account.getReportCount();
            account.setReportCount(currentReportCount + 1);
            accountRepository.save(account);

            if (shouldDisableAccount(account)) {
                disableAccount(accountId, "Account has been reported multiple times");
            }
        }
    }

    public Account getAccountDetails(String accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

}
