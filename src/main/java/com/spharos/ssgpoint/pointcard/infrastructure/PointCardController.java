package com.spharos.ssgpoint.pointcard.infrastructure;

import com.spharos.ssgpoint.point.dto.PointGetDto;
import com.spharos.ssgpoint.point.vo.PointGetVo;
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
@RequestMapping("/pointCard")
public class PointCardController {

    private final PointCardService pointCardService;

    @PostMapping("/create")
    void createPointCard(@RequestHeader("UUID") String UUID, @RequestBody PointCardCreateVo pointCardCreateVo) {
        PointCardCreateDto pointCardCreateDto = PointCardCreateDto.builder()
                .number(pointCardCreateVo.getNumber())
                .agency(pointCardCreateVo.getAgency())
                .UUID(UUID)
                .pointCardType(pointCardCreateVo.getPointCardType())
                .build();
        pointCardService.createPointCard(UUID, pointCardCreateDto);
    }

    @GetMapping("")
    public List<PointCardGetVo> getPointCardByUser(@RequestHeader("UUID") String UUID) {
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
