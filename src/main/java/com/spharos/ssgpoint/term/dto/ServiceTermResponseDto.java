package com.spharos.ssgpoint.term.dto;

import com.spharos.ssgpoint.global.BaseEntity;
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
public class ServiceTermResponseDto  {
    private LocalDate updateDate;
    private String title;
    private boolean agreed;
}
