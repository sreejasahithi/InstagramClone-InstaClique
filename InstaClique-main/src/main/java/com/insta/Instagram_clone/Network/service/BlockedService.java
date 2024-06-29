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
public class BlockedService {

    // private final FollowingRepository repository;
    private final BlockedRepository block_repo;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public BlockedService(MongoTemplate mongoTemplate, BlockedRepository block_repo) {

        this.block_repo = block_repo;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Blocked> getAllDocumentsByUserID(String user_id) {
        System.out.println(user_id + " REPO FOLLOWING");
        return block_repo.findByUserID(user_id);
    }

    public List<Blocked> getAll() {
        return block_repo.findAll();
    }

    public void updateBlockersForUser(Blocked user1) {
        // List<Following> following = repository.findByUserID(user1.userID);
        mongoTemplate.updateFirst(
                // Query to find the document for the specific user
                // Here, assuming 'userID' is the unique identifier for users
                Query.query(Criteria.where("userID").is(user1.userID)),
                // Update operation to set the new 'following' list
                Update.update("blocked", user1.blocked),
                // Class of the document to update
                Blocked.class

        );

    }
}
