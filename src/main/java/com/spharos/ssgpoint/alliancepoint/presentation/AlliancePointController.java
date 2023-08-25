package com.spharos.ssgpoint.alliancepoint.presentation;

import com.spharos.ssgpoint.alliancepoint.application.AlliancePointService;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointCreateDto;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointGetDto;
import com.spharos.ssgpoint.alliancepoint.dto.AlliancePointUpdateDto;
import com.spharos.ssgpoint.alliancepoint.vo.AlliancePointCreateVo;
import com.spharos.ssgpoint.alliancepoint.vo.AlliancePointGetVo;
import com.spharos.ssgpoint.alliancepoint.vo.AlliancePointUpdateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AlliancePointController {

    private final AlliancePointService alliancePointService;

    // 제휴사 포인트 생성 (테스트 위해 생성)
    @PostMapping("/alliance-point")
    public void createAlliancePoint(@RequestParam("UUID") String UUID, @RequestBody AlliancePointCreateVo alliancePointCreateVo) {
        AlliancePointCreateDto alliancePointCreateDto = AlliancePointCreateDto.builder()
                .point(alliancePointCreateVo.getPoint())
                .type(alliancePointCreateVo.getType())
                .UUID(UUID)
                .build();

        alliancePointService.createAlliancePoint(UUID, alliancePointCreateDto);
    }

    // 제휴사 포인트 조회
    @GetMapping("/alliance-point/all")
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

    // 제휴사 포인트 사용
    @PutMapping("/alliance-point")
    public void updateAlliancePoint(@RequestParam("UUID") String UUID, @RequestParam("type") String type, @RequestBody AlliancePointUpdateVo alliancePointUpdateVo) {
        AlliancePointUpdateDto alliancePointUpdateDto = AlliancePointUpdateDto.builder()
                .point(alliancePointUpdateVo.getPoint())
                .build();

        alliancePointService.updateAlliancePoint(UUID, type, alliancePointUpdateDto);
    }

}
