package com.spharos.ssgpoint.question.application;

import com.spharos.ssgpoint.question.domain.Comment;
import com.spharos.ssgpoint.question.domain.Question;
import com.spharos.ssgpoint.question.dto.QuestionDTO;
import com.spharos.ssgpoint.question.infrastructure.CommentRepository;
import com.spharos.ssgpoint.question.infrastructure.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final CommentRepository commentRepository;


    @Transactional
    @Override
    public Long createQuestion(Question question) {
        Question savedQuestion = questionRepository.save(question);
        return savedQuestion.getId();
    }

    @Transactional
    @Override
    public Long createComment(Long questionId, Comment comment) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found: " + questionId));
        comment.setQuestion(question);
        Comment savedComment = commentRepository.save(comment);
        return savedComment.getId();
    }


    @Override
    public List<QuestionDTO> getUserQuestions(String uuid) {
        List<Question> questions = questionRepository.findByUuid(uuid);
        return questions.stream()
                .map(question -> {
                    QuestionDTO dto = new QuestionDTO();
                    dto.setId(question.getId());
                    dto.setUuid(question.getUuid());
                    dto.setTitle(question.getTitle());
                    dto.setDescription(question.getDescription());
                    dto.setStatus(question.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());
    }


    @Override
    public String getQuestionStatus(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found: " + questionId));
        return question.getStatus();
    }
}
