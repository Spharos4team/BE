package com.spharos.ssgpoint.point.presentation;

import com.spharos.ssgpoint.point.application.PointService;
import com.spharos.ssgpoint.point.dto.PointCreateDto;
import com.spharos.ssgpoint.point.dto.PointGetDto;
import com.spharos.ssgpoint.point.vo.PointCreateVo;
import com.spharos.ssgpoint.point.vo.PointFilterVo;
import com.spharos.ssgpoint.point.vo.PointGetVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    // 포인트 목록 - 전체 적립 사용 구분 없이
    @GetMapping("/point")
    public List<PointGetVo> getTotalPointByUser(@RequestParam("UUID") String UUID,@PageableDefault(size=10, sort="createdDate") Pageable pageRequest) {

        List<PointGetDto> pointByUser = pointService.getTotalPointByUser(UUID, pageRequest);

        return pointByUser.stream().map(pointGetDto ->
                PointGetVo.builder()
                        .point(pointGetDto.getPoint())
                        .title(pointGetDto.getTitle())
                        .content(pointGetDto.getContent())
                        .type(pointGetDto.getType())
                        .createdDate(pointGetDto.getCreatedDate())
                        .build()
        ).toList();
    }

    // 포인트 목록 - 적립 구분
    @GetMapping("/save-point")
    public List<PointGetVo> getSavePointByUser(@RequestParam("UUID") String UUID,@PageableDefault(size=10, sort="createdDate") Pageable pageRequest) {

        List<PointGetDto> pointByUser = pointService.getSavePointByUser(UUID, pageRequest);

        return pointByUser.stream().map(pointGetDto ->
                PointGetVo.builder()
                        .point(pointGetDto.getPoint())
                        .title(pointGetDto.getTitle())
                        .content(pointGetDto.getContent())
                        .type(pointGetDto.getType())
                        .createdDate(pointGetDto.getCreatedDate())
                        .build()
        ).toList();
    }

    /**
     * 포인트 내역 필터
     */
    @GetMapping("/test")
    public List<PointGetDto> pointListFilter(@RequestParam("UUID") String UUID,@PageableDefault(size=10, sort="createdDate") Pageable pageRequest
    , @RequestBody PointFilterVo pointFilterVo) {
        return pointService.test(UUID, pageRequest,pointFilterVo);
    }


}
