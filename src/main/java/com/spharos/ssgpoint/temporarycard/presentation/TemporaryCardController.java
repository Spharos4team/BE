package com.spharos.ssgpoint.temporarycard.presentation;

import com.spharos.ssgpoint.temporarycard.application.TemporaryCardService;
import com.spharos.ssgpoint.temporarycard.dto.TemporaryCardGetDto;
import com.spharos.ssgpoint.temporarycard.vo.TemporaryCardGetVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TemporaryCardController {

    private final TemporaryCardService temporaryCardService;

    // 임시 발급 카드를 포인트 카드에 등록
    @PostMapping("/temporary-card")
    public ResponseEntity<String> createTemporaryCardByNumber(@RequestParam("UUID") String UUID,
                                                              @RequestBody TemporaryCardGetVo temporaryCardGetVo) {
        TemporaryCardGetDto temporaryCardGetDto = TemporaryCardGetDto.builder()
                .birth(temporaryCardGetVo.getBirth())
                .name(temporaryCardGetVo.getName())
                .number(temporaryCardGetVo.getNumber())
                .CVC(temporaryCardGetVo.getCVC())
                .agency(temporaryCardGetVo.getAgency())
                .build();

        temporaryCardService.createTemporaryCardByNumber(UUID, temporaryCardGetDto);

        return ResponseEntity.ok("임시 발급 카드 등록 완료");
    }


}
