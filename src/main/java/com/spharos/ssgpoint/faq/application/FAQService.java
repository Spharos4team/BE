package com.spharos.ssgpoint.faq.application;

import com.spharos.ssgpoint.faq.dto.FAQDto;
import java.util.List;

public interface FAQService {
    List<FAQDto> getAllFAQs();

    // ... other methods for CRUD operations
}