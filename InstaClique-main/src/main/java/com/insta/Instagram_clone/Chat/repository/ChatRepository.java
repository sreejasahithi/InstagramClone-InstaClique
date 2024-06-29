package com.insta.Instagram_clone.Chat.repository;

import com.insta.Instagram_clone.Chat.model.Chat_each;

import org.springframework.data.mongodb.repository.MongoRepository;

// import org.bson.types.ObjectId;

public interface ChatRepository extends MongoRepository<Chat_each, String> {
    // You can define custom query methods here if needed
}
