package com.spharos.ssgpoint.point.presentation;

import com.spharos.ssgpoint.point.application.PointService;
import com.spharos.ssgpoint.point.domain.Point;
import com.spharos.ssgpoint.point.dto.PointCreateDto;
import com.spharos.ssgpoint.point.dto.PointFilterDto;
import com.spharos.ssgpoint.point.dto.PointGetDto;
import com.spharos.ssgpoint.point.vo.PointCreateVo;
import com.spharos.ssgpoint.point.vo.PointFilterOutVo;
import com.spharos.ssgpoint.point.vo.PointFilterVo;
import com.spharos.ssgpoint.point.vo.PointGetVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

        if (pointCreateVo.getType().equals("1")) {
            PointCreateDto pointCreateDto = PointCreateDto.builder()
                    .point(pointCreateVo.getPoint())
                    .title(pointCreateVo.getTitle())
                    .content(pointCreateVo.getContent())
                    .statusType(pointCreateVo.getStatusType())
                    .type(pointCreateVo.getType())
                    .receipt(PointCreateDto.ReceiptDto.builder()
                            .alliance(pointCreateVo.getReceipt().getAlliance())
                            .brand(pointCreateVo.getReceipt().getBrand())
                            .storeName(pointCreateVo.getReceipt().getStoreName())
                            .number(pointCreateVo.getReceipt().getNumber())
                            .amount(pointCreateVo.getReceipt().getAmount())
                            .receiptPoint(pointCreateVo.getReceipt().getPoint()) // 변경된 필드명
                            .cardName(pointCreateVo.getReceipt().getCardName())
                            .cardNumber(pointCreateVo.getReceipt().getCardNumber())
                            .build())
                    .build();
            pointService.createPoint(UUID, pointCreateDto);
        } else {
            PointCreateDto pointCreateDto = PointCreateDto.builder()
                    .point(pointCreateVo.getPoint())
                    .title(pointCreateVo.getTitle())
                    .content(pointCreateVo.getContent())
                    .statusType(pointCreateVo.getStatusType())
                    .type(pointCreateVo.getType())
                    .build();
            pointService.createPoint(UUID, pointCreateDto);
        }

    }


    // 포인트필터 목록
    @GetMapping("/test")
    public ResponseEntity<Slice<PointFilterOutVo>> pointListFilter(@RequestParam("UUID") String UUID,
                                                                   @RequestParam(value = "lastId", required = false) Long lastId,
                                                                   @PageableDefault(size = 10, sort = "createdDate") Pageable pageRequest,
                                                                   @RequestBody PointFilterVo pointFilterVo) {
        ModelMapper modelMapper = new ModelMapper();
        Slice<PointFilterOutVo> pointFilterOutVos = modelMapper.map(pointService.pointFilter(lastId, UUID, pageRequest, pointFilterVo)
                , new TypeToken<Slice<PointFilterOutVo>>() {}.getType());

        // ResponseEntity로 감싸서 반환
        return ResponseEntity.ok(pointFilterOutVos);
    }


}
