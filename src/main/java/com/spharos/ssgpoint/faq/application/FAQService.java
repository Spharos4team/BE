package com.spharos.ssgpoint.faq.application;

import com.spharos.ssgpoint.faq.domain.FAQCategory;
import com.spharos.ssgpoint.faq.dto.FAQDTO;

import java.util.List;

public interface FAQService {
    void createFAQ(FAQDTO faqDTO);
    void deleteFAQ(Long id);
    List<FAQDTO> getFAQsByCategory(Long categoryId, Long subCategoryId); // 파라미터를 업데이트
    List<FAQDTO> getAllFAQs();

    List<FAQCategory> getParentCategories();
    List<FAQCategory> getSubCategories(Long parentCategoryId);

}
