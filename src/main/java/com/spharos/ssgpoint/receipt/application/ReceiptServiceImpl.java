package com.spharos.ssgpoint.receipt.application;

import com.spharos.ssgpoint.receipt.domain.Receipt;
import com.spharos.ssgpoint.receipt.dto.ReceiptCreateDto;
import com.spharos.ssgpoint.receipt.dto.ReceiptGetDto;
import com.spharos.ssgpoint.receipt.dto.ReceiptUpdateDto;
import com.spharos.ssgpoint.receipt.infrastructure.ReceiptRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    // 영수증 생성 (테스트 용)
    @Override
    public void createReceipt(ReceiptCreateDto receiptCreateDto) {
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

    // 영수증 일련번호로 영수증 정보 조회
    @Override
    public List<ReceiptGetDto> getReceiptByNumber(String number) {
        List<Receipt> receiptList = receiptRepository.findByNumber(number);

        return receiptList.stream().map(receipt ->
                ReceiptGetDto.builder()
                        .storeName(receipt.getStoreName())
                        .point(receipt.getPoint())
                        .build()
        ).toList();
    }

    // 영수증 포인트 적립 후 영수증 테이블 상태 컬럼 변경
    @Transactional
    @Override
    public void updateReceipt(String number, ReceiptUpdateDto receiptUpdateDto) {
        List<Receipt> receiptList = receiptRepository.findByNumber(number);

        for (Receipt receipt : receiptList) {
            receipt.update(receiptUpdateDto.getStatus());
        }
    }

}
