package com.spharos.ssgpoint.pointgift.infrastructure;

import com.spharos.ssgpoint.pointgift.application.PointGiftService;
import com.spharos.ssgpoint.pointgift.dto.PointGiftCreateDto;
import com.spharos.ssgpoint.pointgift.vo.PointGiftCreateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PointGiftController {

    private final PointGiftService pointGiftService;

    @PostMapping("/point/gift")
    void createPointGift(@RequestParam("UUID") String UUID, @RequestBody PointGiftCreateVo pointGiftCreateVo) {
        PointGiftCreateDto pointGiftCreateDto = PointGiftCreateDto.builder()
                .message(pointGiftCreateVo.getMessage())
                .access(Integer.valueOf(pointGiftCreateVo.getAccess()))
                .UUID(UUID)
                .loginId(pointGiftCreateVo.getLoginId())
                .build();
        pointGiftService.createPointGift(UUID, pointGiftCreateDto);
    }

}
