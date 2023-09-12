package com.spharos.ssgpoint.faq.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FAQDTO {

    private Long id; // FAQ의 ID도 추가하여 특정 FAQ를 식별할 수 있게 합니다.
    private Long categoryId; // FAQ가 속한 카테고리의 ID
    private String parentCategoryName; // FAQ가 속한 카테고리의 부모 카테고리의 이름
    private String categoryName; // FAQ가 속한 카테고리의 이름
    private String question;
    private String answer;

}