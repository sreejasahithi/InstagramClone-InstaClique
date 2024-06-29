package com.insta.Instagram_clone.restcontroller;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import com.insta.Instagram_clone.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

public class FeedController {
    @RestController
    @RequestMapping("/api/feed")
    public class PostController {

        @Autowired
        private PostService postService;
        @Autowired
        private BusinessAccountService busAccService;
        @Autowired
        private PostListService postListService;

        @Autowired
        private Account1Service AccountService;

        // @GetMapping("/post")
        // public byte[] getPost(@RequestParam String postId) {
        // // Retrieve the image data by postId
        // byte[] imageData = postService.findImageById(postId);
        // return imageData;
        // }

    }

}
