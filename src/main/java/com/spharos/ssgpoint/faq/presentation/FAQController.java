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
        return ResponseEntity.ok(faqService.getAllFAQs());
    }

    @GetMapping("/faq/{categoryId}")
    public ResponseEntity<List<FAQDTO>> getFAQsByCategory(@PathVariable Long categoryId, @RequestParam(required = false) Long subCategoryId) {
        return ResponseEntity.ok(faqService.getFAQsByCategory(categoryId, subCategoryId));
    }

    @DeleteMapping("/faq/{id}")
    public ResponseEntity<String> deleteFAQ(@PathVariable Long id) {
        try {
            faqService.deleteFAQ(id);
            return ResponseEntity.ok("FAQ가 삭제되었습니다");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/faq/categories/main")
    public ResponseEntity<List<FAQCategory>> getMainCategories() {
        return ResponseEntity.ok(faqService.getMainCategories());
    }

    @GetMapping("/faq/categories/sub/{mainCategoryId}")
    public ResponseEntity<List<FAQCategory>> getSubCategories(@PathVariable Long mainCategoryId) {
        return ResponseEntity.ok(faqService.getSubCategories(mainCategoryId));
    }

}
