package com.spharos.ssgpoint.term.presentation;

import com.spharos.ssgpoint.term.application.TermService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class TermController {

    private final TermService termService;

    @PostMapping("/term")
    public void saveAgreements(String UUID,@RequestBody Map<String, Boolean> agreements) {

    }
}
