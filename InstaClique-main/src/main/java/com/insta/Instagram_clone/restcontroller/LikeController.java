package com.insta.Instagram_clone.restcontroller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import com.insta.Instagram_clone.service.*;

@RestController
public class LikeController {
	@Autowired
	public LikePost likepost;

	@Autowired
	public LikeDisplay likedisplay;

	@Autowired
	public DisplayFactory likedisplayFact;

	// @RequestParam("likeFromId") String likeFromId
	@PostMapping(path = "/Like", consumes = { "multipart/form-data" })
	public String insertLike(
			@RequestParam("postId") Integer postId

	) throws IOException {
		// try {
		Likes newLike = new Likes();

		newLike.setPostId(postId);
		// newLike.setLikeFromId(likeFromId);
		String USER_NAME = Singleton.getInstance().getUsername();
		newLike.setLikeFromId(USER_NAME);

		Likes likes = likepost.createlike(newLike);
		if (likes == null)
			return "Error liking";
		else
			return "liked successfully";

		// return "Post inserted successfully";
		// } catch (IOException e) {
		// e.printStackTrace();

		// }
	}

	@GetMapping("/getLikes/{postId}")
	// public Integer getLikes(@PathVariable Integer postId) {
	public Map<Integer, Integer> getLikes(@PathVariable Integer postId) {
		// System.out.println("post::::"+post_interface.findOne(id));
		// System.out.println("byte::"+post_interface.findById(id).get().getImage());

		// Binary bin=post_interface.findByPostId(id).getImage();
		// System.out.println("image:::"+Base64.getEncoder().encodeToString(bin.getData()));

		// return Base64.getEncoder().encodeToString(bin.getData());
		// return likedisplay.displaylike(postId);
		return likedisplayFact.getInstance("like").display(postId);

	}

}
