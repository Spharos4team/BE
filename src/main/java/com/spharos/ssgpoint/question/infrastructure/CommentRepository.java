package com.spharos.ssgpoint.question.infrastructure;

import com.spharos.ssgpoint.question.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
