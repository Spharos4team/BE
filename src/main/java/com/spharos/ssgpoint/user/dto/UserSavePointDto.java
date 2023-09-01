package com.spharos.ssgpoint.user.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Builder
@Getter
@Setter

@NoArgsConstructor
public class UserSavePointDto {
    private Integer savePoint;

    @QueryProjection
    public UserSavePointDto(Integer savePoint) {
        this.savePoint = savePoint;
    }
}
