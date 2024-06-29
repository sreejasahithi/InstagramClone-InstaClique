package com.insta.Instagram_clone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// import com.example.instagram_clone.entity.Comments;
import com.insta.Instagram_clone.model.*;

@Repository
public interface Comments_interface extends MongoRepository<Comments, Integer> {

	Comments findByCommentId(Integer commentId);

}
