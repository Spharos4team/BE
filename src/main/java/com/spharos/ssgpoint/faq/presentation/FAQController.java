package com.spharos.ssgpoint.faq.presentation;

import com.spharos.ssgpoint.faq.application.FAQService;
import com.spharos.ssgpoint.faq.dto.FAQDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/faq")
public class FAQController {

    @Autowired
    private FAQService faqService;

    @GetMapping
    public List<FAQDto> getAllFAQs() {
        return faqService.getAllFAQs();
    }

    // ... more endpoints for CRUD operations
}
