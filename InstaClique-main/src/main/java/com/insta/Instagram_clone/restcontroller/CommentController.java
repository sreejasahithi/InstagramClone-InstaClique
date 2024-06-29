package com.insta.Instagram_clone.restcontroller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import com.insta.Instagram_clone.service.*;

@RestController
public class CommentController {
    @Autowired
    public MakeComments makeComments;

    @Autowired
    public DisplayFactory commentdisplayFact;

    @Autowired
    public CommentDisplay commentdisplay;

    @Autowired
    public DeleteComment delete_comment;

    // @RequestParam("commentFromId") String commentFromId,
    @PostMapping(path = "/makeComment", consumes = { "multipart/form-data" })
    public String insertComment(@RequestParam("postId") Integer postId,

            @RequestParam("commentData") String commentData) throws IOException {
        // try {
        Comments newComment = new Comments();
        String USER = Singleton.getInstance().getUsername();
        newComment.setPostId(postId);
        // newComment.setCommentFromId(commentFromId);
        newComment.setCommentFromId(USER);
        newComment.setCommentData(commentData);

        Comments comments = makeComments.createcomment(newComment);
        if (comments == null)
            return "Error making a comment";
        else
            return "commented successfully";

        // return "Post inserted successfully";
        // } catch (IOException e) {
        // e.printStackTrace();

        // }
    }

    @GetMapping("/getComments/{postId}")
    public Map<String, String> getComment(@PathVariable Integer postId) {

        // return commentdisplay.displaycomment(postId);
        return commentdisplayFact.getInstance("comment").display(postId);

    }
    /*
     * @PostMapping("/Delete/Comments/{postId}")
     * public String deleteComment(@PathVariable Integer
     * postId, @RequestParam("commentData") String commentData) {
     * // Call your service method to delete the comment
     * System.out.println(commentData);
     * return delete_comment.deleteTheComment(postId, "niki_123", commentData);
     * }
     */

    @PostMapping("/deleteComment")
    public String deleteComment(@RequestParam("postId") Integer postId, @RequestParam("userId") String userId) {
        // Add logic to delete the comment based on postId and userId

        String USER = Singleton.getInstance().getUsername();
        String[] parts = userId.split("#");
        String e1 = parts.length > 1 ? parts[1] : userId;

        Integer e2 = Integer.parseInt(e1);
        System.out.println(postId);
        System.out.println(e2);

        return delete_comment.deleteTheComment(postId, e2, USER);

        // return "Comment deleted successfully";
    }

}
