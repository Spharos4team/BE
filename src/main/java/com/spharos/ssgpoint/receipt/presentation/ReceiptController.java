package com.spharos.ssgpoint.receipt.presentation;

import com.spharos.ssgpoint.receipt.application.ReceiptService;
import com.spharos.ssgpoint.receipt.dto.ReceiptCreateTestDto;
import com.spharos.ssgpoint.receipt.dto.ReceiptGetDto;
import com.spharos.ssgpoint.receipt.vo.ReceiptCreateTestVo;
import com.spharos.ssgpoint.receipt.vo.ReceiptGetVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReceiptController {

    private final ReceiptService receiptService;

    // 영수증 생성 (테스트 용)
    @PostMapping("/point/receipt/test")
    public void createReceipt(@RequestBody ReceiptCreateTestVo receiptCreateVo) {
        ModelMapper modelMapper = new ModelMapper();
        ReceiptCreateTestDto receiptCreateDto = modelMapper.map(receiptCreateVo, ReceiptCreateTestDto.class);
        receiptService.createReceipt(receiptCreateDto);
    }

    // 영수증으로 적립
    @PostMapping("/point/receipt")
    public void createPointByReceipt(@RequestParam("UUID") String UUID, @RequestBody ReceiptGetVo receiptGetVo) {
        ReceiptGetDto receiptGetDto = ReceiptGetDto.builder()
                .alliance(receiptGetVo.getAlliance())
                .brand(receiptGetVo.getBrand())
                .storeName(receiptGetVo.getStoreName())
                .number(receiptGetVo.getNumber())
                .build();

        receiptService.createPointByReceipt(UUID, receiptGetDto);
    }

}
