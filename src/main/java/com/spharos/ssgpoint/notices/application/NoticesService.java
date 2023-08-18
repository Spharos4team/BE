package com.spharos.ssgpoint.notices.application;

import com.spharos.ssgpoint.notices.domain.Notices;
import com.spharos.ssgpoint.notices.infrastructure.NoticesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticesService {

    private final NoticesRepository noticesRepository;

    public List<Notices> findAllNotices() {
        return noticesRepository.findAll();
    }

    public Notices findNoticeById(Long id) {
        return noticesRepository.findById(id).orElse(null);
    }

    public Notices createNotice(Notices notices) {
        return noticesRepository.save(notices);
    }

    public void deleteNotice(Long id) {
        noticesRepository.deleteById(id);
    }
}
