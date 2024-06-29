package com.insta.Instagram_clone.service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import com.insta.Instagram_clone.Network.service.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class SearchFacade {
    @Autowired
    private AccountService accountService;

    @Autowired
    private NetworkService networkService;

    public SearchFacade(AccountService accountService, NetworkService networkService) {
        this.accountService = accountService;
        this.networkService = networkService;
    }

    public List<String> globalsearch() {
        return accountService.getAllUserIds("1");
    }

    public List<String> followersearch(String userId) {
        return networkService.getAllUserIds(userId);
    }
}
