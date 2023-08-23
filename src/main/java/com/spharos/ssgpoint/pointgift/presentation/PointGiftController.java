package com.spharos.ssgpoint.pointgift.presentation;

import com.spharos.ssgpoint.pointgift.application.PointGiftService;
import com.spharos.ssgpoint.pointgift.dto.PointGiftCreateDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftGetDto;
import com.spharos.ssgpoint.pointgift.vo.PointGiftCreateVo;
import com.spharos.ssgpoint.pointgift.vo.PointGiftGetVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PointGiftController {

    private final PointGiftService pointGiftService;

    // 포인트 선물 보내기
    @PostMapping("/point/gift")
    public void createPointGift(@RequestParam("UUID") String UUID, @RequestBody PointGiftCreateVo pointGiftCreateVo) {
        PointGiftCreateDto pointGiftCreateDto = PointGiftCreateDto.builder()
                .point(pointGiftCreateVo.getPoint())
                .message(pointGiftCreateVo.getMessage())
                .access(0)
                .UUID(UUID)
                .loginId(pointGiftCreateVo.getLoginId())
                .build();

        pointGiftService.createPointGift(UUID, pointGiftCreateDto);
    }

    // 포인트 선물 목록
    @GetMapping("/point/gift")
    public List<PointGiftGetVo> getPointGiftByUser(@RequestParam("UUID") String UUID) {
        List<PointGiftGetDto> pointGiftGetDtoList = pointGiftService.getPointByUser(UUID);

        return pointGiftGetDtoList.stream().map(pointGiftGetDto ->
                PointGiftGetVo.builder()
                        .point(pointGiftGetDto.getPoint())
                        .message(pointGiftGetDto.getMessage())
                        .access(String.valueOf(pointGiftGetDto.getAccess()))
                        .UUID(UUID)
                        .build()
        ).toList();
    }

}
