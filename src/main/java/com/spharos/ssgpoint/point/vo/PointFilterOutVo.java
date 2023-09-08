package com.spharos.ssgpoint.point.vo;

import com.spharos.ssgpoint.point.domain.PointStatusType;
import com.spharos.ssgpoint.point.domain.PointType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointFilterOutVo {
    private Long pointId;
    private Integer point;
    private String title;
    private String content;
    private PointType type;
    private PointStatusType statusType;
    private LocalDateTime createdDate;
    private Long receiptId;

}
