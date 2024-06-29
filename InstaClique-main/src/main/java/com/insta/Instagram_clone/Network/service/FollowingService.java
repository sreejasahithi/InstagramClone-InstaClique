package com.insta.Instagram_clone.Network.service;

import java.util.List;
import com.insta.Instagram_clone.Network.model.*;
import com.insta.Instagram_clone.Network.repository.*;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.insta.Instagram_clone.Network.model.Following;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@Service
public class FollowingService {

    private final FollowingRepository repository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public FollowingService(FollowingRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Following> getAllDocumentsByUserID(String user_id) {
        System.out.println(user_id + " REPO FOLLOWING");
        return repository.findByUserID(user_id);
    }

    public List<Following> getAll() {
        return repository.findAll();
    }

    public void updateFollowingForUser(Following user1) {
        // List<Following> following = repository.findByUserID(user1.userID);
        mongoTemplate.updateFirst(
                // Query to find the document for the specific user
                // Here, assuming 'userID' is the unique identifier for users
                Query.query(Criteria.where("userID").is(user1.userID)),
                // Update operation to set the new 'following' list
                Update.update("following", user1.following),
                // Class of the document to update
                Following.class

        );

    }

    public void updateFollowersForUser(Follower user1) {
        // List<Following> following = repository.findByUserID(user1.userID);
        mongoTemplate.updateFirst(
                // Query to find the document for the specific user
                // Here, assuming 'userID' is the unique identifier for users
                Query.query(Criteria.where("userID").is(user1.userID)),
                // Update operation to set the new 'following' list
                Update.update("followers", user1.followers),
                // Class of the document to update
                Follower.class

        );

    }
}
