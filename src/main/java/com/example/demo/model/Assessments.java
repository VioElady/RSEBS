package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "assessments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Assessments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "post_id", unique = true)
    private Long postId;

    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "like")
    private boolean like;


}

