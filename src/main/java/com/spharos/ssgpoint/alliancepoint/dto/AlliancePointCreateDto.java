package com.spharos.ssgpoint.alliancepoint.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlliancePointCreateDto {

    private Integer point;
    private String type;
    private String UUID;

}
