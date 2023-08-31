package com.spharos.ssgpoint.point.application;

import com.spharos.ssgpoint.point.domain.Point;
import com.spharos.ssgpoint.point.domain.PointType;
import com.spharos.ssgpoint.point.domain.PointTypeConverter;
import com.spharos.ssgpoint.point.dto.PointCreateDto;
import com.spharos.ssgpoint.point.dto.PointGetDto;
import com.spharos.ssgpoint.point.infrastructure.PointRepository;
import com.spharos.ssgpoint.user.domain.User;
import com.spharos.ssgpoint.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final UserRepository userRepository;
    private final PointRepository pointRepository;

    // 포인트 생성
    @Override
    public void createPoint(String UUID, PointCreateDto pointCreateDto) {
        PointType pointType = new PointTypeConverter().convertToEntityAttribute(pointCreateDto.getType());
        User user = userRepository.findByUuid(UUID).orElseThrow(() ->
                new IllegalArgumentException("UUID 정보 없음 = " + UUID));

        // totalPoint 계산
        List<Point> pointList = pointRepository.findByUserIdOrderById(user.getUuid());
        Long count = pointRepository.countByUserId(user.getId());

        int totalPoint = 0;

        if (count.equals(0L)) {
            totalPoint = pointCreateDto.getPoint();
        } else {
            for (Point point : pointList) {
                if (pointType.getCode().equals("1") || pointType.getCode().equals("2")
                        || pointType.getCode().equals("6") || pointType.getCode().equals("7")) {
                    totalPoint = point.getTotalPoint() + pointCreateDto.getPoint();
                }
                if (pointType.getCode().equals("3") || pointType.getCode().equals("4") || pointType.getCode().equals("5")) {
                    totalPoint = point.getTotalPoint() - pointCreateDto.getPoint();
                }
            }
        }

        pointRepository.save(Point.builder()
                .totalPoint(totalPoint)
                .point(pointCreateDto.getPoint())
                .title(pointCreateDto.getTitle())
                .content(pointCreateDto.getContent())
                .type(pointType)
                .user(user)
                .build());
    }

    // 포인트 목록
    @Override
    public List<PointGetDto> getPointByUser(String UUID) {
        User user = userRepository.findByUuid(UUID).orElseThrow(() ->
                new IllegalArgumentException("UUID 정보 없음 = " + UUID));

        List<Point> pointList = pointRepository.findByUserId(user.getUuid());

        return pointList.stream().map(point ->
                PointGetDto.builder()
                        .totalPoint(point.getTotalPoint())
                        .point(point.getPoint())
                        .title(point.getTitle())
                        .content(point.getContent())
                        .type(String.valueOf(point.getType().getValue()))
                        .createdDate(LocalDateTime.from(point.getCreatedDate()))
                        .build()
        ).toList();
    }

}
