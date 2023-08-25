package com.spharos.ssgpoint.receipt.presentation;

import com.spharos.ssgpoint.receipt.application.ReceiptService;
import com.spharos.ssgpoint.receipt.dto.ReceiptCreateDto;
import com.spharos.ssgpoint.receipt.dto.ReceiptGetDto;
import com.spharos.ssgpoint.receipt.dto.ReceiptUpdateDto;
import com.spharos.ssgpoint.receipt.vo.ReceiptCreateVo;
import com.spharos.ssgpoint.receipt.vo.ReceiptGetVo;
import com.spharos.ssgpoint.receipt.vo.ReceiptUpdateVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReceiptController {

    private final ReceiptService receiptService;

    // 영수증 생성 (테스트 용)
    @PostMapping("/point/receipt")
    public void createReceipt(@RequestBody ReceiptCreateVo receiptCreateVo) {
        ModelMapper modelMapper = new ModelMapper();
        ReceiptCreateDto receiptCreateDto = modelMapper.map(receiptCreateVo, ReceiptCreateDto.class);
        receiptService.createReceipt(receiptCreateDto);
    }

    // 영수증 일련번호로 영수증 정보 조회
    @GetMapping("/point/receipt")
    public List<ReceiptGetVo> getReceiptByNumber(@RequestParam("number") String number) {
        List<ReceiptGetDto> receiptGetDtoList = receiptService.getReceiptByNumber(number);

        return receiptGetDtoList.stream().map(receiptGetDto ->
                ReceiptGetVo.builder()
                        .storeName(receiptGetDto.getStoreName())
                        .point(receiptGetDto.getPoint())
                        .build()
        ).toList();
    }

    // 영수증 포인트 적립 후 영수증 테이블 상태 컬럼 변경
    @PutMapping("/point/receipt")
    public void updateReceipt(@RequestParam("number") String number) {

        ReceiptUpdateDto receiptUpdateDto = ReceiptUpdateDto.builder()
                .status(1)
                .build();

        receiptService.updateReceipt(number, receiptUpdateDto);
    }

}
