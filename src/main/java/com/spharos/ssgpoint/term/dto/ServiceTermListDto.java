package com.spharos.ssgpoint.term.dto;

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
public class ServiceTermListDto {
    private String title;
    private boolean agreed;
    private LocalDateTime checkDate;
}
