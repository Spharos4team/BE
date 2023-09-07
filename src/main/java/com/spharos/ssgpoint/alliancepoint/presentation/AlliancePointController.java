package com.spharos.ssgpoint.alliancepoint.presentation;

import com.spharos.ssgpoint.alliancepoint.application.AlliancePointService;
import com.spharos.ssgpoint.alliancepoint.domain.AlliancePoint;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointCreateDto;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointGetDto;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointUpdateDto;
import com.spharos.ssgpoint.alliancepoint.vo.AlliancePointCreateVo;
import com.spharos.ssgpoint.alliancepoint.vo.AlliancePointGetVo;
import com.spharos.ssgpoint.alliancepoint.vo.AlliancePointUpdateVo;
import com.spharos.ssgpoint.user.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AlliancePointController {

    private final AlliancePointService alliancePointService;

    // 제휴사 포인트 생성 (테스트 위해 생성)
    @PostMapping("/alliance-point")
    public ResponseEntity<String> createAlliancePoint(@RequestParam("UUID") String UUID, @RequestBody AlliancePointCreateVo alliancePointCreateVo) {

        AlliancePointCreateDto alliancePointCreateDto = AlliancePointCreateDto.builder()
                .type(alliancePointCreateVo.getType())
                .point(alliancePointCreateVo.getPoint())
                .build();
        alliancePointService.createAlliancePoint(UUID, alliancePointCreateDto);
        return ResponseEntity.ok("포인트생성");
    }

    // 제휴사 포인트 조회
    @GetMapping("/alliance-point")
    public List<AlliancePointGetVo> getAlliancePointByUUID(@RequestParam("UUID") String UUID) {
        List<AlliancePointGetDto> alliancePointGetDtoList
                = alliancePointService.getAlliancePointByUUID(UUID);

        return alliancePointGetDtoList.stream().map(alliancePointAllGetDto ->
                AlliancePointGetVo.builder()
                        .point(alliancePointAllGetDto.getPoint())
                        .type(alliancePointAllGetDto.getType())
                        .build()
        ).toList();
    }

    // 제휴사 포인트 전환
    @PutMapping("/alliance-point")
    public void updateAlliancePoint(@RequestParam("UUID") String UUID, @RequestParam("type") String type,
                                    @RequestParam("status") String status,
                                    @RequestBody AlliancePointUpdateVo alliancePointUpdateVo) {
        AlliancePointUpdateDto alliancePointUpdateDto = AlliancePointUpdateDto.builder()
                .point(alliancePointUpdateVo.getPoint())
                .build();

        alliancePointService.updateAlliancePoint(UUID, type, status, alliancePointUpdateDto);
    }

}
