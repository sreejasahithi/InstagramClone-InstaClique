
package com.insta.Instagram_clone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import com.insta.Instagram_clone.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostListRepository extends MongoRepository<PostList, String> {

    // Custom query to find a PostList by postListId (document id)
    // Spring Data MongoDB automatically generates this method based on method name
    // No need to implement it explicitly
    // PostList findById(String postListId);

    // Custom query to find a PostList by postId contained in the postList

    // PostList findByPostListContaining(String userId);

    @Query("{ 'UserId' : ?0 }")
    PostList findByUserId(String userId);
}
