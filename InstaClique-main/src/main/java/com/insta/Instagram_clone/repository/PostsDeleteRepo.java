package com.insta.Instagram_clone.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class PostsDeleteRepo {

	@Autowired
	MongoOperations mongoOperations;

	private Integer postid;

	public Integer getPostid() {
		return postid;
	}

	public void setPostid(Integer postid) {
		this.postid = postid;
	}

	public void execute() {

		mongoOperations.remove(query(where("postId").is(postid)), "Posts");

		System.out.println("deleted:::");
	}

}
