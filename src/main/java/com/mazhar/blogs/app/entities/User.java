package com.mazhar.blogs.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name",nullable = false, length = 100)
    private String name;

    private String email;

    private String password;

    private String about;

    @OneToMany(mappedBy="user", cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Post> postId;
}
