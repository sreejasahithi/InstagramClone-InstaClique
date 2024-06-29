package com.insta.Instagram_clone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;

@Service
public class PostsDelete {

	@Autowired
	public Post_interface post_interface;

	@Autowired
	public PostsDeleteRepo postDeleteRepo;

	public boolean deletePostById(Integer postId) {
		try {
			// Find the post by ID
			Posts post = post_interface.findByPostId(postId);

			// If post exists, delete it
			if (post != null) {
				System.out.println("delete bfr");
				postDeleteRepo.setPostid(postId);
				postDeleteRepo.execute();
				// post_interface.deleteById(postId);
				// post_interface.deleteByPostId(postId);
				// post_interface.delete(post);
				return true; // Deletion successful
			} else {
				// Post with the given ID not found
				return false; // Deletion failed
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false; // Deletion failed
		}
	}

}
