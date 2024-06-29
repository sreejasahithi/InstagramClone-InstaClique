package com.insta.Instagram_clone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;

@Service
// public class CommentDisplay {
public class CommentDisplay implements Display_Interface<String, String> {

	@Autowired
	public Post_interface post_interface;
	@Autowired
	public Comments_interface comments_interface;

	public Map<String, String> displaycomment(Integer postId) {

		List<Integer> lsc = post_interface.findByPostId(postId).getComments();
		Map<String, String> mp = new HashMap<String, String>();
		for (int i = 0; i < lsc.size(); i++) {
			if (lsc.get(i) != -1) {
				Comments comment = comments_interface.findByCommentId(lsc.get(i));
				mp.put(comment.getCommentFromId(), comment.getCommentData());
			}
		}
		return mp;

	}

	@Override
	public Map<String, String> display(Integer postId) {
		// TODO Auto-generated method stub
		List<Integer> lsc = post_interface.findByPostId(postId).getComments();
		Map<String, String> mp = new HashMap<String, String>();
		// int j=0;
		for (int i = 0; i < lsc.size(); i++) {
			if (lsc.get(i) != -1) {
				Comments comment = comments_interface.findByCommentId(lsc.get(i));
				mp.put(comment.getCommentFromId() + "#" + lsc.get(i), comment.getCommentData());

			}
			// j++;
		}
		return mp;
	}
}
