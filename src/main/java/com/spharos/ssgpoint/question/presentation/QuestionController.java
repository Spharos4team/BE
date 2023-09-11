package com.spharos.ssgpoint.question.presentation;

import com.spharos.ssgpoint.question.application.QuestionService;
import com.spharos.ssgpoint.question.dto.CommentDTO;
import com.spharos.ssgpoint.question.dto.QuestionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/question")
    public ResponseEntity<Long> createQuestion(@RequestBody QuestionDTO questionDTO) {
        Long questionId = questionService.createQuestion(questionDTO.toEntity());
        return ResponseEntity.status(201).body(questionId);
    }

    @PostMapping("/question/{questionId}/comments")
    public ResponseEntity<Long> createComment(@PathVariable Long questionId, @RequestBody CommentDTO commentDTO) {
        Long commentId = questionService.createComment(questionId, commentDTO.toEntity());
        return ResponseEntity.status(201).body(commentId);
    }

    @GetMapping("/question")
    public ResponseEntity<List<QuestionDTO>> getUserQuestions(@RequestParam String uuid) {
        List<QuestionDTO> questions = questionService.getUserQuestions(uuid);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/question/{questionId}/status")
    public ResponseEntity<String> getQuestionStatus(@PathVariable Long questionId) {
        String status = questionService.getQuestionStatus(questionId);
        return ResponseEntity.ok(status);
    }
}