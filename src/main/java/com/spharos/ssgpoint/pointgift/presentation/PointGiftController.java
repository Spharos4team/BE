package com.spharos.ssgpoint.pointgift.presentation;

import com.spharos.ssgpoint.pointgift.application.PointGiftService;
import com.spharos.ssgpoint.pointgift.dto.PointGiftCreateDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftGetDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftUpdateDto;
import com.spharos.ssgpoint.pointgift.vo.PointGiftCreateVo;
import com.spharos.ssgpoint.pointgift.vo.PointGiftGetVo;
import com.spharos.ssgpoint.pointgift.vo.PointGiftUpdateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PointGiftController {

    private final PointGiftService pointGiftService;

    // 포인트 선물 수신인 확인
    @GetMapping("/point/gift/user")
    public String getPointGiftUser(@RequestParam("phone") String phone, @RequestParam("name") String name) {

        return pointGiftService.getPointGiftUser(phone, name);
    }

    // 포인트 선물 보내기
    @PostMapping("/point/gift")
    public void createPointGift(@RequestParam("UUID") String UUID, @RequestBody PointGiftCreateVo pointGiftCreateVo) {
        PointGiftCreateDto pointGiftCreateDto = PointGiftCreateDto.builder()
                .point(pointGiftCreateVo.getPoint())
                .message(pointGiftCreateVo.getMessage())
                .type("선물사용")
                .access("대기")
                .UUID(UUID)
                .loginId(pointGiftCreateVo.getLoginId())
                .name(pointGiftCreateVo.getName())
                .build();

        pointGiftService.createPointGift(UUID, pointGiftCreateDto);
    }

    // 포인트 선물 수락
    @PutMapping("/point/gift/accept")
    public void updatePointGiftAccept(@RequestParam("id") Long id) {
        PointGiftUpdateDto pointGiftUpdateDto = PointGiftUpdateDto.builder()
                .access("수락")
                .build();

        pointGiftService.updatePointGiftAccept(id, pointGiftUpdateDto);
    }
    
    // 포인트 선물 거절
    @PutMapping("/point/gift/refuse")
    public void updatePointGiftRefuse(@RequestParam("id") Long id) {
        PointGiftUpdateDto pointGiftUpdateDto = PointGiftUpdateDto.builder()
                .access("거절")
                .build();

        pointGiftService.updatePointGiftRefuse(id, pointGiftUpdateDto);
    }

    // 포인트 선물 목록
    @GetMapping("/point/gift")
    public List<PointGiftGetVo> getPointGiftByUser(@RequestParam("UUID") String UUID) {
        List<PointGiftGetDto> pointGiftGetDtoList = pointGiftService.getPointGiftByUser(UUID);

        return pointGiftGetDtoList.stream().map(pointGiftGetDto -> PointGiftGetVo.builder()
                .point(pointGiftGetDto.getPoint())
                .message(pointGiftGetDto.getMessage())
                .type(pointGiftGetDto.getType())
                .access(pointGiftGetDto.getAccess())
                .UUID(UUID)
                .loginId(pointGiftGetDto.getLoginId())
                .name(pointGiftGetDto.getName())
                .createdDate(pointGiftGetDto.getCreatedDate())
                .build()
        ).toList();
    }

}
