
package com.insta.Instagram_clone.service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.Network.repository.*;
import com.insta.Instagram_clone.Network.service.*;
import com.insta.Instagram_clone.Network.model.*;
import com.insta.Instagram_clone.repository.*;
import com.insta.Instagram_clone.New_chat_try.repository.*;
import com.insta.Instagram_clone.New_chat_try.model.*;

@Service
public class AuthenticationManagerServiceImpl implements AuthenticationService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private FollowerRepository followrepository; // Add @Autowired annotation here
    @Autowired
    private FollowingRepository followingrepository;
    @Autowired
    private BlockedRepository blockedRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private Chatrepository chatrepo;
    @Autowired
    private Account1Repository account1repo;
    @Autowired
    private PostListRepository postlistrepo;
    @Autowired
    private BusinessAccountRepository businessrepo;

    // private FollowerRepository followrepository;
    // private FollowingRepository followingrepository;
    // private BlockedRepository blockedRepository;
    // private RequestRepository requestRepository;
    // private Chatrepository chatrepo;

    @Override
    public Account signUp(AccountDto accountDto) {

        Account account = new Account();
        account.setId(accountDto.getId());
        account.setUsername(accountDto.getUsername());
        account.setEmail(accountDto.getEmail());
        account.setPassword(accountDto.getPassword());

        // Set default values for other fields
        account.setEnabled(true); // Default value for enabled status
        account.setDisabledReason("Its enabled"); // Default reason for disabling
        account.setReportCount(0); // Default value for report count

        List<String> temp = new ArrayList<>();
        Follower follower = new Follower(account.getId(), temp);
        followrepository.save(follower);
        Following following = new Following(account.getId(), temp);
        followingrepository.save(following);
        Blocked blocked = new Blocked(account.getId(), temp);
        blockedRepository.save(blocked);
        Requests requests = new Requests(account.getId(), temp);
        requestRepository.save(requests);
        ChatList chatL = new ChatList(account.getId(), temp);
        chatrepo.save(chatL);
        BusinessAccount business_account = new BusinessAccount();

        chatrepo.save(chatL);

        PostList postList = new PostList();
        postList.setUserId(account.getId());
        postList.setPostList(new ArrayList<>());
        postlistrepo.save(postList);

        Account1 account1 = new Account1();
        account1.setFollowersPostList(new ArrayList<>());
        account1.setUserId(account.getId());
        account1.setType("PERSONAL");
        account1.setUsername(account.getUsername());
        account1.setPassword(account.getPassword());

        account1repo.save(account1);

        // If ID is provided, check if it already exists
        if (accountRepository.existsById(account.getId())) {
            throw new RuntimeException("User ID already exists: " + account.getId());
            // Alternatively, you can return a custom error message or handle it as per your
            // application's requirements
        }

        int maxAccountsPerUser = 5;
        long existingAccountsCount = accountRepository.countByUsername(account.getUsername());
        if (existingAccountsCount >= maxAccountsPerUser) {
            throw new RuntimeException("Maximum number of accounts reached for username: " + account.getUsername());
        }
        // Additional checks can be added here before saving the account
        return accountRepository.save(account);
    }

    @Override
    public Account login(String userId, String username, String password) {
        Singleton singleton = Singleton.getInstance();

        // Set the username and password
        singleton.setUsername(username);
        singleton.setUserID(userId);

        // singleton.setPassword("example_password");

        // Retrieve the username and password
        String username1 = singleton.getUsername();
        // String password1 = singleton.getPassword();

        // Print the retrieved values
        System.out.println("Username: " + username1);
        // System.out.println("Password: " + password1);

        return accountRepository.findByIdAndUsernameAndPassword(userId, username, password);

    }
}
