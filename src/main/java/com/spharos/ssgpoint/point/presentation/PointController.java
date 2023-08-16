package com.spharos.ssgpoint.point.presentation;

import com.spharos.ssgpoint.point.application.PointService;
import com.spharos.ssgpoint.point.dto.PointGetDto;
import com.spharos.ssgpoint.point.vo.PointGetVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {

    private final PointService pointService;

    @GetMapping("/{userId}")
    public List<PointGetVo> getPointByUser(@PathVariable Long userId) {
        List<PointGetDto> pointGetDtoList = pointService.getPointByUser(userId);
        List<PointGetVo> pointGetVoList = pointGetDtoList.stream().map(pointGetDto -> {
            return PointGetVo.builder()
                    .point(pointGetDto.getPoint())
                    .status(pointGetDto.getStatus())
                    .build();
        }).toList();

        return pointGetVoList;
    }

}
