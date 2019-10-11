package com.example.springbootmonolith.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
    property ="id")
@Entity
@Table (name = "users")
public class User {
    /**
     * setting users datatable.
     */
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,
            nullable = false)
    private String username;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;


    /**
     * user_profile_id references UserProfile datatable.
     */
    //CascadeType.MERGE allows for the columns to join.
    // CascadeType.ALL creates functionality redundancies.
    // Original error was "detached entity passed to persist."
    @OneToOne (cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE})
    @JoinColumn (name = "user_profile_id")
    private UserProfile userProfile;

    /**
     * Post table references users table via user_id join column.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "user",
            cascade= {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE})
    private List<Post> posts;

    /**
     * Comment table references users table via user_id join column.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.MERGE, CascadeType.REFRESH,CascadeType.REMOVE})
        private List<Comment> comments;

    public User() {
    }

    /**
     *
     * @param comment
     * @return
     */
    public List<Comment> addComment (Comment comment){
        if (comments == null)
            comments = new ArrayList<>();
        comments.add(comment);

        return comments;
    }

    /**
     *
     * @param post
     * @return
     */
    public List<Post> addPost (Post post){
        if (posts == null)
            posts = new ArrayList<>();
        posts.add(post);

        return posts;
    }

    public List<Comment> getComments(){return comments;}

    public void setComments(List<Comment> comments){this.comments = comments;}

    public List<Post> getPosts(){return posts;}

    public void setPosts(List<Post> posts){this.posts = posts;}

    public UserProfile getUserProfile(){return userProfile;}

    public void setUserProfile(UserProfile userProfile){this.userProfile = userProfile;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}