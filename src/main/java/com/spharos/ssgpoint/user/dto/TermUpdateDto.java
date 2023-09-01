package com.spharos.ssgpoint.user.dto;

import lombok.*;

import java.util.Map;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TermUpdateDto {
    private Map<String,Boolean> termJson;
}
