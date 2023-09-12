package com.spharos.ssgpoint.attendancecheck.application;

import com.spharos.ssgpoint.attendancecheck.domain.AttendanceCheck;
import com.spharos.ssgpoint.attendancecheck.infrastructure.AttendanceCheckRepository;
import com.spharos.ssgpoint.point.application.PointService;
import com.spharos.ssgpoint.point.dto.PointCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceCheckServiceImpl implements AttendanceCheckService {

    private final PointService pointService;

    private final AttendanceCheckRepository attendanceCheckRepository;

    // 출석 체크
    @Override
    public void createAttendanceCheck(String UUID) {
        // 하루 전 날짜
        LocalDate date = LocalDate.now().minusDays(1);

        // 이번 달
        int month = LocalDate.now().getMonth().getValue();

        // count 연산
        List<AttendanceCheck> attendanceCheckList = attendanceCheckRepository.findByUUIDOrderById(UUID, date, month);
        Long count = attendanceCheckRepository.countByUUID(UUID, date, month);

        int calcCount = 1;

        if (!count.equals(0L)) {
            for (AttendanceCheck attendanceCheck : attendanceCheckList) {
                if (attendanceCheck.getCount().equals(10)) {
                    calcCount = 1;
                } else {
                    calcCount = attendanceCheck.getCount() + 1;
                }
            }
        }

        // 출석 체크 테이블에 추가
        attendanceCheckRepository.save(AttendanceCheck.builder()
                .count(calcCount)
                .UUID(UUID)
                .build());

        // point 테이블에 추가
        PointCreateDto pointCreateDto_1 = PointCreateDto.builder()
                .point(1)
                .title("신세계포인트닷컴")
                .content("[APP] 출석체크 포인트 지급")
                .statusType("0")
                .type("2")
                .user(UUID)
                .build();

        pointService.createPoint(UUID, pointCreateDto_1);

        // 10일 연속 출석 시
        if (calcCount == 10) {
            PointCreateDto pointCreateDto_10 = PointCreateDto.builder()
                    .point(10)
                    .title("신세계포인트닷컴")
                    .content("[APP] 출석체크 포인트 지급")
                    .statusType("0")
                    .type("2")
                    .user(UUID)
                    .build();

            pointService.createPoint(UUID, pointCreateDto_10);
        }
    }

}
