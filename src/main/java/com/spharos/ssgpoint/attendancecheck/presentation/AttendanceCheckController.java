package com.spharos.ssgpoint.attendancecheck.presentation;

import com.spharos.ssgpoint.attendancecheck.application.AttendanceCheckService;
import com.spharos.ssgpoint.attendancecheck.dto.AttendanceCheckCreateDto;
import com.spharos.ssgpoint.attendancecheck.vo.AttendanceCheckCreateVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
