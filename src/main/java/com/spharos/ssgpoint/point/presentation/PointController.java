package com.spharos.ssgpoint.point.presentation;

import com.spharos.ssgpoint.point.application.PointService;
import com.spharos.ssgpoint.point.dto.PointCreateDto;
import com.spharos.ssgpoint.point.dto.PointGetDto;
import com.spharos.ssgpoint.point.vo.PointCreateVo;
import com.spharos.ssgpoint.point.vo.PointGetVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PointController {

    private final PointService pointService;

    // 포인트 생성
    @PostMapping("/point")
    public void createPoint(@RequestParam("UUID") String UUID, @RequestBody PointCreateVo pointCreateVo) {
        PointCreateDto pointCreateDto = PointCreateDto.builder()
                .totalPoint(pointCreateVo.getTotalPoint())
                .point(pointCreateVo.getPoint())
                .pointTitle(pointCreateVo.getPointTitle())
                .pointContent(pointCreateVo.getPointContent())
                .type(pointCreateVo.getType())
                .user(UUID)
                .build();

        pointService.createPoint(UUID, pointCreateDto);
    }

    // 포인트 목록
    @GetMapping("/point")
    public List<PointGetVo> getPointByUser(@RequestParam("UUID") String UUID) {
        List<PointGetDto> pointGetDtoList = pointService.getPointByUser(UUID);

        return pointGetDtoList.stream().map(pointGetDto ->
                PointGetVo.builder()
                        .totalPoint(pointGetDto.getTotalPoint())
                        .point(pointGetDto.getPoint())
                        .pointTitle(pointGetDto.getPointTitle())
                        .pointContent(pointGetDto.getPointContent())
                        .type(pointGetDto.getType())
                        .build()
        ).toList();
    }

}
