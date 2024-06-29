package com.insta.Instagram_clone.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.*;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Service
public class MakeComments {
	@Autowired
	public Comments_interface comments_interface;
	@Autowired
	public Post_interface post_interface;

	@Autowired
	MongoOperations mongoOperations;

	@Autowired
	private MongoTemplate mongoTemplate;

	public Comments createcomment(Comments comment) {

		Query query = new Query();
		Comments document = mongoTemplate.findOne(query.with(Sort.by(Sort.Direction.DESC, "commentId")).limit(1),
				Comments.class);
		comment.setCommentId(document.getCommentId() + 1);

		Comments new_comment = comments_interface.save(comment);

		Posts post = post_interface.findByPostId(comment.getPostId());
		System.out.println();

		if (post != null) {
			List<Integer> comments = post.getComments();
			comments.add(new_comment.getCommentId());

			Update update = new Update().set("comments", comments);
			mongoTemplate.updateFirst(query(where("postId").is(comment.getPostId())), update, Posts.class);
		}

		return new_comment;

	}

	/*
	 * public Posts updateCommentsInPosts(Comments comments,Posts post) {
	 * //Query query = new
	 * Query().addCriteria(Criteria.where("cityName").is(oldCityName));
	 * List<Integer> ls=new ArrayList<Integer>();
	 * if (post.getComments()==null) {
	 * 
	 * ls.add(comments.getCommentId());
	 * 
	 * }
	 * else {
	 * ls=post.getComments();
	 * ls.add(comments.getCommentId());
	 * }
	 * Update updateDefinition = new Update().set("comments", ls);
	 * FindAndModifyOptions options = new
	 * FindAndModifyOptions().returnNew(true).upsert(true);
	 * 
	 * return
	 * mongoOperations.findAndModify(query(where("postId").is(post.getPostId())),
	 * updateDefinition, options, Posts.class);
	 * }
	 */
}