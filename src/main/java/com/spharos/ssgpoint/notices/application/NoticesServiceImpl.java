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

    /**
     * 공지사항 전체 조회
     * @return
     */
    @Override
    public List<Notices> findAllNotices() {
        return noticesRepository.findAll();
    }

    /**
     * 공지사항 상세 조회
     * @param id
     * @return
     */
    @Override
    public Notices findNoticeById(Long id) {
        return noticesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 글 " + id + " 를 찾을수 없습니다.."));
    }

    /**
     * 공지사항 생성
     * @param notices
     * @return
     */
    @Override
    public Notices createNotice(Notices notices) {
        notices.setRegDate(new Date());
        return noticesRepository.save(notices);
    }

    /**
     * 공지사항 수정
     * @param id
     * @return
     */
    @Override
    public void deleteNotice(Long id) {
        noticesRepository.deleteById(id);
    }



}