package com.spharos.ssgpoint.receipt.application;

import com.spharos.ssgpoint.receipt.dto.ReceiptGetDto;

import java.util.List;

public interface ReceiptService {

    // 영수증
    List<ReceiptGetDto> getReceiptByNumber(String number);

}
