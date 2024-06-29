package com.insta.Instagram_clone.Network.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.insta.Instagram_clone.Chat.repository.*;
import com.insta.Instagram_clone.Network.model.*;
import com.insta.Instagram_clone.Network.service.HandlerChainClient;
import com.insta.Instagram_clone.Network.repository.*;
import com.insta.Instagram_clone.Network.service.*;
import com.insta.Instagram_clone.New_chat_try.model.Chat_data;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.io.IOException;
import java.util.*;
import org.bson.types.Binary;
import java.util.Base64;
import java.time.LocalDateTime;
import com.insta.Instagram_clone.Network.model.*;
// import com.insta.Instagram_clone.Network.model.*;
import com.insta.Instagram_clone.service.*;

@RestController
@RequestMapping("/network")

public class networkcontroller {

    private final NetworkService service;
    private final FollowingService following_service;
    private final RequestService request_service;
    private final BlockedService block_service;
    String USER_NAME = Singleton.getInstance().getUsername();

    @Autowired
    public networkcontroller(NetworkService service, FollowingService following_service, RequestService r,
            BlockedService block_service) {
        this.following_service = following_service;
        this.service = service;
        this.block_service = block_service;
        this.request_service = r;
    }

    @GetMapping("/followers/{user_id}")
    public ResponseEntity<List<String>> handleMessage(@PathVariable("user_id") String user) {
        String USER_NAME = Singleton.getInstance().getUsername();
        System.out.println("ahfklafrfeir");
        List<Follower> ans = service.getAllDocumentsByUserID(USER_NAME);

        return ResponseEntity.ok().body(ans.get(0).followers);

    }

    @GetMapping("/follow/{user_id}/{my_userid}")
    public ResponseEntity<String> FollowUser(@PathVariable("user_id") String user,
            @PathVariable("my_userid") String my_user) {
        String USER_NAME = Singleton.getInstance().getUsername();
        // List<Follower> ans = service.getAllDocumentsByUserID(user);
        System.out.printf(user + " In follow path" + USER_NAME);
        List<Requests> ans = request_service.getAllDocumentsByUserID(user);
        // List<Following> ans3 = following_service.getAllDocumentsByUserID(user);
        // List<Follower> ans = service.getAllDocumentsByUserID(my_user);
        // Increase followers
        Network_t n1_requester = new Network_t(null, null, ans.get(0).requesters, null);
        n1_requester.Follow(USER_NAME);
        // Follower a = new Follower(my_user, n1_acceptor.display_followers());
        // service.updateFollowersForUser(a);
        // Decrease requests
        System.out.println(n1_requester.display_requests());
        Requests b = new Requests(user, n1_requester.requesters);
        request_service.updateRequestersForUser(b);

        return ResponseEntity.ok("Requested successfully.");

    }

    @GetMapping("/my_network/{user_id}")
    public ResponseEntity<List<String>> showRequesters(@PathVariable("user_id") String user) {
        String USER_NAME = Singleton.getInstance().getUsername();
        List<Requests> ans = request_service.getAllDocumentsByUserID(USER_NAME);

        return ResponseEntity.ok().body(ans.get(0).requesters);

    }

    @GetMapping("/blocked/{user_id}")
    public ResponseEntity<List<String>> showBlocked(@PathVariable("user_id") String user) {
        String USER_NAME = Singleton.getInstance().getUsername();
        List<Blocked> ans = block_service.getAllDocumentsByUserID(user);

        return ResponseEntity.ok().body(ans.get(0).blocked);

    }

    @GetMapping("/block/{user_id}/{my_userid}")
    public ResponseEntity<List<String>> block(@PathVariable("user_id") String user,
            @PathVariable("my_userid") String my_userid) {
        String USER_NAME = Singleton.getInstance().getUsername();
        List<Blocked> ans = block_service.getAllDocumentsByUserID(USER_NAME);
        // Network_t user_net = new Network_t(null, null, null, ans.get(0).blocked);
        // user_net.block(user);
        Blocked new_obj = new Blocked(user, ans.get(0).blocked);
        new_obj.block(user);
        System.out.println(new_obj.blocked);
        block_service.updateBlockersForUser(new_obj);

        List<Follower> ans1 = service.getAllDocumentsByUserID(USER_NAME);
        Network_t n1 = new Network_t(ans1.get(0).followers, null, null, null);
        n1.remove_follower(user);
        Follower a = new Follower(USER_NAME, n1.display_followers());
        service.updateFollowersForUser(a);

        List<Following> ans2 = following_service.getAllDocumentsByUserID(user);
        Network_t n2 = new Network_t(null, ans2.get(0).following, null, null);
        n2.Unfollow(USER_NAME);
        Following a1 = new Following(user, n2.display_following());
        System.out.println(n2.display_following());
        following_service.updateFollowingForUser(a1);

        return ResponseEntity.ok().body(ans.get(0).blocked);

    }

    @GetMapping("/unblock/{user_id}/{my_userid}")
    public ResponseEntity<List<String>> unBlock(@PathVariable("user_id") String user,
            @PathVariable("my_userid") String my_userid) {
        String USER_NAME = Singleton.getInstance().getUsername();

        List<Blocked> ans = block_service.getAllDocumentsByUserID(USER_NAME);
        // Network_t user_net = new Network_t(null, null, null, ans.get(0).blocked);
        System.out.println("UBLOCK  " + user);
        // user_net.block(user);
        Blocked new_obj = new Blocked(USER_NAME, ans.get(0).blocked);
        new_obj.unblock_user(user);
        System.out.println(new_obj.blocked);
        block_service.updateBlockersForUser(new_obj);
        return ResponseEntity.ok().body(ans.get(0).blocked);

    }

    // @GetMapping("/followers/remove/{user_id}/{my_user}")
    // public ResponseEntity removeFollowers(@PathVariable("user_id") String user,
    // @PathVariable("my_user") String my_user) {
    // List<Follower> ans = service.getAllDocumentsByUserID(my_user);
    // Network_t n1 = new Network_t(ans.get(0).followers, null, null, null);
    // n1.remove_follower(user);
    // Follower a = new Follower(my_user, n1.display_followers());
    // service.updateFollowersForUser(a);

    // List<Following> ans1 = following_service.getAllDocumentsByUserID(user);
    // Network_t n2 = new Network_t(null, ans1.get(0).following, null, null);
    // n2.Unfollow(my_user);
    // Following a1 = new Following(user, n2.display_following());
    // System.out.println(n2.display_following());
    // following_service.updateFollowingForUser(a1);

    // return ResponseEntity.ok().build();

    // }

    @GetMapping("/followers/remove/{user_id}/{my_user}")
    public ResponseEntity removeFollowers(@PathVariable("user_id") String user,
            @PathVariable("my_user") String myUser) {
        String USER_NAME = Singleton.getInstance().getUsername();
        HandlerChainClient client = new HandlerChainClient(service, following_service, request_service, block_service);
        client.executeRequest(user, USER_NAME);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/request_accept/{user_id}/{my_id}")
    public ResponseEntity RequestAccepting(@PathVariable("user_id") String user,
            @PathVariable("my_id") String my_user1) {
        String my_user = Singleton.getInstance().getUsername();
        List<Requests> ans2 = request_service.getAllDocumentsByUserID(my_user);
        // List<Following> ans3 = following_service.getAllDocumentsByUserID(user);
        List<Follower> ans = service.getAllDocumentsByUserID(my_user);
        // Increase followers
        Network_t n1_acceptor = new Network_t(ans.get(0).followers, null, ans2.get(0).requesters, null);
        n1_acceptor.accept_request(user);
        Follower a = new Follower(my_user, n1_acceptor.display_followers());
        service.updateFollowersForUser(a);
        // Decrease requests
        Requests b = new Requests(my_user, n1_acceptor.requesters);
        request_service.updateRequestersForUser(b);

        // Following of requester increase
        List<Following> ans1 = following_service.getAllDocumentsByUserID(user);
        Network_t n2 = new Network_t(null, ans1.get(0).following, null, null);
        n2.addFollowing(my_user);
        Following a1 = new Following(user, n2.display_following());
        System.out.println(n2.display_following());
        following_service.updateFollowingForUser(a1);

        return ResponseEntity.ok().build();

    }

}