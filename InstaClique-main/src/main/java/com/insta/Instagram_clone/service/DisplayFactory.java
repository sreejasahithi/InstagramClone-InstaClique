package com.insta.Instagram_clone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisplayFactory<K> {
	@Autowired
	LikeDisplay like;
	@Autowired
	CommentDisplay comment;

	public <T, U> Display_Interface<T, U> getInstance(String str) {
		if (str.equals("like")) {
			// PostsDisplay<Integer,String,String> posts=new PostsDisplay();
			// return DsiplayRecord.Posts.make() ;
			return (Display_Interface<T, U>) like;
		} else if (str.equals("comment")) {
			return (Display_Interface<T, U>) comment;
		}
		/*
		 * else {
		 * return new windows();
		 * }
		 */
		return null;
	}

}
