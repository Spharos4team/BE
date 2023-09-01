package com.spharos.ssgpoint.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserCompositeDto {
    private PointGetDto pointGetDto;
    private UserSavePointDto userSavePointDto;
    private UserUsePointDto userUsePointDto;
    private VisitedCountDto visitedCountDto;
    private TotalPointDtoByReceipt totalPointDtoByReceipt;

    private List<FrequentBrandTop3CountDto> frequentBrandTop3Dto;
    private List<FrequentBrandTop3SumDto> frequentBrandTop3SumDto;
}
