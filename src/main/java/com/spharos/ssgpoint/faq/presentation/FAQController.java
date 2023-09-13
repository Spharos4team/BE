package com.spharos.ssgpoint.faq.presentation;

import com.spharos.ssgpoint.faq.Exception.ResourceNotFoundException;
import com.spharos.ssgpoint.faq.application.FAQService;
import com.spharos.ssgpoint.faq.domain.FAQCategory;
import com.spharos.ssgpoint.faq.dto.FAQDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FAQController {

    private final FAQService faqService;

    @PostMapping("/faq")
    public ResponseEntity createFAQ(@RequestBody FAQDTO faqDTO) {
        faqService.createFAQ(faqDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("FAQ가 등록되었습니다");
    }

    @GetMapping("/faq")
    public ResponseEntity<List<FAQDTO>> getAllFAQs() {
        List<FAQDTO> faqs = faqService.getAllFAQs();
        if(faqs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(faqs);
        }
        return ResponseEntity.ok(faqs);
    }
    @GetMapping("/faq/{categoryId}")
    public ResponseEntity<List<FAQDTO>> getFAQsByCategory(@PathVariable Long categoryId, @RequestParam(required = false) Long subCategoryId) {
        List<FAQDTO> faqsByCategory = faqService.getFAQsByCategory(categoryId, subCategoryId);
        if(faqsByCategory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(faqsByCategory);
        }
        return ResponseEntity.ok(faqsByCategory);
    }

    @DeleteMapping("/faq/{id}")
    public ResponseEntity<String> deleteFAQ(@PathVariable Long id) {
        try {
            faqService.deleteFAQ(id);
            return ResponseEntity.ok("FAQ가 삭제되었습니다");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("FAQ를 찾을 수 없습니다" + e.getMessage());
        }
    }

    @GetMapping("/faq/parent")
    public ResponseEntity<List<FAQCategory>> getParentCategories() {
        List<FAQCategory> parentCategories = faqService.getParentCategories();
        if(parentCategories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(parentCategories);
        }
        return ResponseEntity.ok(parentCategories);
    }

    @GetMapping("/faq/sub/{parentCategoryId}")
    public ResponseEntity<List<FAQCategory>> getSubCategories(@PathVariable Long parentCategoryId) {
        List<FAQCategory> subCategories = faqService.getSubCategories(parentCategoryId);
        if(subCategories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(subCategories);
        }
        return ResponseEntity.ok(subCategories);
    }
}
