package com.insta.Instagram_clone.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
// import com.example.instagram_clone.repository.Comments_interface;
// import com.example.instagram_clone.repository.Post_interface;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class DeleteComment {
	@Autowired
	public Post_interface post_interface;

	@Autowired
	public Comments_interface comments_interface;

	@Autowired
	MongoOperations mongoOperations;

	@Autowired
	private MongoTemplate mongoTemplate;

	public String deleteTheComment(Integer postId, Integer commentId, String userId) {
		// String user=userId.substring(0, userId.length() - 2);
		List<Integer> lsc = post_interface.findByPostId(postId).getComments();

		Comments commentdoc = comments_interface.findByCommentId(commentId);

		if (userId.equals(commentdoc.getCommentFromId())) {
			int index = 0;
			for (int i = 0; i < lsc.size(); i++) {
				if (commentId.equals(lsc.get(i))) {
					index = i;
					break;
				}
			}
			lsc.remove(index);

			mongoOperations.remove(query(where("commentId").is(commentId)), "Comments");
			// post_interface.save(post);

			Update update = new Update().set("comments", lsc);
			mongoTemplate.updateFirst(query(where("postId").is(postId)), update, Posts.class);

			return "Deletion done";

		}

		return "Warning can not Delete";
	}

}
