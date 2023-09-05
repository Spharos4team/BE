package com.spharos.ssgpoint.attendancecheck.presentation;

import com.spharos.ssgpoint.attendancecheck.application.AttendanceCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AttendanceCheckController {

    private final AttendanceCheckService attendanceCheckService;

    // 출석 체크
    @PostMapping("/attendance-check")
    public void createAttendanceCheck(@RequestParam("UUID") String UUID) {
        attendanceCheckService.createAttendanceCheck(UUID);
    }

}
