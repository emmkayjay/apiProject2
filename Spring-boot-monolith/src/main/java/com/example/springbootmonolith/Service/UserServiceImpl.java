package com.example.springbootmonolith.Service;

import com.example.springbootmonolith.Config.JwtUtil;
import com.example.springbootmonolith.Repository.CommentRepository;
import com.example.springbootmonolith.Repository.PostRepository;
import com.example.springbootmonolith.Repository.UserRepository;
import com.example.springbootmonolith.model.Post;
import com.example.springbootmonolith.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    /**
     * Autowiring postRepository for addPost functionality
     */
    @Autowired
    PostRepository postRepository;

    /**
     * Autowiring commentRepository for addComment functionality.
     */

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    JwtUtil jwtUtil;

    /**
     *Throws exception if password/username are incorrect.
     * @param user
     * @return
     */
    @Override
    public String login(User user) throws Exception {
        User newUser = userRepository.findByUsername(user.getUsername());
        if(newUser != null && user.getPassword().equals(newUser.getPassword())){
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());

            return jwtUtil.generateToken(userDetails);
        }else{
            throw new Exception("Username or Password are incorrect. Please try again.");
        }

    }

    /**
     *
     * @param newUser
     * @return
     */
    @Override
    public String createUser(User newUser) throws Exception{
        newUser.setUsername(newUser.getUsername());
        newUser.setPassword(newUser.getPassword());
        newUser.setEmail(newUser.getEmail());
        if(userRepository.save(newUser) != null){
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        else {
            throw new Exception("Username or Email already in use, please choose another.");
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }

    /**
     *
     * @param username
     * @return
     */
    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     *
     * @param userId
     * @return
     */
    @Override
    public HttpStatus deleteById(Long userId) {
        userRepository.deleteById(userId);
        return HttpStatus.OK;
    }

    /**
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException // functionality used for JWT header generation.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);

        if(user==null)
            throw new UsernameNotFoundException("User null");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true, true, getGrantedAuthorities(user));
    }

    /**
     *
     * @param user
     * @return
     */
    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(user.getUsername()));

        return authorities;
    }

}
