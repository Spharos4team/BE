package com.spharos.ssgpoint.pointgift.application;

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

    private final PointGiftRepository pointGiftRepository;

    @Override
    public void createPointGift(String UUID, PointGiftCreateDto pointGiftCreateDto) {
        pointGiftRepository.save(PointGift.builder()
                .point(pointGiftCreateDto.getPoint())
                .message(pointGiftCreateDto.getMessage())
                .access(pointGiftCreateDto.getAccess())
                .UUID(pointGiftCreateDto.getUUID())
                .loginId(pointGiftCreateDto.getLoginId())
                .build());
    }

    @Override
    public List<PointGiftGetDto> getPointByUser(String UUID) {
        List<PointGift> pointGiftList = pointGiftRepository.findByUUID(UUID);
        List<PointGiftGetDto> pointGiftGetDtoList = pointGiftList.stream().map(pointGift -> {
            return PointGiftGetDto.builder()
                    .point(pointGift.getPoint())
                    .message(pointGift.getMessage())
                    .access(pointGift.getAccess())
                    .UUID(pointGift.getUUID())
                    .build();
        }).toList();

        return pointGiftGetDtoList;
    }

}
