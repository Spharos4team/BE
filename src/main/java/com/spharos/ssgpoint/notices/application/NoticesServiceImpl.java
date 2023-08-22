package com.spharos.ssgpoint.notices.application;

import com.spharos.ssgpoint.notices.domain.Notices;
import com.spharos.ssgpoint.notices.infrastructure.NoticesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        return noticesRepository.findById(id).orElse(null);
    }

    @Override
    public Notices createNotice(Notices notices) {
        return noticesRepository.save(notices);
    }

    @Override
    public void deleteNotice(Long id) {
        noticesRepository.deleteById(id);
    }
}
