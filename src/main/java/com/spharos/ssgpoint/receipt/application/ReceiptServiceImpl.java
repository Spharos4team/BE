package com.spharos.ssgpoint.receipt.application;

import com.spharos.ssgpoint.point.application.PointService;
import com.spharos.ssgpoint.point.dto.PointCreateDto;
import com.spharos.ssgpoint.receipt.domain.Receipt;
import com.spharos.ssgpoint.receipt.dto.ReceiptCreateTestDto;
import com.spharos.ssgpoint.receipt.dto.ReceiptGetDto;
import com.spharos.ssgpoint.receipt.infrastructure.ReceiptRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final PointService pointService;

    private final ReceiptRepository receiptRepository;

    // 영수증 생성 (테스트 용)
    @Override
    public void createReceipt(ReceiptCreateTestDto receiptCreateDto) {
        receiptRepository.save(Receipt.builder()
                .alliance(receiptCreateDto.getAlliance())
                .brand(receiptCreateDto.getBrand())
                .storeName(receiptCreateDto.getStoreName())
                .number(receiptCreateDto.getNumber())
                .amount(receiptCreateDto.getAmount())
                .point(receiptCreateDto.getPoint())
                .cardName(receiptCreateDto.getCardName())
                .cardNumber(receiptCreateDto.getCardNumber())
                .status(0)
                .build());
    }

    // 영수증으로 적립
    @Transactional
    @Override
    public void createPointByReceipt(String UUID, ReceiptGetDto receiptGetDto) {
        Receipt receipt
                = receiptRepository.findByAllianceAndBrandAndStoreNameAndNumber(receiptGetDto.getAlliance(),
                        receiptGetDto.getBrand(), receiptGetDto.getStoreName(), receiptGetDto.getCardNumber())
                .orElseThrow(() -> new IllegalArgumentException("영수증 일련번호를 다시 확인해 주세요."));

        PointCreateDto pointCreateDto = PointCreateDto.builder()
                .point(receipt.getPoint())
                .title(receipt.getStoreName())
                .statusType("0")
                .type("5")
                //.user(UUID)
                .build();

        pointService.createPoint(UUID, pointCreateDto);

        // 영수증 상태 정보 변경
        receipt.update(1);

    }

}
