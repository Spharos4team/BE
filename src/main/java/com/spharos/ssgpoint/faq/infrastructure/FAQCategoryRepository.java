package com.spharos.ssgpoint.faq.infrastructure;

import com.spharos.ssgpoint.faq.domain.FAQCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FAQCategoryRepository extends JpaRepository<FAQCategory, Long> {

    List<FAQCategory> findByParentCategoryIsNull();
    List<FAQCategory> findByParentCategory(FAQCategory parentCategory);
    List<FAQCategory> findByParentCategory_Id(Long parentId);

}
