package com.insta.Instagram_clone.Network.service;

import com.insta.Instagram_clone.Network.model.*;
import com.insta.Instagram_clone.Network.service.*;
// import com.insta.Instagram_clone.Network.service.*;

import org.springframework.beans.factory.annotation.Autowired;
// import com.insta.Instagram_clone.Network.model.*;

public class HandlerChainClient {
    private Handler followersHandler;
    private Handler followingHandler;
    private final NetworkService service;
    private final FollowingService following_service;
    private final RequestService request_service;
    private final BlockedService block_service;

    public HandlerChainClient(NetworkService service, FollowingService following_service, RequestService r,
            BlockedService block_service) {
        this.following_service = following_service;
        this.service = service;
        this.block_service = block_service;
        this.request_service = r;
        followersHandler = new FollowersHandler(service, following_service, request_service, block_service);
        followingHandler = new FollowersHandler(service, following_service, request_service, block_service);
        followersHandler.setNextHandler(followingHandler);

    }

    public void executeRequest(String user, String myUser) {
        followersHandler.handleRequest(user, myUser);
    }
}
