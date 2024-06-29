package com.insta.Instagram_clone.model;
// import com.insta.insta.models.Account;

import com.insta.Instagram_clone.model.*;
// import com.insta.insta.models.BusinessAccount;

public class AccountFactoryImpl implements AccountFactory {

    @Override
    public Account1 createAccount(String type) {
        if (type.equalsIgnoreCase("Business")) {
            return new BusinessAccount();
        } else {
            // Default to regular Account type
            return new Account1();
        }
    }
}
