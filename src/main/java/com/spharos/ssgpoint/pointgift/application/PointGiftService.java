package com.spharos.ssgpoint.pointgift.application;

import com.spharos.ssgpoint.pointgift.dto.PointGiftCreateDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftGetDto;

import java.util.List;

public interface PointGiftService {

    void createPointGift(String UUID, PointGiftCreateDto pointGiftCreateDto);
    List<PointGiftGetDto> getPointByUser(String UUID);

}
