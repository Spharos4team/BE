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

    @PostMapping("/point-card")
    void createPointCard(@RequestParam("UUID") String UUID, @RequestBody PointCardCreateVo pointCardCreateVo) {
        PointCardCreateDto pointCardCreateDto = PointCardCreateDto.builder()
                .number(pointCardCreateVo.getNumber())
                .agency(pointCardCreateVo.getAgency())
                .UUID(UUID)
                .pointCardType(pointCardCreateVo.getPointCardType())
                .build();
        pointCardService.createPointCard(UUID, pointCardCreateDto);
    }

    @GetMapping("/point-card")
    public List<PointCardGetVo> getPointCardByUser(@RequestParam("UUID") String UUID) {
        List<PointCardGetDto> pointCardGetDtoList = pointCardService.getPointCardByUser(UUID);
        List<PointCardGetVo> pointCardGetVoList = pointCardGetDtoList.stream().map(pointCardGetDto -> {
            return PointCardGetVo.builder()
                    .name(pointCardGetDto.getName())
                    .number(pointCardGetDto.getNumber())
                    .agency(pointCardGetDto.getAgency())
                    .build();
        }).toList();

        return pointCardGetVoList;
    }

}
