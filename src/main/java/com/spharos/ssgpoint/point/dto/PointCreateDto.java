package com.spharos.ssgpoint.point.dto;


import com.spharos.ssgpoint.point.vo.PointCreateVo;
import com.spharos.ssgpoint.receipt.domain.Receipt;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointCreateDto {

    private Integer totalPoint;
    private Integer point;
    private String title;
    private String content;
    private Integer used;
    private String type;
    private String user;
    private Long pointCardId;

    private ReceiptDto receipt;

    @Data
    @Builder
    public static class ReceiptDto {

        private String alliance;
        private String brand;
        private String storeName;
        private String number;
        private Integer amount;
        private Integer receiptPoint; // 주의: receiptPoint로 이름 변경
        private String cardName;
        private String cardNumber;

    }

}
