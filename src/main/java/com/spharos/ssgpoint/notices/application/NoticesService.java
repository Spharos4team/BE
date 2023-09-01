package com.spharos.ssgpoint.notices.application;

import com.spharos.ssgpoint.notices.domain.Notices;

import java.util.List;

public interface NoticesService {
    List<Notices> findAllNotices();

    Notices findNoticeById(Long id);

    Notices createNotice(Notices notices);

    void deleteNotice(Long id);
}
