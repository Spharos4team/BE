package com.spharos.ssgpoint.question.dto;

import com.spharos.ssgpoint.question.domain.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private String uuid;
    private String comment;

    public Comment toEntity() {
        Comment comment = new Comment();
        comment.setId(this.id);
        comment.setUuid(this.uuid);
        comment.setComment(this.comment);
        return comment;
    }
}
