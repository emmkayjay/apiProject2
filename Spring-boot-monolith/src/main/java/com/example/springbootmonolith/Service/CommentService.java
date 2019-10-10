package com.example.springbootmonolith.Service;

import com.example.springbootmonolith.model.Comment;
import org.springframework.http.HttpStatus;

public interface CommentService {

    /**
     *
     * @param newComment
     * @param postId
     * @return
     */
    public Comment createComment (Comment newComment, long postId);

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
    public HttpStatus deleteComment(long commentId);
}
