package com.spharos.ssgpoint.offlinepointcard.presentation;

import com.spharos.ssgpoint.offlinepointcard.application.OfflinePointCardService;
import com.spharos.ssgpoint.offlinepointcard.dto.OfflinePointCardCreateDto;
import com.spharos.ssgpoint.offlinepointcard.dto.OfflinePointCardGetDto;
import com.spharos.ssgpoint.offlinepointcard.vo.OfflinePointCardCreateVo;
import com.spharos.ssgpoint.offlinepointcard.vo.OfflinePointCardGetVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 오프라인 포인트 카드 가져오기
    @GetMapping("/offline-point-card")
    public List<OfflinePointCardGetVo> getOfflinePointCardByNumber(@RequestParam("number") String number) {
        List<OfflinePointCardGetDto> offlinePointCardGetDtoList = offlinePointCardService.getOfflinePointCard(number);

        return offlinePointCardGetDtoList.stream().map(offlinePointCardGetDto ->
                OfflinePointCardGetVo.builder()
                        .number(offlinePointCardGetDto.getNumber())
                        .CVC(offlinePointCardGetDto.getCVC())
                        .alliance(offlinePointCardGetDto.getAlliance())
                        .store(offlinePointCardGetDto.getStore())
                        .build()
        ).toList();
    }

}
