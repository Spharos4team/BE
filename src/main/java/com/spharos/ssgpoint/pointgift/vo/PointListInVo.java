package com.spharos.ssgpoint.pointgift.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointListInVo {
    private LocalDate startDate;
    private LocalDate endDate;
}
