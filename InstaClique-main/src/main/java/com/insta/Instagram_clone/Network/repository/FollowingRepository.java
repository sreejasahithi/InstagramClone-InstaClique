package com.insta.Instagram_clone.Network.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.insta.Instagram_clone.Network.model.*;
import java.util.List;

public interface FollowingRepository extends MongoRepository<Following, String> {
    @Query("{'userID' : ?0}")
    List<Following> findByUserID(String userID); // Update method name to match
    // field name

    @Query("{'userID': ?0}")
    List<Following> updateFollowingByUserId(String userId, List<String> following);

}
