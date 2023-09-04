package com.spharos.ssgpoint.faq.application;

import com.spharos.ssgpoint.faq.dto.FAQDto;

import java.util.List;

public interface FAQService {
    /**
     * @return {@연결 클래스}
     */
    List<FAQDto> getAllFAQs();




}