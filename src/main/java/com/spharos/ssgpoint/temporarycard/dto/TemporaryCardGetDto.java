package com.spharos.ssgpoint.temporarycard.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemporaryCardGetDto {

    private Long id;
    private String birth;
    private String name;
    private String number;
    private Integer CVC;
    private String agency;

}
