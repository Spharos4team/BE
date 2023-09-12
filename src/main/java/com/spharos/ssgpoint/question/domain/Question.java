package com.spharos.ssgpoint.question.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;
    private String title;
    private String description;
    private String status = "답변대기"; // Default status

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionTag> tags = new ArrayList<>();

}