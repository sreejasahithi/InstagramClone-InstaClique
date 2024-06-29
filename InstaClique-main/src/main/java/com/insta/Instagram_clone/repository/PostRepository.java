
package com.insta.Instagram_clone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.insta.Instagram_clone.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Posts, Integer> {

    // Method to find a Post by userId
    Posts findByPostId(Integer PostId);

    Posts findFirstByOrderByPostIdDesc();

}
