package com.spharos.ssgpoint.pointcard.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointCardCreateDto {

    private String number;
    private String agency;
    private String UUID;
    private String pointCardType;

}
