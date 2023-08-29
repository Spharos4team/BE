package com.spharos.ssgpoint.term.presentation;

import com.spharos.ssgpoint.term.application.TermService;
import com.spharos.ssgpoint.term.domain.ServiceTerm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequiredArgsConstructor
public class TermController {
    private final TermService termService;

    /**
     * 서비스 내용보기
     */
    @GetMapping("/term/{id}")
    public ResponseEntity<ServiceTerm> getContent(@PathVariable Long id) {
        ServiceTerm content = termService.getContent(id);
        return ResponseEntity.ok(content);
    }
}
