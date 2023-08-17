package com.spharos.ssgpoint.notice.presentation;

import com.spharos.ssgpoint.notice.application.NoticeService;
import com.spharos.ssgpoint.notice.domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @GetMapping
    public List<Notice> getAllNotices() {
        return noticeService.getAllNotices();
    }

    // 추가적인 컨트롤러 메서드 구현...
}
