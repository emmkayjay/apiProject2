package com.example.springbootmonolith.Service;

import com.example.springbootmonolith.Repository.PostRepository;
import com.example.springbootmonolith.Repository.UserRepository;
import com.example.springbootmonolith.model.Post;
import com.example.springbootmonolith.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    /**
     *
     * @param newPost
     * @return saved post.
     */
    @Override
    public Post createPost(Post newPost, String username) {
        User user = userRepository.findByUsername(username);
        newPost.setUser(user);

        return postRepository.save(newPost);
    }

    /**
     *
     * @return list of all posts.
     */
    @Override
    public Iterable<Post> listPosts() {
        return postRepository.findAll();
    }

    /**
     *
     * @param postId
     * @return httpstatus ok if delete post successful.
     */
    @Override
    //consider returning as void//
    public HttpStatus deletePost(Long postId) {
        postRepository.deleteById(postId);
        return HttpStatus.OK;
    }

}

