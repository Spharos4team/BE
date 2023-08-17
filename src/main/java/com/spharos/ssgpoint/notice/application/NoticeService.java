package com.spharos.ssgpoint.notice.application;

import com.spharos.ssgpoint.notice.domain.Notice;
import com.spharos.ssgpoint.notice.infrastructure.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    public List<Notice> getAllNotices() {
        return noticeRepository.findAll();
    }

    // 추가적인 서비스 메서드 구현...
}
