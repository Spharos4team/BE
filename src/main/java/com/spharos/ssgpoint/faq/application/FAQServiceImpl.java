package com.spharos.ssgpoint.faq.application;

import com.spharos.ssgpoint.faq.domain.FAQ;
import com.spharos.ssgpoint.faq.dto.FAQDto;
import com.spharos.ssgpoint.faq.infrastructure.FAQRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {

    private final FAQRepository faqRepository;  // final 키워드 추가

    /**
     * @return {@연결 클래스}
     */
    public List<FAQDto> getAllFAQs() {
        List<FAQ> faqs = faqRepository.findAll();
        return faqs.stream().map(faq ->
                new FAQDto(faq.getId(), faq.getTitle(), faq.getContent(), faq.getRegDate())
        ).collect(Collectors.toList());
    }

}
