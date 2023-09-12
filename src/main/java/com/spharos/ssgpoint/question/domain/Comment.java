package com.spharos.ssgpoint.question.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}