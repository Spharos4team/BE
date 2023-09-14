package com.spharos.ssgpoint.pointgift.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointGiftCheckDto {
    private Long id;
    private Integer point;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime localDate;
}
