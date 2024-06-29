package com.insta.Instagram_clone.Network.service;

import com.insta.Instagram_clone.Network.model.*;
import java.util.*;
import com.insta.Instagram_clone.Network.model.Network_t.*;
import com.insta.Instagram_clone.Network.repository.*;
import com.insta.Instagram_clone.Network.service.*;

import org.springframework.beans.factory.annotation.Autowired;
// import com.insta.Instagram_clone.Network.model.*;

public class FollowersHandler implements Handler {
    private Handler nextHandler;
    private final NetworkService service;
    private final FollowingService following_service;
    private final RequestService request_service;
    private final BlockedService block_service;

    @Autowired
    public FollowersHandler(NetworkService service, FollowingService following_service, RequestService r,
            BlockedService block_service) {
        this.following_service = following_service;
        this.service = service;
        this.block_service = block_service;
        this.request_service = r;
    }

    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(String user, String myUser) {
        List<Follower> ans = service.getAllDocumentsByUserID(myUser);
        Network_t network = new Network_t(ans.get(0).followers, null, null, null);
        network.remove_follower(user);
        Follower a = new Follower(myUser, network.display_followers());
        service.updateFollowersForUser(a);

        // Pass the request to the next handler if exists
        if (nextHandler != null) {
            nextHandler.handleRequest(user, myUser);
        }
    }
}
