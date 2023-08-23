package com.spharos.ssgpoint.receipt.presentation;

import com.spharos.ssgpoint.receipt.application.ReceiptService;
import com.spharos.ssgpoint.receipt.dto.ReceiptGetDto;
import com.spharos.ssgpoint.receipt.vo.ReceiptGetVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReceiptController {

    private final ReceiptService receiptService;

    // 영수증
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

}
