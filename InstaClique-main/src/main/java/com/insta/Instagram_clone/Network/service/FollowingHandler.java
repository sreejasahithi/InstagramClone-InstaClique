package com.insta.Instagram_clone.Network.service;

import com.insta.Instagram_clone.Network.model.*;
import java.util.*;
import com.insta.Instagram_clone.Network.model.Network_t.*;
import com.insta.Instagram_clone.Network.repository.*;
import com.insta.Instagram_clone.Network.service.*;

import org.springframework.beans.factory.annotation.Autowired;
// import com.insta.Instagram_clone.Network.model.*;

public class FollowingHandler implements Handler {
    private Handler nextHandler;
    private final NetworkService service;
    private final FollowingService following_service;
    private final RequestService request_service;
    private final BlockedService block_service;

    @Autowired
    public FollowingHandler(NetworkService service, FollowingService following_service, RequestService r,
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
        List<Following> ans = following_service.getAllDocumentsByUserID(user);
        Network_t network = new Network_t(null, ans.get(0).following, null, null);
        network.Unfollow(myUser);
        Following a = new Following(user, network.display_following());
        System.out.println(network.display_following());
        following_service.updateFollowingForUser(a);

        // No need to pass the request to the next handler in this case
    }
}
