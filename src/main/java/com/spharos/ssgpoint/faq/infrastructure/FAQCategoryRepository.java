package com.spharos.ssgpoint.faq.infrastructure;

import com.spharos.ssgpoint.faq.domain.FAQCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FAQCategoryRepository extends JpaRepository<FAQCategory, Long> {
}
