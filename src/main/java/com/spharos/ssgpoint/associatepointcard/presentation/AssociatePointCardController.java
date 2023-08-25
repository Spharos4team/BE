package com.spharos.ssgpoint.associatepointcard.presentation;

import com.spharos.ssgpoint.associatepointcard.application.AssociatePointCardService;
import com.spharos.ssgpoint.associatepointcard.dto.AssociatePointCardCreateDto;
import com.spharos.ssgpoint.associatepointcard.dto.AssociatePointCardGetDto;
import com.spharos.ssgpoint.associatepointcard.vo.AssociatePointCardCreateVo;
import com.spharos.ssgpoint.associatepointcard.vo.AssociatePointCardGetVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AssociatePointCardController {

    private final AssociatePointCardService associatePointCardService;

    // 제휴 포인트 카드 등록
    @PostMapping("/point-card/associate")
    public void createAssociatePointCard(@RequestParam("UUID") String UUID
            , @RequestBody AssociatePointCardCreateVo associatePointCardCreateVo) {
        AssociatePointCardCreateDto associatePointCardCreateDto = AssociatePointCardCreateDto.builder()
                .number(associatePointCardCreateVo.getNumber())
                .UUID(UUID)
                .type(associatePointCardCreateVo.getType())
                .build();

        associatePointCardService.createAssociatePointCard(UUID, associatePointCardCreateDto);
    }

    // 제휴 포인트 카드 조회
    @GetMapping("/point-card/associate")
    public List<AssociatePointCardGetVo> getAssociatePointCardByNumber(@RequestParam("UUID") String UUID) {
        List<AssociatePointCardGetDto> associatePointCardGetDtoList
                = associatePointCardService.getAssociatePointCardByUser(UUID);

        return associatePointCardGetDtoList.stream().map(associatePointCardGetDto ->
                AssociatePointCardGetVo.builder()
                        .number(associatePointCardGetDto.getNumber())
                        .type(associatePointCardGetDto.getType())
                        .build()
        ).toList();
    }

}
