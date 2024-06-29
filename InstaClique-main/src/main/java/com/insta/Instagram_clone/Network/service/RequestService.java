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

@Service
public class RequestService {

    private final NetworkRepository repository;
    private final MongoTemplate mongotemplate;
    private final RequestRepository req_repo;

    @Autowired
    public RequestService(NetworkRepository repository, MongoTemplate mongoTemplate, RequestRepository repo) {
        this.repository = repository;
        this.mongotemplate = mongoTemplate;
        this.req_repo = repo;
    }

    public List<Requests> getAllDocumentsByUserID(String user_id) {
        System.out.println(user_id + " REPO");
        return req_repo.findByUserID(user_id);
    }

    public void addRequest(Requests r) {
        mongotemplate.insert(r, "requests");

    }

    // public List<Requests> getAll() {
    // return req_repo.findAll();
    // }

    public void updateRequestersForUser(Requests user1) {
        // List<Following> following = repository.findByUserID(user1.userID);
        mongotemplate.updateFirst(
                // Query to find the document for the specific user
                // Here, assuming 'userID' is the unique identifier for users
                Query.query(Criteria.where("userID").is(user1.userID)),
                // Update operation to set the new 'following' list
                Update.update("requesters", user1.requesters),
                // Class of the document to update
                Requests.class

        );

    }

}
