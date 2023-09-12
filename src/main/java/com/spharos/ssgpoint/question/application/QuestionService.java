package com.spharos.ssgpoint.question.application;

import com.spharos.ssgpoint.question.domain.Comment;
import com.spharos.ssgpoint.question.domain.Question;
import com.spharos.ssgpoint.question.dto.QuestionDTO;

import java.util.List;

public interface QuestionService {

    Long createQuestion(Question question  );

    Long createComment(Long questionId, Comment comment);

    List<QuestionDTO> getUserQuestions(String uuid);

    String getQuestionStatus(Long questionId);
}