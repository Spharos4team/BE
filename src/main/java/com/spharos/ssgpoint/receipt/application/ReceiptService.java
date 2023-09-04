package com.spharos.ssgpoint.receipt.application;

import com.spharos.ssgpoint.receipt.dto.ReceiptCreateTestDto;
import com.spharos.ssgpoint.receipt.dto.ReceiptGetDto;

public interface ReceiptService {

    // 영수증 생성 (테스트 용)
    void createReceipt(ReceiptCreateTestDto receiptCreateDto);

    // 영수증으로 적립
    void createPointByReceipt(String UUID, ReceiptGetDto receiptGetDto);

}
