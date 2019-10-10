package com.example.springbootmonolith.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

@Entity
@Table(name = "post")
public class Post {
    /**
     * setting 'post' datatable.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String title;

    @Column
    private String body;

    /**
     * user_id references 'users' table.
     */
    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * post_id references comment table.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL)
    private List<Comment> comments;

    public List<Comment> addComment (Comment comment){
        if (comments == null)
            comments = new ArrayList<>();
        comments.add(comment);

        return comments;
    }


    public Post(){}

   public List<Comment> getComments(){return comments;}

   public void setComments(List<Comment> comments){this.comments = comments;}

    public User getUser(){return user;}

    public void setUser(User user){this.user = user;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}