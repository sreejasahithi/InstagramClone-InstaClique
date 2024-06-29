package com.insta.Instagram_clone.restcontroller;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import com.insta.Instagram_clone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/username/{username}")
    public List<AccountDto> searchUsers(@PathVariable String username) {
        String USER_NAME = Singleton.getInstance().getUsername();
        return accountService.searchByUsername(USER_NAME);
    }

    @GetMapping("/userid/{userId}")
    public AccountDto searchUserByUserId(@PathVariable String userId) {
        String USER_ID = Singleton.getInstance().getuserID();
        return accountService.searchByUserId(USER_ID);
    }

    @GetMapping("/userIds")
    public List<String> getAllUserIds() {
        return accountService.getAllUserIds("1");
    }
}
