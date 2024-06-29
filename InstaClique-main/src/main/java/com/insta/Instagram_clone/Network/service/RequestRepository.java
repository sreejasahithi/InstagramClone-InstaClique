
package com.insta.Instagram_clone.Network.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.insta.Instagram_clone.Network.model.*;
import java.util.List;

public interface RequestRepository extends MongoRepository<Requests, String> {
    @Query("{'userID' : ?0}")
    List<Requests> findByUserID(String userID); // Update method name to match
    // field name

    @Query("{'userID': ?0}")
    List<Requests> UpdateRequestsByUserId(String userId, List<String> requesters);

}
