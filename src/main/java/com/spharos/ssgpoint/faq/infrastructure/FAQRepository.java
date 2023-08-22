package com.spharos.ssgpoint.faq.infrastructure;

import com.spharos.ssgpoint.faq.domain.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FAQRepository extends JpaRepository<FAQ, Long> {
}
