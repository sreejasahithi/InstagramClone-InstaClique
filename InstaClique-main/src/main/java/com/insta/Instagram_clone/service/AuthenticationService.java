package com.insta.Instagram_clone.service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;

public interface AuthenticationService {
    Account signUp(AccountDto accountDto);

    Account login(String userId, String username, String password);
}
