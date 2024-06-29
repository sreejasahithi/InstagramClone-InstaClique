package com.insta.Instagram_clone.service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.bson.types.Binary;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    @Autowired
    public Post_interface post_interface;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Helper method to convert java.util.Date to Timestamp
    // private void convertDatesToTimestamp(Posts posts) {
    // if (posts.getTimeStamp() != null) {
    // java.util.Date utilDate = posts.getTimeStamp();
    // Timestamp timestamp = new Timestamp(utilDate.getTime());
    // posts.setTimeStamp(timestamp);
    // }
    // }

    // Find a Post by ID and convert its date to Timestamp if it exists
    public Posts findById(Integer id) {
        Posts posts = postRepository.findByPostId(id);
        // if (posts != null) {
        // convertDatesToTimestamp(posts); // Convert dates to Timestamp
        // }
        return posts;
    }

    // Find image byte array by Post ID
    public Binary findImageById(Integer id) {
        Posts post = post_interface.findByPostId(id);
        // if (optionalPosts.isPresent()) {
        // Posts posts = optionalPosts.get();
        // if (posts.getImage() != null) {
        // return posts.getImage().getData(); // Assuming getImage() returns image data
        // }
        // }
        return post.getImage(); // Return null if no Posts entity found for the given id or image is null
    }

    // Save or update a Post
    public void savePost(Posts post) {
        postRepository.save(post);
    }

    // Additional service methods can be added here for specific business logic

}
