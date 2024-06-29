package com.insta.Instagram_clone.New_chat_try.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// import com.example.instagram_clone.entity.Comments;
import com.insta.Instagram_clone.New_chat_try.model.*;

@Repository
public interface Chatrepository extends MongoRepository<ChatList, String> {

    // findByCommentId(Integer commentId);

}
