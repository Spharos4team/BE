package com.spharos.ssgpoint.faq.infrastructure;

import com.spharos.ssgpoint.faq.domain.FAQCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FAQCategoryRepository extends JpaRepository<FAQCategory, Long> {

    Optional<FAQCategory> findByName(String name);
    List<FAQCategory> findByParentCategoryIsNull();
    List<FAQCategory> findByParentCategoryId(Long parentId);

}
