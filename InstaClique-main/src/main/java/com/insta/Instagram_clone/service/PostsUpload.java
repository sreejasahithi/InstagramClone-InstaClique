package com.insta.Instagram_clone.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import com.insta.Instagram_clone.model.*;

import com.insta.Instagram_clone.repository.*;
import com.insta.Instagram_clone.Network.service.*;
//import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Service
public class PostsUpload {

    @Autowired
    public Post_interface post_interface;

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private PostList postlist;
    @Autowired
    private PostListRepository postlistrepo;
    @Autowired
    private NetworkService networkservice;
    @Autowired
    private Account1Service AccountService;
    @Autowired
    private Account1Repository AccountRepository;

    public Posts createPost(Posts posts) {

        List<Integer> ls1 = new ArrayList<Integer>();
        ls1.add(-1);
        List<Integer> ls2 = new ArrayList<Integer>();
        ls2.add(-1);
        posts.setComments(ls1);
        posts.setLikes(ls2);

        Query query = new Query();
        Posts document = mongoTemplate.findOne(query.with(Sort.by(Sort.Direction.DESC, "postId")).limit(1),
                Posts.class);
        PostList postlist = postlistrepo.findByUserId(posts.getUserID());
        postlist.addPost(document.getPostId() + 1);
        System.out.println("UP");
        System.out.println(postlist);
        Update update = new Update().set("postList", postlist.getPostList());
        mongoTemplate.updateFirst(query(where("userId").is(postlist.getuserId())), update, PostList.class);
        System.out.println("EHRER");
        posts.setPostId(document.getPostId() + 1);

        List<String> followers = networkservice.getAllUserIds(posts.getUserID());

        for (String id : followers) {
            Account1 acc = AccountService.findByUserId(id);

            // Ensure followersPostList is initialized
            // if (acc.getFollowersPostList() == null) {
            // // Initialize followersPostList with an empty ArrayList
            // System.out.println("HERE");
            // // acc.setFollowersPostList(new ArrayList<>());
            // }

            // // Add newPost.postId to followersPostList
            // if (acc.getFollowersPostList() != null) { // Double-check for safety
            List<Integer> t = acc.getFollowersPostList();
            t.add(posts.getPostId());

            System.out.println("HERE");
            System.out.println(t);
            // } else {
            // System.out.println("followers is null");
            // }

            // Save the updated account

            // AccountService.saveAccount(acc);

            Update update1 = new Update().set("followersPostList", t);
            mongoTemplate.updateFirst(query(where("UserId").is(id)), update1, Account1.class);

        }

        return post_interface.save(posts);

    }

}
