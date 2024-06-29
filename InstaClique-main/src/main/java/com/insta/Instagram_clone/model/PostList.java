package com.insta.Instagram_clone.model;
// import com.insta.insta.models.Account;

import com.insta.Instagram_clone.model.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Component
@Document("postList")
public class PostList {

	@Id
	private String id;

	@Field("userId")
	private String userId;

	private List<Integer> postList = new ArrayList<>();

	// Getter and setter methods

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getuserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Integer> getPostList() {
		return postList;
	}

	public void setPostList(List<Integer> postList) {
		this.postList = postList;

	}

	public void dispay() {
		for (Integer id : this.postList) {
			System.out.println(id);
		}
	}

	public void addPost(Integer postId) {
		this.postList.add(postId);
	}
}
