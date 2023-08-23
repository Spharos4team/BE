package com.spharos.ssgpoint.pointgift.application;

import com.spharos.ssgpoint.point.domain.Point;
import com.spharos.ssgpoint.point.dto.PointCreateDto;
import com.spharos.ssgpoint.point.infrastructure.PointRepository;
import com.spharos.ssgpoint.pointgift.domain.PointGift;
import com.spharos.ssgpoint.pointgift.dto.PointGiftCreateDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftGetDto;
import com.spharos.ssgpoint.pointgift.presentation.PointGiftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointGiftServiceImpl implements PointGiftService {

    private final PointRepository pointRepository;
    private final PointGiftRepository pointGiftRepository;

    // 포인트 선물 보내기
    @Override
    public void createPointGift(String UUID, PointGiftCreateDto pointGiftCreateDto, PointCreateDto pointCreateDto) {
        pointGiftRepository.save(PointGift.builder()
                .point(pointGiftCreateDto.getPoint())
                .message(pointGiftCreateDto.getMessage())
                .access(pointGiftCreateDto.getAccess())
                .UUID(pointGiftCreateDto.getUUID())
                .loginId(pointGiftCreateDto.getLoginId())
                .build());

    }

    // 포인트 선물 목록
    @Override
    public List<PointGiftGetDto> getPointByUser(String UUID) {
        List<PointGift> pointGiftList = pointGiftRepository.findByUUID(UUID);

        return pointGiftList.stream().map(pointGift ->
            PointGiftGetDto.builder()
                    .point(pointGift.getPoint())
                    .message(pointGift.getMessage())
                    .access(pointGift.getAccess())
                    .UUID(pointGift.getUUID())
                    .build()
        ).toList();
    }

}
