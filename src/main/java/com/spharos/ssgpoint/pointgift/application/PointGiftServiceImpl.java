package com.spharos.ssgpoint.pointgift.application;

import com.spharos.ssgpoint.pointgift.domain.PointGift;
import com.spharos.ssgpoint.pointgift.dto.PointGiftCreateDto;
import com.spharos.ssgpoint.pointgift.presentation.PointGiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointGiftServiceImpl implements PointGiftService {

    private final PointGiftRepository pointGiftRepository;

    @Override
    public void createPointGift(String UUID, PointGiftCreateDto pointGiftCreateDto) {

        pointGiftRepository.save(PointGift.builder()
                .message(pointGiftCreateDto.getMessage())
                .access(pointGiftCreateDto.getAccess())
                .UUID(pointGiftCreateDto.getUUID())
                .loginId(pointGiftCreateDto.getLoginId())
                .build());
    }

}
