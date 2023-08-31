package com.spharos.ssgpoint.faq.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FAQDto {

    private Long id;
    private String title;
    private String content;

}
