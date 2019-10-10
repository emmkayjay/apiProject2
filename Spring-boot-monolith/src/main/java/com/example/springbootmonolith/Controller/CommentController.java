package com.example.springbootmonolith.Controller;

import com.example.springbootmonolith.Service.CommentService;
import com.example.springbootmonolith.model.Comment;
import com.example.springbootmonolith.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/{username}")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/{postId}/comment")
    public Comment createComment(@RequestBody Comment newComment, @PathVariable Long postId, String username) {
        return commentService.createComment(newComment, postId, username);
    }

    @GetMapping("/comment/list")
    public Iterable<Comment> listComments() { return commentService.listComments(); }

    @DeleteMapping("/comment/delete/{commentId}")
    public HttpStatus deleteComment(@PathVariable Long commentId) { return commentService.deleteComment(commentId); }

}
