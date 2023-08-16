package com.spharos.ssgpoint.pointcard.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointCardGetDto {

    private Long pointCardId;
    private String name;
    private String number;
    private String agency;

}
