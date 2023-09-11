package com.spharos.ssgpoint.question.dto;

import com.spharos.ssgpoint.question.domain.Question;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {
    private Long id;
    private String uuid;
    private String title;
    private String content;
    private String status;

    public Question toEntity() {
        Question question = new Question();
        question.setId(this.id);
        question.setUuid(this.uuid);
        question.setTitle(this.title);
        question.setDescription(this.content);
        question.setStatus(this.status);
        return question;
    }
}
