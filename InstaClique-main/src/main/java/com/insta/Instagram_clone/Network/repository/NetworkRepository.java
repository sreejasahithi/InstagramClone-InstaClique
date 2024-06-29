// package com.insta.Instagram_clone.Network.repository;

// import org.springframework.data.mongodb.repository.MongoRepository;
// import com.insta.Instagram_clone.Network.model.*;
// import java.util.List;

// public interface NetworkRepository extends MongoRepository<Follower, String> {
//     List<Follower> findByUserId(String user_id);
// }

package com.insta.Instagram_clone.Network.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.insta.Instagram_clone.Network.model.*;
import java.util.List;

public interface NetworkRepository extends MongoRepository<Follower, String> {
    @Query("{'userID' : ?0}")
    List<Follower> findByUserID(String userID); // Update method name to match
    // field name

    @Query("{'userID': ?0}")
    List<Follower> updateFollowersByUserId(String userId, List<String> followers);

}
