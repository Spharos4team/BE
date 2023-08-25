package com.spharos.ssgpoint.temporarycard.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemporaryCardGetVo {

    private Long id;
    private String birth;
    private String name;
    private String number;
    private Integer CVC;
    private String agency;

}
