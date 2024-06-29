package com.insta.Instagram_clone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.insta.Instagram_clone.model.*;

@Repository
public interface Likes_interface extends MongoRepository<Likes, Integer> {

	Likes findByLikeId(Integer likeId);
}
