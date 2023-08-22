package com.spharos.ssgpoint.faq.dto;

import com.spharos.ssgpoint.faq.domain.FAQ;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FAQDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime regDate;

    public FAQDto(Class<? extends FAQ> aClass, Class<? extends FAQ> aClass1, Class<? extends FAQ> aClass2, Class<? extends FAQ> aClass3) {
    }
}
