package com.spharos.ssgpoint.pointgift.application;

import com.spharos.ssgpoint.pointgift.dto.PointGiftCreateDto;

public interface PointGiftService {

    void createPointGift(String UUID, PointGiftCreateDto pointGiftCreateDto);

}
