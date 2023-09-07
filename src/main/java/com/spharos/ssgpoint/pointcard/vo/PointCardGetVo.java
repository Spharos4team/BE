package com.spharos.ssgpoint.pointcard.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointCardGetVo {

    private LocalDate createdDate;
    private String number;
    private String agency;

}
