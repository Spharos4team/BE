package com.spharos.ssgpoint.pointcard.infrastructure;

import com.spharos.ssgpoint.pointcard.application.PointCardService;
import com.spharos.ssgpoint.pointcard.dto.PointCardCreateDto;
import com.spharos.ssgpoint.pointcard.vo.PointCardCreateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pointCard")
public class PointCardController {

    private final PointCardService pointCardService;

    @PostMapping("/create")
    void createPointCard(@RequestParam(value = "UUID", required = false) String UUID, @RequestBody PointCardCreateVo pointCardCreateVo) {
        PointCardCreateDto pointCardCreateDto = PointCardCreateDto.builder()
                .number(pointCardCreateVo.getNumber())
                .agency(pointCardCreateVo.getAgency())
                .UUID(pointCardCreateVo.getUUID())
                .pointCardType(pointCardCreateVo.getPointCardType())
                .build();
        pointCardService.createPointCard(UUID, pointCardCreateDto);
    }

}
