package com.spharos.ssgpoint.question.infrastructure;

import com.spharos.ssgpoint.question.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByUuid(String uuid);
}
