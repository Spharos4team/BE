package com.spharos.ssgpoint.point.presentation;

import com.spharos.ssgpoint.point.application.PointService;
import com.spharos.ssgpoint.point.domain.Point;
import com.spharos.ssgpoint.point.dto.PointCreateDto;
import com.spharos.ssgpoint.point.dto.PointFilterDto;
import com.spharos.ssgpoint.point.dto.PointFilterSumDto;
import com.spharos.ssgpoint.point.dto.PointGetDto;
import com.spharos.ssgpoint.point.vo.*;
import com.spharos.ssgpoint.pointgift.dto.PointGiftMessageDto;
import com.spharos.ssgpoint.pointgift.vo.PointGiftMessageVo;
import com.spharos.ssgpoint.receipt.dto.ReceiptGetDto;
import com.spharos.ssgpoint.receipt.vo.ReceiptGetVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PointController {

    private final PointService pointService;

    // 포인트 생성
    @PostMapping("/point")
    public void createPoint(@RequestParam("UUID") String UUID, @RequestBody PointCreateVo pointCreateVo) {

        if (pointCreateVo.getType().equals("1")) { //결제 적립
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
                            .pointCardNumber(pointCreateVo.getReceipt().getPointCardNumber())
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
    @GetMapping("/point-list")
    public ResponseEntity<Slice<PointFilterOutVo>> pointListFilter(@RequestParam("UUID") String UUID,
                                                                   @RequestParam(value = "lastId", required = false) Long lastId,
                                                                    @PageableDefault(size = 10) Pageable pageRequest,
                                                       @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                       @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                       @RequestParam String pointUse, @RequestParam String pointType) {
        ModelMapper modelMapper = new ModelMapper();
        Slice<PointFilterOutVo> pointFilterOutVos = modelMapper.map(pointService.pointFilter(lastId, UUID, pageRequest,
        startDate,endDate, pointUse, pointType)
                , new TypeToken<Slice<PointFilterOutVo>>() {}.getType());

        // ResponseEntity로 감싸서 반환
        return ResponseEntity.ok(pointFilterOutVos);
    }

/*
    @GetMapping("/point-list")
    public ResponseEntity<Page<PointFilterDto>> pageRe(@RequestParam("UUID") String UUID,
                                                       @PageableDefault(size = 10) Pageable pageRequest,
                                                       @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                       @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                       @RequestParam String pointUse, @RequestParam String pointType) {
        Page<PointFilterDto> pointFilterDto = pointService.pointFilter(UUID, pageRequest, startDate,endDate, pointUse, pointType);

        // ResponseEntity로 감싸서 반환
        return ResponseEntity.ok(pointFilterDto);
    }
*/


    // 포인트 목록 적립 사용 포인트 합계
    @GetMapping("/point-list-sum")
    public ResponseEntity<PointFilterSumVo> pointListFilterSum(@RequestParam("UUID") String UUID,
                                                               @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                                               @RequestParam  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                                               @RequestParam String pointUse, @RequestParam String pointType) {

        PointFilterSumDto pointFilterSumDto = pointService.sumPointsByFilter(UUID, startDate, endDate, pointUse, pointType);
        ModelMapper modelMapper = new ModelMapper();
        return ResponseEntity.ok(modelMapper.map(pointFilterSumDto, PointFilterSumVo.class));

    }
    // 포인트 내역 영수증 보기
    @GetMapping("/point-list/receipt")
    public ResponseEntity<ReceiptGetVo> pointListReceipt(@RequestParam("id") Long id) {
        ReceiptGetDto receiptGetDto = pointService.getReceiptByPointListReceiptId(id);
        ModelMapper modelMapper = new ModelMapper();
        return ResponseEntity.ok(modelMapper.map(receiptGetDto, ReceiptGetVo.class));
    }

    // 포인트 내역 메세지 보기
    @GetMapping("/point-list/message")
    public ResponseEntity<PointGiftMessageVo> pointListMessage(@RequestParam("id") Long id) {
        PointGiftMessageDto pointGiftMessageDto = pointService.getGiftMessage(id);
        ModelMapper modelMapper = new ModelMapper();
        return ResponseEntity.ok(modelMapper.map(pointGiftMessageDto, PointGiftMessageVo.class));
    }


}
