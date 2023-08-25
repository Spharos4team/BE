package com.spharos.ssgpoint.notices.application;

import com.spharos.ssgpoint.notices.domain.Notices;
import com.spharos.ssgpoint.notices.infrastructure.NoticesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticesServiceImpl implements NoticesService {

    private final NoticesRepository noticesRepository;

    @Override
    public List<Notices> findAllNotices() {
        return noticesRepository.findAll();
    }

    @Override
    public Notices findNoticeById(Long id) {
        return noticesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 글 " + id + " 를 찾을수 없습니다.."));
    }

    @Override
    public Notices createNotice(Notices notices) {
        notices.setRegDate(new Date());
        return noticesRepository.save(notices);
    }

    @Override
    public void deleteNotice(Long id) {
        noticesRepository.deleteById(id);
    }
}