package com.insta.Instagram_clone.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.Instagram_clone.model.Posts;

import com.insta.Instagram_clone.repository.*;

@Service
// public class PostsDisplay implements
// Display_Interface<Integer,String,String>{
public class PostsDisplay {
    @Autowired
    public Post_interface post_interface;

    /*
     * public List<String> displaypost(String id) {
     * 
     * Posts[] postsArr=post_interface.findByUserID(id);
     * 
     * List<String> imageDataList = new ArrayList<>();
     * 
     * for (Posts post : postsArr) {
     * 
     * String imageData =
     * Base64.getEncoder().encodeToString(post.getImage().getData());
     * imageDataList.add(imageData);
     * }
     * 
     * 
     * 
     * return imageDataList;
     * 
     * }
     */
    /*
     * correct
     * public Map<Integer, String> displaypost(String id) {
     * Posts[] postsArr = post_interface.findByUserID(id);
     * Map<Integer, String> imageDataMap = new HashMap<>();
     * 
     * for (Posts post : postsArr) {
     * // Assuming the postId is an integer field in the Posts entity
     * Integer postId = post.getPostId();
     * // Assuming the image is stored as a Base64-encoded string in the database
     * 
     * String imageData =
     * Base64.getEncoder().encodeToString(post.getImage().getData()); // Assuming
     * getImage() returns the Base64 string
     * imageDataMap.put(postId, imageData);
     * 
     * 
     * 
     * 
     * }
     * 
     * return imageDataMap;
     * }
     * 
     */

    public Map<Integer, Map<String, String>> displaypost(String id) {
        Posts[] postsArr = post_interface.findByUserID(id);
        Map<Integer, Map<String, String>> mediaDataMap = new HashMap<>();
        System.out.println(id + "+OIEQGO");
        for (Posts post : postsArr) {
            // Assuming the postId is an integer field in the Posts entity
            Integer postId = post.getPostId();
            Map<String, String> mediaInfo = new HashMap<>();

            if ("image".equals(post.getContentType())) {
                String imageData = Base64.getEncoder().encodeToString(post.getImage().getData());
                mediaInfo.put("type", "image");
                mediaInfo.put("data", imageData);
            } else {
                String videoData = Base64.getEncoder().encodeToString(post.getVideo().getData());
                mediaInfo.put("type", "video");
                mediaInfo.put("data", videoData);
            }
            mediaDataMap.put(postId, mediaInfo);

        }

        return mediaDataMap;
    }

    /*
     * @Override
     * public Map<Integer, String> display(String k) {
     * // TODO Auto-generated method stub
     * System.out.println("post_interface:::"+post_interface);
     * System.out.println("k:::"+k);
     * Posts[] postsArr = post_interface.findByUserID(k);
     * Map<Integer, String> imageDataMap = new HashMap<>();
     * 
     * for (Posts post : postsArr) {
     * // Assuming the postId is an integer field in the Posts entity
     * Integer postId = post.getPostId();
     * // Assuming the image is stored as a Base64-encoded string in the database
     * String imageData =
     * Base64.getEncoder().encodeToString(post.getImage().getData()); // Assuming
     * getImage() returns the Base64 string
     * imageDataMap.put(postId, imageData);
     * }
     * 
     * return imageDataMap;
     * }
     */

}
