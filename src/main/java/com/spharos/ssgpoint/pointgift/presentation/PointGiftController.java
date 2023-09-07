package com.spharos.ssgpoint.pointgift.presentation;

import com.spharos.ssgpoint.point.vo.PointFilterOutVo;
import com.spharos.ssgpoint.pointgift.application.PointGiftService;
import com.spharos.ssgpoint.pointgift.dto.PointGiftCreateDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftListDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftUserGetDto;
import com.spharos.ssgpoint.pointgift.vo.PointGiftCreateVo;
import com.spharos.ssgpoint.pointgift.vo.PointGiftListInVo;
import com.spharos.ssgpoint.pointgift.vo.PointGiftListOutVo;
import com.spharos.ssgpoint.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Slf4j
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
    public ResponseEntity<String> createPointGift(@RequestParam("UUID") String UUID, @RequestBody PointGiftCreateVo pointGiftCreateVo) {
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
        return ResponseEntity.ok("포인트 선물 완료");
    }

    // 포인트 선물 수락/거절
    @PutMapping("/point/gift")
    public ResponseEntity<String> updatePoint(@RequestParam("id") Long id, @RequestParam("status") String status) {
        pointGiftService.updatePoint(id, status);

        return ResponseEntity.ok("포인트 선물 상태 변경 완료");
    }


    //포인트 선물 목록
    @GetMapping("/point/gift")
    public ResponseEntity<Slice<PointGiftListOutVo>> pointListFilter(@RequestParam("UUID") String UUID,
                                                                     @RequestParam(value = "lastId", required = false) Long lastId,
                                                                     @PageableDefault(size=10, sort="createdDate") Pageable pageRequest
            , @RequestBody PointGiftListInVo pointGiftListVo){

        ModelMapper modelMapper = new ModelMapper();
        Slice<PointGiftListOutVo> pointFilterOutVos = modelMapper.map(pointGiftService.getPointGiftList(lastId, UUID, pageRequest, pointGiftListVo)
                , new TypeToken<Slice<PointGiftListOutVo>>() {}.getType());


        return ResponseEntity.ok(pointFilterOutVos);




    }



}
