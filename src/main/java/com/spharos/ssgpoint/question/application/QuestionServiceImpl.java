package com.spharos.ssgpoint.question.application;

import com.spharos.ssgpoint.question.domain.Comment;
import com.spharos.ssgpoint.question.domain.Question;
import com.spharos.ssgpoint.question.domain.QuestionTag;
import com.spharos.ssgpoint.question.dto.QuestionDTO;
import com.spharos.ssgpoint.question.infrastructure.CommentRepository;
import com.spharos.ssgpoint.question.infrastructure.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final CommentRepository commentRepository;


    /**
     * 문의글을 생성합니다.
     *
     * @param question 생성할 문의글 정보가 담긴 DTO.
     * @return 생성된 문의글의 ID.
     */
    @Override
    public Long createQuestion(Question question) {
        List<QuestionTag> selectedTags = question.getTags();
        List<QuestionTag> tags = selectedTags.stream()
                .map(tag -> {
                    QuestionTag questionTag = new QuestionTag();
                    questionTag.setName(tag.getName());
                    questionTag.setQuestion(question); // 각 태그에 현재의 Question을 설정
                    return questionTag;
                })
                .collect(Collectors.toList());

        question.setStatus("답변대기"); // 상태를 "답변 대기"로 설정
        question.setTags(tags); // 새로 생성된 태그 리스트를 Question 엔터티에 설정
        Question savedQuestion = questionRepository.save(question);
        return savedQuestion.getId();
    }


    /**
     * 문의글을 삭제합니다.
     *
     * @param questionId 삭제할 문의글의 ID.
     */
    @Override
    public Long createComment(Long questionId, Comment comment) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("문의글을 찾을 수 없습니다 " + questionId));
        comment.setQuestion(question);
        Comment savedComment = commentRepository.save(comment);

        // 답변이 달리면 문의글 상태를 "답변 완료"로 업데이트
        question.setStatus("답변완료");
        questionRepository.save(question);

        return savedComment.getId();
    }

    /**
     *  문의글을 삭제합니다.
     * @param uuid
     * @return
     */
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

                    // 문의글에 달린 댓글을 QuestionDTO에 설정
                    List<String> comments = question.getComments().stream()
                            .map(Comment::getComment)
                            .collect(Collectors.toList());
                    dto.setComments(comments);

                    return dto;
                })
                .collect(Collectors.toList());
    }


    /**
     * 문의글을 삭제합니다.
     * @param questionId
     * @return
     */
    @Override
    public String getQuestionStatus(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("문의글을 찾을수 없습니다. " + questionId));
        return question.getStatus();
    }
}
