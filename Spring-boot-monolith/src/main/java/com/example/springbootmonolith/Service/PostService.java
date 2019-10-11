package com.example.springbootmonolith.Service;

import com.example.springbootmonolith.model.Post;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface PostService {

    /**
     *
     * @param newPost
     * @return
     */
    public Post createPost(Post newPost, String username);

    /**
     *
     * @return
     */
    public Iterable<Post> listPosts();

    /**
     *
     * @param postId
     * @return
     */
    public HttpStatus deletePost(Long postId);


}
