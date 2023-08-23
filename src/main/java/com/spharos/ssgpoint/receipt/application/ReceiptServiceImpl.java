package com.spharos.ssgpoint.receipt.application;

import com.spharos.ssgpoint.receipt.domain.Receipt;
import com.spharos.ssgpoint.receipt.dto.ReceiptGetDto;
import com.spharos.ssgpoint.receipt.infrastructure.ReceiptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    // 영수증
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

}
