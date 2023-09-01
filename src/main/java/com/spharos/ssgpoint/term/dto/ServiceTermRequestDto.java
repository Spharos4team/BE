package com.spharos.ssgpoint.term.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceTermRequestDto {

    private String title; //해당 서비스 이름
    private boolean agreed;
}
