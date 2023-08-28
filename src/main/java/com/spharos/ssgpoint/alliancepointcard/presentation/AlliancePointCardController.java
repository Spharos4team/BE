package com.spharos.ssgpoint.alliancepointcard.presentation;

import com.spharos.ssgpoint.alliancepointcard.application.AlliancePointCardService;
import com.spharos.ssgpoint.alliancepointcard.dto.AlliancePointCardCreateDto;
import com.spharos.ssgpoint.alliancepointcard.dto.AlliancePointCardGetDto;
import com.spharos.ssgpoint.alliancepointcard.vo.AlliancePointCardCreateVo;
import com.spharos.ssgpoint.alliancepointcard.vo.AlliancePointCardGetVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AlliancePointCardController {

    private final AlliancePointCardService alliancePointCardService;

    // 제휴 포인트 카드 등록
    @PostMapping("/point-card/alliance")
    public void createAlliancePointCard(@RequestParam("UUID") String UUID
            , @RequestBody AlliancePointCardCreateVo alliancePointCardCreateVo) {
        AlliancePointCardCreateDto alliancePointCardCreateDto = AlliancePointCardCreateDto.builder()
                .number(alliancePointCardCreateVo.getNumber())
                .UUID(UUID)
                .type(alliancePointCardCreateVo.getType())
                .build();

        alliancePointCardService.createAlliancePointCard(UUID, alliancePointCardCreateDto);
    }

    // 제휴 포인트 카드 조회
    @GetMapping("/point-card/alliance")
    public List<AlliancePointCardGetVo> getAlliancePointCardByNumber(@RequestParam("UUID") String UUID) {
        List<AlliancePointCardGetDto> alliancePointCardGetDtoList
                = alliancePointCardService.getAlliancePointCardByUser(UUID);

        return alliancePointCardGetDtoList.stream().map(alliancePointCardGetDto ->
                AlliancePointCardGetVo.builder()
                        .number(alliancePointCardGetDto.getNumber())
                        .type(alliancePointCardGetDto.getType())
                        .build()
        ).toList();
    }

}
