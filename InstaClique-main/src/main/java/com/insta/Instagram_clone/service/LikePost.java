package com.insta.Instagram_clone.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
//import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;

import org.springframework.data.mongodb.core.query.Query;

@Service
public class LikePost {

	@Autowired
	public Likes_interface likes_interface;
	@Autowired
	public Post_interface post_interface;

	@Autowired
	MongoOperations mongoOperations;

	@Autowired
	private MongoTemplate mongoTemplate;

	public Likes createlike(Likes like) {
		Posts post = post_interface.findByPostId(like.getPostId());
		List<Integer> likes = post.getLikes();
		int flag = 0;
		int IDindex = 0;
		Integer likeId = 0;
		for (int i = 0; i < likes.size(); i++) {
			if (likes.get(i) != -1) {

				Likes likeDoc = likes_interface.findByLikeId(likes.get(i));

				if (likeDoc.getLikeFromId().equals(like.getLikeFromId())) {
					System.out.println(likeDoc.getLikeFromId());
					IDindex = i;
					likeId = likeDoc.getLikeId();
					flag = 1;
					break;
				}

			}

		}

		if (flag == 1) {
			likes.remove(IDindex);

			mongoOperations.remove(query(where("likeId").is(likeId)), "Likes");
			// post_interface.save(post);

			Update update = new Update().set("likes", likes);
			mongoTemplate.updateFirst(query(where("postId").is(like.getPostId())), update, Posts.class);

			return like;

		}

		else {
			Query query = new Query();
			Likes document = mongoTemplate.findOne(query.with(Sort.by(Sort.Direction.DESC, "likeId")).limit(1),
					Likes.class);
			like.setLikeId(document.getLikeId() + 1);

			Likes new_like = likes_interface.save(like);
			// Posts post=post_interface.findByPostId(like.getPostId());
			System.out.println();
			/*
			 * List<Integer> ls=new ArrayList<Integer>();
			 * if (post.getLikes()==null) {
			 * 
			 * ls.add(new_like.getLikeId());
			 * 
			 * }
			 * else {
			 * ls=post.getLikes();
			 * ls.add(new_like.getLikeId());
			 * }
			 * post.setLikes(ls);
			 * //updateCommentsInPosts(new_like,post);
			 * post_interface.save(post);
			 * return new_like;
			 */
			;

			if (post != null) {
				// List<Integer> likes = post.getLikes();
				likes.add(new_like.getLikeId());
				// post.setLikes(likes);
				// post_interface.save(post); // Save the updated post
				Update update = new Update().set("likes", likes);
				mongoTemplate.updateFirst(query(where("postId").is(like.getPostId())), update, Posts.class);
			}
			return new_like;

		}

	}

	/*
	 * public Posts updateLikesInPosts(Likes like,Posts post) {
	 * //Query query = new
	 * Query().addCriteria(Criteria.where("cityName").is(oldCityName));
	 * List<Integer> ls=new ArrayList<Integer>();
	 * if (post.getLikes()==null) {
	 * 
	 * ls.add(like.getLikeId());
	 * 
	 * }
	 * else {
	 * ls=post.getComments();
	 * ls.add(like.getLikeId());
	 * }
	 * Update updateDefinition = new Update().set("Likes", ls);
	 * FindAndModifyOptions options = new
	 * FindAndModifyOptions().returnNew(true).upsert(true);
	 * 
	 * return
	 * mongoOperations.findAndModify(query(where("postId").is(post.getPostId())),
	 * updateDefinition, options, Posts.class);
	 * }
	 */
}
