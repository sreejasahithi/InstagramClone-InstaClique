package com.insta.Instagram_clone.service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import org.springframework.data.mongodb.core.MongoTemplate;

@Service
public class PostListService {

    private final PostListRepository postListRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    public PostListService(PostListRepository postListRepository) {
        this.postListRepository = postListRepository;
    }

    // Method to add a postId to a PostList
    public void addPostToPostList(Integer postid) {
        String USER_NAME = Singleton.getInstance().getUsername();
        PostList postList = postListRepository.findByUserId(USER_NAME);
        // PostList postList = postlistrepo.findByPostListContaining(posts.getUserID());
        postList.addPost(postid);

        Update update = new Update().set("postList", postList.getPostList());
        mongoTemplate.updateFirst(query(where("userId").is(postList.getuserId())), update, PostList.class);

        // if (postList == null) {
        // // If no PostList containing the postId exists, create a new PostList
        // postList = new PostList();
        // }

        // // Add postId to the PostList
        // postList.addPost(postId);

        // // Save the updated PostList back to the repository
        // postListRepository.save(postList);
    }

    // Method to find a PostList by postListId (document id)
    public PostList findPostListById(String postListId) {
        Optional<PostList> optionalPostList = postListRepository.findById(postListId);

        // Check if the PostList is present in the Optional
        if (optionalPostList.isPresent()) {
            return optionalPostList.get(); // Return the PostList if present
        } else {
            return null; // Return null if PostList is not found
        }
    }

    // Method to find a PostList by postId contained in the postList
    public PostList findPostListByPostId(String postId) {
        String USER_NAME = Singleton.getInstance().getUsername();
        return postListRepository.findByUserId(USER_NAME);
    }

    // Additional service methods can be added here for specific business logic

    // Example: Method to retrieve all PostLists
    public List<PostList> getAllPostLists() {
        return postListRepository.findAll();
    }
}
