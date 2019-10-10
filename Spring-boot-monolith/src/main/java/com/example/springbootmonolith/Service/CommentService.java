package com.example.springbootmonolith.Service;

import com.example.springbootmonolith.model.Comment;
import com.example.springbootmonolith.model.User;
import org.springframework.http.HttpStatus;

public interface CommentService {

    /**
     *
     * @param newComment
     * @param postId
     * @return
     */
    public Comment createComment (Comment newComment, Long postId, String username);

    /**
     *
     * @return
     */
    public Iterable<Comment> listComments();

    /**
     *
     * @param commentId
     * @return
     */
    public HttpStatus deleteComment(Long commentId);
}
