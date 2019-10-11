package com.example.springbootmonolith.Service;

import com.example.springbootmonolith.model.Post;
import com.example.springbootmonolith.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

public interface UserService extends UserDetailsService {

    /**
     *
     * @param user
     * @return
     */
    public String login (User user) throws Exception;

    /**
     *
     * @param newUser
     * @return
     */
    public String createUser (User newUser) throws Exception;


    /**
     *
     * @return
     */
    public Iterable<User> listUsers();

    /**
     *
     * @param username
     * @return
     */
    public User getUser(String username);

    /**
     *
     * @param userId
     * @return
     */
    public HttpStatus deleteById(Long userId);


}
