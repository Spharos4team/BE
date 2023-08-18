package com.spharos.ssgpoint.faq.application;

import com.spharos.ssgpoint.faq.domain.FAQ;
import com.spharos.ssgpoint.faq.dto.FAQDto;
import com.spharos.ssgpoint.faq.infrastructure.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FAQService {

    @Autowired
    private FAQRepository faqRepository;

    public List<FAQDto> getAllFAQs() {
        List<FAQ> faqs = faqRepository.findAll();
        return faqs.stream().map(faq -> new FAQDto(faq.getClass(), faq.getClass(), faq.getClass(), faq.getClass())).collect(Collectors.toList());
    }

    // ... more methods for CRUD operations
}
