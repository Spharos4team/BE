package com.spharos.ssgpoint.faq.presentation;

import com.spharos.ssgpoint.faq.application.FAQService;
import com.spharos.ssgpoint.faq.dto.FAQDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FAQController {

    private final FAQService faqService;

    @GetMapping
    public List<FAQDto> getAllFAQs() {
        return faqService.getAllFAQs();
    }

    @GetMapping("/fap/faq_id={faq_id}")
    public FAQDto getFAQById(@PathVariable Long faq_id) {
        // TODO: Implement the method to get FAQ by ID
        return null;
    }

    @PostMapping("/faq")
    public void createFAQ(@RequestBody FAQDto faqDto) {
        // TODO: Implement the method to create a new FAQ
    }

    @DeleteMapping("/faq/faq_id={faq_id}")
    public void deleteFAQ(@PathVariable Long faq_id) {
        // TODO: Implement the method to delete FAQ by ID
    }
}
