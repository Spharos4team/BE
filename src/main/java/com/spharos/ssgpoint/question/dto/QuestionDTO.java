package com.spharos.ssgpoint.question.dto;

import com.spharos.ssgpoint.question.domain.Question;
import com.spharos.ssgpoint.question.domain.QuestionTag;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class QuestionDTO {
    private Long id;
    private String uuid;
    private String title;
    private String description;
    private String status;
    private List<String> tags; // 태그(카테고리) 목록을 문자열 리스트로 받음

    public Question toEntity() {
        Question question = new Question();
        question.setId(this.id);
        question.setUuid(this.uuid);
        question.setTitle(this.title);
        question.setDescription(this.description);
        question.setStatus(this.status);


        if (tags != null) {
            List<QuestionTag> questionTags = tags.stream()
                    .map(tagName -> {
                        QuestionTag questionTag = new QuestionTag();
                        questionTag.setName(tagName);
                        questionTag.setQuestion(question);
                        return questionTag;
                    })
                    .collect(Collectors.toList());
            question.setTags(questionTags);
        }

        return question;
    }

    private List<String> comments; // 문의글에 달린 댓글을 저장하는 필드
}
