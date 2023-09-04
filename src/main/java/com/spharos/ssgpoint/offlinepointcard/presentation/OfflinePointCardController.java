package com.spharos.ssgpoint.offlinepointcard.presentation;

import com.spharos.ssgpoint.offlinepointcard.application.OfflinePointCardService;
import com.spharos.ssgpoint.offlinepointcard.dto.OfflinePointCardCreateDto;
import com.spharos.ssgpoint.offlinepointcard.dto.OfflinePointCardCreateTestDto;
import com.spharos.ssgpoint.offlinepointcard.vo.OfflinePointCardCreateTestVo;
import com.spharos.ssgpoint.offlinepointcard.vo.OfflinePointCardCreateVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OfflinePointCardController {

    private final OfflinePointCardService offlinePointCardService;

    // 오프라인 포인트 카드 생성 (테스트 용)
    @PostMapping("/offline-point-card/test")
    public void createOfflinePointCardTest(@RequestBody OfflinePointCardCreateTestVo offlinePointCardCreateVo) {
        ModelMapper modelMapper = new ModelMapper();
        OfflinePointCardCreateTestDto offlinePointCardCreateDto
                = modelMapper.map(offlinePointCardCreateVo, OfflinePointCardCreateTestDto.class);

        offlinePointCardService.createOfflinePointCardTest(offlinePointCardCreateDto);
    }

    // 오프라인 포인트 카드 등록
    @PostMapping("/offline-point-card")
    public void createOfflinePointCard(@RequestParam("UUID") String UUID,
                                       @RequestBody OfflinePointCardCreateVo offlinePointCardCreateVo) {
        OfflinePointCardCreateDto offlinePointCardCreateDto = OfflinePointCardCreateDto.builder()
                .number(offlinePointCardCreateVo.getNumber())
                .CVC(offlinePointCardCreateVo.getCVC())
                .alliance(offlinePointCardCreateVo.getAlliance())
                .store(offlinePointCardCreateVo.getStore())
                .build();

        offlinePointCardService.createOfflinePointCard(UUID, offlinePointCardCreateDto);
    }

}
