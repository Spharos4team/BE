package com.spharos.ssgpoint.pointgift.presentation;

import com.spharos.ssgpoint.pointgift.application.PointGiftService;
import com.spharos.ssgpoint.pointgift.dto.PointGiftCreateDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftGetDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftUserGetDto;
import com.spharos.ssgpoint.pointgift.vo.PointGiftCreateVo;
import com.spharos.ssgpoint.pointgift.vo.PointGiftGetVo;
import com.spharos.ssgpoint.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PointGiftController {

    private final PointGiftService pointGiftService;

    // 포인트 선물 수신인 확인
    @GetMapping("/point/gift/user")
    public ResponseEntity<PointGiftUserGetDto> getPointGiftUser(@RequestParam("phone") String phone, @RequestParam("name") String name) {
        User user = pointGiftService.getPointGiftUser(phone, name);

        PointGiftUserGetDto pointGiftUserGetDto = PointGiftUserGetDto.builder()
                .loginId(user.getLoginId())
                .name(user.getName())
                .phone(user.getPhone())
                .build();

        return ResponseEntity.ok(pointGiftUserGetDto);
    }

    // 포인트 선물 보내기
    @PostMapping("/point/gift")
    public void createPointGift(@RequestParam("UUID") String UUID, @RequestBody PointGiftCreateVo pointGiftCreateVo) {
        PointGiftCreateDto pointGiftCreateDto = PointGiftCreateDto.builder()
                .point(pointGiftCreateVo.getPoint())
                .message(pointGiftCreateVo.getMessage())
                .type("선물사용")
                .status("대기")
                .UUID(UUID)
                .loginId(pointGiftCreateVo.getLoginId())
                .name(pointGiftCreateVo.getName())
                .build();

        pointGiftService.createPointGift(UUID, pointGiftCreateDto);
    }

    // 포인트 선물 수락/거절
    @PutMapping("/point/gift")
    public ResponseEntity<String> updatePoint(@RequestParam("id") Long id, @RequestParam("status") String status) {
        pointGiftService.updatePoint(id, status);

        return ResponseEntity.ok("포인트 선물 상태 변경 완료");
    }

    // TODO: 안 쓰면 삭제
    // 포인트 선물 목록
    @GetMapping("/point/gift")
    public List<PointGiftGetVo> getPointGiftByUser(@RequestParam("UUID") String UUID) {
        List<PointGiftGetDto> pointGiftGetDtoList = pointGiftService.getPointGiftByUser(UUID);

        return pointGiftGetDtoList.stream().map(pointGiftGetDto -> PointGiftGetVo.builder()
                .point(pointGiftGetDto.getPoint())
                .message(pointGiftGetDto.getMessage())
                .type(pointGiftGetDto.getType())
                .status(pointGiftGetDto.getStatus())
                .UUID(UUID)
                .loginId(pointGiftGetDto.getLoginId())
                .name(pointGiftGetDto.getName())
                .createdDate(pointGiftGetDto.getCreatedDate())
                .build()
        ).toList();
    }
}
