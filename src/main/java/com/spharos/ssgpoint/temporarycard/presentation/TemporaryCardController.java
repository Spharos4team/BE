package com.spharos.ssgpoint.temporarycard.presentation;

import com.spharos.ssgpoint.temporarycard.application.TemporaryCardService;
import com.spharos.ssgpoint.temporarycard.domain.TemporaryCard;
import com.spharos.ssgpoint.temporarycard.dto.TemporaryCardGetDto;
import com.spharos.ssgpoint.temporarycard.infrastructure.TemporaryCardRepository;
import com.spharos.ssgpoint.temporarycard.vo.TemporaryCardGetVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TemporaryCardController {

    private final TemporaryCardService temporaryCardService;

    // 임시 발급 카드 조회
    @GetMapping("/temporary-card")
    public List<TemporaryCardGetVo> getTemporaryCardByNumber(String number) {
        List<TemporaryCardGetDto> temporaryCardGetDtoList = temporaryCardService.getTemporaryCardByNumber(number);

        return temporaryCardGetDtoList.stream().map(temporaryCard ->
                TemporaryCardGetVo.builder()
                        .birth(temporaryCard.getBirth())
                        .name(temporaryCard.getName())
                        .number(temporaryCard.getNumber())
                        .CVC(temporaryCard.getCVC())
                        .agency(temporaryCard.getAgency())
                        .build()
        ).toList();
    }


}
