package com.spharos.ssgpoint.receipt.application;

import com.spharos.ssgpoint.receipt.dto.ReceiptCreateDto;
import com.spharos.ssgpoint.receipt.dto.ReceiptGetDto;
import com.spharos.ssgpoint.receipt.dto.ReceiptUpdateDto;

import java.util.List;

public interface ReceiptService {

    // 영수증 생성 (테스트 용)
    void createReceipt(ReceiptCreateDto receiptCreateDto);
    // 영수증 일련번호로 영수증 정보 조회
    List<ReceiptGetDto> getReceiptByNumber(String number);
    // 영수증 포인트 적립 후 영수증 테이블 상태 컬럼 변경
    void updateReceipt(String number, ReceiptUpdateDto receiptUpdateDto);

}
