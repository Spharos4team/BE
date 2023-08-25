package com.spharos.ssgpoint.receipt.application;

import com.spharos.ssgpoint.receipt.dto.ReceiptGetDto;

import java.util.List;

public interface ReceiptService {

    // 영수증 일련번호로 영수증 정보 조회
    List<ReceiptGetDto> getReceiptByNumber(String number);

}
