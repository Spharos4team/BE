package com.spharos.ssgpoint.pointcard.infrastructure;

import com.spharos.ssgpoint.pointcard.application.PointCardService;
import com.spharos.ssgpoint.pointcard.dto.PointCardCreateDto;
import com.spharos.ssgpoint.pointcard.dto.PointCardGetDto;
import com.spharos.ssgpoint.pointcard.vo.PointCardCreateVo;
import com.spharos.ssgpoint.pointcard.vo.PointCardGetVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PointCardController {

    private final PointCardService pointCardService;

    // 포인트 카드 생성
    @PostMapping("/point-card")
    public void createPointCard(@RequestParam("UUID") String UUID, @RequestBody PointCardCreateVo pointCardCreateVo) {
        PointCardCreateDto pointCardCreateDto = PointCardCreateDto.builder()
                .number(pointCardCreateVo.getNumber())
                .agency(pointCardCreateVo.getAgency())
                .UUID(UUID)
                .pointCardType(pointCardCreateVo.getPointCardType())
                .build();

        pointCardService.createPointCard(UUID, pointCardCreateDto);
    }

    // 포인트 카드 목록
    @GetMapping("/point-card")
    public List<PointCardGetVo> getPointCardByUser(@RequestParam("UUID") String UUID) {
        List<PointCardGetDto> pointCardGetDtoList = pointCardService.getPointCardByUser(UUID);

        return pointCardGetDtoList.stream().map(pointCardGetDto ->
                PointCardGetVo.builder()
                        .name(pointCardGetDto.getName())
                        .number(pointCardGetDto.getNumber())
                        .agency(pointCardGetDto.getAgency())
                        .build()
        ).toList();
    }

}
