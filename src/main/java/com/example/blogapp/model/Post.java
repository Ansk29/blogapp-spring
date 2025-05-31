package com.example.blogapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 10000)
    private String content;

    private String imageName;

    private Date addedDate;

    // Many posts → one category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Many posts → one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
