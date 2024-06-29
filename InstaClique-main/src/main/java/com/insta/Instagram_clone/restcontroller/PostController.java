package com.insta.Instagram_clone.restcontroller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import com.insta.Instagram_clone.service.*;

@RestController
public class PostController {

	@Autowired
	public Post_interface post_interface;

	@Autowired
	public PostsUpload postsUpload;

	@Autowired
	public PostsDisplay postDisplay;

	@Autowired
	public PostsDelete postDelete;

	/*
	 * @Autowired
	 * public DisplayFactory dispFactory;
	 */

	@GetMapping(path = "/getAllPosts")
	public List<Posts> getAllPosts() {
		System.out.println("list size:::" + post_interface.findAll().size());
		return post_interface.findAll();

	}

	@GetMapping(path = "/sayHello")
	public String sayHello() {
		System.out.println("hello");
		return "Hello ammu";

	}

	/*
	 * @PostMapping(path = "/insertPost")
	 * public String insertPost(@RequestBody Map<String, Object> post) {
	 * // Extract post_id and content_type from the request body
	 * Integer postId = (Integer) post.get("post_id");
	 * String contentType = (String) post.get("content_type");
	 * 
	 * // Create a new Posts object and save it to the database
	 * Posts newPost = new Posts();
	 * newPost.setPost_id(postId);
	 * newPost.setContent_type(contentType);
	 * post_interface.save(newPost);
	 * 
	 * return "Post inserted successfully";
	 * }
	 */
	/*
	 * @PostMapping(path = "/insertPost", consumes = {"multipart/form-data"})
	 * public String insertPost(@RequestParam("post_id") Integer postId,
	 * 
	 * @RequestParam("content_type") String contentType,
	 * 
	 * @RequestParam("image") MultipartFile image) {
	 * // Create a new Posts object and set the fields
	 * Posts newPost = new Posts();
	 * newPost.setPost_id(postId);
	 * newPost.setContent_type(contentType);
	 * 
	 * // Set the image data
	 * try {
	 * if (image != null && !image.isEmpty()) {
	 * newPost.setImage(image.getBytes());
	 * }
	 * } catch (IOException e) {
	 * e.printStackTrace();
	 * return "Error uploading image";
	 * }
	 * 
	 * // Save the new post to the database
	 * post_interface.save(newPost);
	 * 
	 * return "Post inserted successfully";
	 * }
	 */
	/*
	 * correct
	 * 
	 * @PostMapping(path = "/insertPost", consumes = {"multipart/form-data"})
	 * public String insertPost(
	 * 
	 * @RequestParam("userId") String userID,
	 * 
	 * @RequestParam("content_type") String contentType,
	 * 
	 * @RequestParam("image") MultipartFile image) throws IOException {
	 * // try {
	 * System.out.println(userID);
	 * Posts newPost = new Posts();
	 * //newPost.setPostId(postId);
	 * newPost.setUserID(userID);
	 * newPost.setContentType(contentType);
	 * newPost.setImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
	 * 
	 * Posts posts=postsUpload.createPost(newPost);
	 * if (posts==null)
	 * return "Error uploading image";
	 * else
	 * return "Post inserted successfully";
	 * 
	 * //return "Post inserted successfully";
	 * // } catch (IOException e) {
	 * //e.printStackTrace();
	 * 
	 * //}
	 * }
	 */
	// @RequestParam("userId") String userID,
	@PostMapping(path = "/insertPost", consumes = { "multipart/form-data" })
	public String insertPost(

			@RequestParam("content_type") String contentType,
			@RequestParam(value = "image", required = false) MultipartFile image,
			@RequestParam(value = "video", required = false) MultipartFile video) throws IOException {
		System.out.println(image);
		System.out.println(video);
		if ((image == null || image.isEmpty()) && (video == null || video.isEmpty())) {
			return "No file provided";
		}

		Posts newPost = new Posts();
		// newPost.setUserID(userID);
		// newPost.setUserID("sreeja_sahithi");
		// String USER_NAME = Singleton.getInstance().getUsername();
		String USER_NAME = Singleton.getInstance().getUsername();
		System.out.println(USER_NAME);
		newPost.setUserID(USER_NAME);
		newPost.setContentType(contentType);

		if (image != null && !image.isEmpty()) {
			newPost.setImage(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
		} else if (video != null && !video.isEmpty()) {
			// Handle video upload
			newPost.setVideo(new Binary(BsonBinarySubType.BINARY, video.getBytes()));
		}

		Posts posts = postsUpload.createPost(newPost);
		if (posts == null)
			return "Error uploading file";
		else
			return "Post inserted successfully";
	}

	/*
	 * @GetMapping("/getPhoto/{id}")
	 * public Map<Integer, Map<String, String>> getPhoto(@PathVariable String id) {
	 * //System.out.println("post::::"+post_interface.findOne(id));
	 * //System.out.println("byte::"+post_interface.findById(id).get().getImage());
	 * 
	 * //Binary bin=post_interface.findByPostId(id).getImage();
	 * //System.out.println("image:::"+Base64.getEncoder().encodeToString(bin.
	 * getData()));
	 * 
	 * // return Base64.getEncoder().encodeToString(bin.getData());
	 * //return dispFactory.getInstance("post").display(id);
	 * return postDisplay.displaypost(id);
	 * 
	 * 
	 * }
	 */

	@GetMapping("/getPhoto")
	public Map<Integer, Map<String, String>> getPhoto() {
		// System.out.println("post::::"+post_interface.findOne(id));
		// System.out.println("byte::"+post_interface.findById(id).get().getImage());

		// Binary bin=post_interface.findByPostId(id).getImage();
		// System.out.println("image:::"+Base64.getEncoder().encodeToString(bin.getData()));

		// return Base64.getEncoder().encodeToString(bin.getData());
		// return dispFactory.getInstance("post").display(id);
		String USER_NAME = Singleton.getInstance().getUsername();
		System.out.println(USER_NAME);
		return postDisplay.displaypost(USER_NAME);

	}

	@PostMapping("/deletePost/{postId}")
	public String deletePost(@PathVariable Integer postId) {
		boolean isDeleted = postDelete.deletePostById(postId);
		if (isDeleted) {
			return "Post with ID " + postId + " deleted successfully.";
		} else {
			return "Error deleting post with ID " + postId + ".";
		}
	}
	/*
	 * @GetMapping(path="/getPost/{postId}")
	 * public Posts getPost(@PathVariable Integer postId) {
	 * return post_interface.findByPostId(postId);
	 * }
	 */

}
