package com.insta.Instagram_clone.repository;

import org.bson.types.Binary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.insta.Instagram_clone.model.*;

@Repository
public interface Post_interface extends MongoRepository<Posts, Integer> {

    // @Query(value="{'postId':?0}", fields="{'image:1'}")
    // Binary getImage(Integer postId);

    Posts findByPostId(Integer postId);

    Posts[] findByUserID(String userID);

    @Query("delete from Posts p where p.postId = :postId")
    Integer deleteByPostId(@Param("postId") Integer postId);
}
