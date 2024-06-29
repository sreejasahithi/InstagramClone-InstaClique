package com.insta.Instagram_clone.restcontroller;

import com.insta.Instagram_clone.Network.service.NetworkService;
import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import com.insta.Instagram_clone.service.*;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.bson.types.Binary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.insta.Instagram_clone.service.*;

@RestController
@RequestMapping("/api/post")
public class Post1Controller {

    @Autowired
    private PostService postService;
    @Autowired
    private BusinessAccountService busAccService;
    @Autowired
    private PostListService postListService;
    @Autowired
    private PostListRepository postListrepo;

    @Autowired
    private Account1Service AccountService;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private NetworkService networkService;
    @Autowired
    private Post_interface post_interface;

    @GetMapping("/list")
    public List<Integer> getFollowerPostList() {
        String USER_NAME = Singleton.getInstance().getUsername();
        Account1 acc = AccountService.findByUserId(USER_NAME);
        List<Integer> posts = acc.followersPostList;
        System.out.println("posts " + posts);
        return posts;
    }

    @GetMapping("/post")
    public Binary getPostImage(@RequestParam String postId) {
        Integer post_id = Integer.parseInt(postId);
        // System.out.println("feching for " + post_id);

        // // Retrieve the image data by postId
        // byte[] imageData = postService.findImageById(post_id);

        Posts post = post_interface.findByPostId(post_id);
        // if (optionalPosts.isPresent()) {
        // Posts posts = optionalPosts.get();
        // if (posts.getImage() != null) {
        // return posts.getImage().getData(); // Assuming getImage() returns image data
        // }
        // }
        System.out.println(post.getImage());
        return post.getImage();
        // return ;
    }

    @GetMapping("/postlist_get")
    public List<Integer> getPostList() {
        String USER_NAME = Singleton.getInstance().getUsername();
        // Retrieve the image data by postId
        List<Integer> temp = postListrepo.findByUserId(USER_NAME).getPostList();
        return temp;

    }
    // @GetMapping("/followerList")
    // public List<String> getFollowerList() {
    // UserId userId = UserId.getInstance();
    // Account1 acc = AccountService.findByUserId(userId.getUserId());
    // List<String> followers = acc.getFollowers();
    // return followers;
    // }

    @PostMapping("/create")
    public String createPost() throws IOException {

        // Posts newPost = new Posts();
        // newPost.setPostId("1");

        // Posts latestPost = postRepository.findFirstByOrderByPostIdDesc();

        // // Increment the postId by 1 and set it to the new post
        // Integer nextPostId = (latestPost != null) ? (latestPost.getPostId()) + 1 : 1;
        // // String newPostId = String.valueOf(nextPostId);
        // System.out.println("new post id " + nextPostId);

        // // Create a new post object and set the postId
        // Posts newPost = new Posts();
        // newPost.setPostId(nextPostId);

        // if (image != null) {
        // newPost.setImage(new Binary(image.getBytes()));
        // newPost.setContentType("image");
        // }

        // if (video != null) {
        // newPost.setVideo(new Binary(video.getBytes()));
        // newPost.setContentType("video");
        // }
        // LocalDateTime currentDateTime = LocalDateTime.now();
        // Timestamp timestamp = Timestamp.valueOf(currentDateTime);
        // newPost.setTimeStamp(timestamp);
        //
        //// Assign the timestamp to newPost
        // newPost.setTimeStamp(timestamp);
        String USER_NAME = Singleton.getInstance().getUsername();

        // BusinessAccount account = busAccService.findByUserId(USER_NAME);
        // if (account != null) {
        // // PostList posts = account.getPosts();
        // System.out.println("Finding for Id " + account.getUserId());
        // PostList posts = postListService.findPostListById(account.id);
        // System.out.println("found posts " + posts);
        // if (posts == null) {
        // posts = new PostList(); // Initialize the posts list if it's null
        // posts.setId(account.UserId);
        // account.setPosts(posts); // Set the initialized list back to account
        // }
        // posts.addPost(newPost.postId); // Now add the post to the list
        // } else {
        // throw new IllegalArgumentException("Account cannot be null");
        // }
        System.out.println("hereeeeeeeeeeee   \n\n\n hereeeeeeee\n");
        // String USER_NAME = Singleton.getInstance().getUsername();
        BusinessAccount acc = busAccService.findByUserId(USER_NAME);
        // acc.followersPostList.add(nextPostId);
        // AccountService.saveAccount(acc);

        // postListService.addPostToPostList(newPost.postId);
        float temp = acc.bill;
        acc.bill = temp + 500;
        busAccService.saveBusinessAccount(acc);
        // postService.savePost(newPost);
        return "Post created successfully";
    }

    @PostMapping("/add")
    public String createPostPrivate(@RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "video", required = false) MultipartFile video) throws IOException {

        // Posts newPost = new Posts();
        // newPost.setPostId("2");

        Posts latestPost = postRepository.findFirstByOrderByPostIdDesc();

        // Increment the postId by 1 and set it to the new post
        int nextPostId = (latestPost != null) ? (latestPost.getPostId()) + 1 : 1;
        String newPostId = String.valueOf(nextPostId);
        System.out.println("new post id " + newPostId);

        // Create a new post object and set the postId
        Posts newPost = new Posts();
        newPost.setPostId(nextPostId);

        if (image != null) {
            newPost.setImage(new Binary(image.getBytes()));
            newPost.setContentType("image");
        }

        if (video != null) {
            newPost.setVideo(new Binary(video.getBytes()));
            newPost.setContentType("video");
        }
        // LocalDateTime currentDateTime = LocalDateTime.now();
        // Timestamp timestamp = Timestamp.valueOf(currentDateTime);
        // newPost.setTimeStamp(timestamp);
        //
        //// Assign the timestamp to newPost
        // newPost.setTimeStamp(timestamp);g `
        String USER_NAME = Singleton.getInstance().getUsername();
        newPost.userID = USER_NAME;

        Account1 account = AccountService.findByUserId(USER_NAME);
        if (account != null) {
            // PostList posts = account.getPosts();
            System.out.println("Finding for Id " + account.getUserId());
            PostList posts = postListService.findPostListById(account.id);
            System.out.println("found posts " + posts);
            if (posts == null) {
                posts = new PostList(); // Initialize the posts list if it's null
                posts.setId(account.UserId);
                // account.setPosts(posts); // Set the initialized list back to account
            }
            posts.addPost(newPost.postId); // Now add the post to the list
        } else {
            throw new IllegalArgumentException("Account cannot be null");
        }

        postListService.addPostToPostList(newPost.postId);

        postListService.addPostToPostList(newPost.postId);

        List<String> followers = networkService.getAllUserIds(USER_NAME);
        for (String id : followers) {
            Account1 acc = AccountService.findByUserId(id);

            // Ensure followersPostList is initialized
            if (acc.getFollowersPostList() == null) {
                // Initialize followersPostList with an empty ArrayList
                // acc.setFollowersPostList(new ArrayList<>());
            }

            // Add newPost.postId to followersPostList
            if (acc.getFollowersPostList() != null) { // Double-check for safety
                acc.getFollowersPostList().add(newPost.postId);
            } else {
                System.out.println("followers is null");
            }

            // Save the updated account
            AccountService.saveAccount(acc);
        }

        System.out.println("Added to followers' post lists");

        AccountService.saveAccount(account);
        postService.savePost(newPost);
        return "Post created successfully";
    }

}
