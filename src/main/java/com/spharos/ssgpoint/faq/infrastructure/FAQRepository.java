package com.spharos.ssgpoint.faq.infrastructure;

import com.spharos.ssgpoint.faq.domain.FAQ;
import com.spharos.ssgpoint.faq.domain.FAQCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FAQRepository extends JpaRepository<FAQ, Long> {
    List<FAQ> findByCategoryIn(List<FAQCategory> relevantCategories);
}
