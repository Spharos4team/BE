package com.spharos.ssgpoint.offlinepointcard.presentation;

import com.spharos.ssgpoint.offlinepointcard.application.OfflinePointCardService;
import com.spharos.ssgpoint.offlinepointcard.dto.OfflinePointCardCreateDto;
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
    public void createOfflinePointCardTest(@RequestBody OfflinePointCardCreateVo offlinePointCardCreateVo) {
        ModelMapper modelMapper = new ModelMapper();
        OfflinePointCardCreateDto offlinePointCardCreateDto
                = modelMapper.map(offlinePointCardCreateVo, OfflinePointCardCreateDto.class);

        offlinePointCardService.createOfflinePointCardTest(offlinePointCardCreateDto);
    }

    // 오프라인 포인트 카드 등록
    @PostMapping("/offline-point-card")
    public void createOfflinePointCard(@RequestParam("UUID") String UUID, @RequestParam("number") String number,
                                       @RequestParam("CVC") Integer CVC, @RequestParam("alliance") String alliance,
                                       @RequestParam("store") String store) {
        offlinePointCardService.createOfflinePointCard(UUID, number, CVC, alliance, store);
    }

}
