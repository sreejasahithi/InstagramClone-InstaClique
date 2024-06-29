package com.insta.Instagram_clone.Network.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.insta.Instagram_clone.Chat.repository.*;
import com.insta.Instagram_clone.Network.model.*;
import com.insta.Instagram_clone.Network.repository.*;
import com.insta.Instagram_clone.Network.service.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.insta.Instagram_clone.Network.model.*;
import com.insta.Instagram_clone.service.*;

@RestController
@RequestMapping("/following")
public class followingcontroller {

    private final FollowingService service;
    private final NetworkService net_Service;
    String USER_NAME = Singleton.getInstance().getUsername();

    @Autowired
    public followingcontroller(FollowingService service, NetworkService net_service) {
        this.net_Service = net_service;
        this.service = service;
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<String>> handleMessage1(@PathVariable("user_id") String user) {
        // Extract content and type from the request
        // network n1 =new network([],)
        System.out.println(user + 2);
        // List<Following> ans = service.getAll();
        // FollowingService.updateFollowersForUser(ans.get(0));
        List<Following> ans = service.getAllDocumentsByUserID(user);
        for (Following y : ans) {
            System.out.println(y.userID + " " + y.following);
        }
        // System.out.println("BELOW " + ans + " ");

        return ResponseEntity.ok().body(ans.get(0).following);

    }

    @GetMapping("/remove/{user_id}/{my_user}")
    public ResponseEntity unfollow(@PathVariable("user_id") String user,
            @PathVariable("my_user") String my_user) {
        List<Following> ans = service.getAllDocumentsByUserID(my_user);
        Network_t n1 = new Network_t(null, ans.get(0).following, null, null);
        n1.Unfollow(user);
        Following a = new Following(my_user, n1.display_following());
        service.updateFollowingForUser(a);

        List<Follower> ans1 = net_Service.getAllDocumentsByUserID(user);
        Network_t n2 = new Network_t(ans1.get(0).followers, null, null, null);
        n2.remove_follower(my_user);
        Follower a1 = new Follower(user, n2.display_followers());
        System.out.println(n2.display_followers());
        net_Service.updateFollowersForUser(a1);

        return ResponseEntity.ok().build();

    }

}
