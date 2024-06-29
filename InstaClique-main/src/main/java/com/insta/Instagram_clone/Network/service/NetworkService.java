package com.insta.Instagram_clone.Network.service;

import java.util.List;
import com.insta.Instagram_clone.Network.model.*;
import com.insta.Instagram_clone.Network.repository.*;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.insta.Instagram_clone.service.*;;

@Service
public class NetworkService implements search_interface {

    private final NetworkRepository repository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public NetworkService(NetworkRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    public List<Follower> getAllDocumentsByUserID(String user_id) {
        System.out.println(user_id + " REPO");
        return repository.findByUserID(user_id);
    }

    public List<Follower> getAll() {
        return repository.findAll();
    }

    public void updateFollowersForUser(Follower user) {
        List<Follower> followers = repository.findByUserID(user.userID);
        if (followers.isEmpty()) {
            throw new RuntimeException(String.format("Cannot find follower by ID %s", user.userID));
        } else {
            Follower follower = followers.get(0); // Assuming you want to get the first follower if there are multiple
            follower.setFollower(user.followers); // Assuming there's a setter method for followers in the Follower
                                                  // class
            repository.save(follower);
        }
    }

    public List<String> getAllUserIds(String user_id) {
        System.out.println(user_id + " REPO");
        List<Follower> arr = repository.findByUserID(user_id);
        return arr.get(0).followers;
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

}
