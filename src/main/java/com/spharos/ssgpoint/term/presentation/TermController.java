package com.spharos.ssgpoint.term.presentation;

import com.spharos.ssgpoint.term.application.TermService;
import com.spharos.ssgpoint.term.domain.ServiceTerm;
import com.spharos.ssgpoint.term.domain.UserServiceTerm;
import com.spharos.ssgpoint.term.dto.ServiceTermListDto;
import com.spharos.ssgpoint.term.dto.ServiceTermRequestDto;
import com.spharos.ssgpoint.term.dto.ServiceTermResponseDto;
import com.spharos.ssgpoint.term.dto.TermContentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<TermContentDto> getContent(@PathVariable Long id) {
        TermContentDto content = termService.getContent(id);
        return ResponseEntity.ok(content);
    }

    /**
     * 서비스 동의안에 작은버튼 누를때
     */
    @PostMapping("/term/{UUID}")
    public ResponseEntity<ServiceTermResponseDto> checkTerm(@PathVariable String UUID, @RequestBody ServiceTermRequestDto serviceTerm ) {
        ServiceTermResponseDto serviceTermResponseDto = termService.checkTerm(UUID, serviceTerm);
        return ResponseEntity.ok(serviceTermResponseDto);
    }

    /**
     * 서비스 동의 내역 페이지 클릭시 3개 서비스 동의 보여주기
     * {
     *     "신세계포인트":0,
     *     "이마트":1,
     *     "백화점":0
     * }
     */
    @GetMapping("/termList")
    public ResponseEntity<List<ServiceTermListDto>> getTermList(@RequestParam String UUID) {
        List<ServiceTermListDto> termList = termService.getTermList(UUID);
        return ResponseEntity.ok(termList);
    }



}
