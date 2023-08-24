package com.spharos.ssgpoint.notices.application;

import com.spharos.ssgpoint.notices.domain.Notices;
import com.spharos.ssgpoint.notices.infrastructure.NoticesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

// @Service 어노테이션은 이 클래스를 Spring에서 서비스 레이어 컴포넌트로 관리하도록 지정합니다.
@Service
@RequiredArgsConstructor // Lombok 라이브러리를 사용하여 final 필드에 대한 생성자를 자동으로 생성합니다.
public class NoticesServiceImpl implements NoticesService {

    // 노티스(공지사항) 데이터에 접근하기 위한 저장소(repository) 인스턴스
    private final NoticesRepository noticesRepository;

    // 모든 공지사항을 조회하는 메서드
    @Override
    public List<Notices> findAllNotices() {
        return noticesRepository.findAll();
    }

    // 주어진 ID에 해당하는 공지사항을 조회하는 메서드. 찾을 수 없다면 null을 반환합니다.
    @Override
    public Notices findNoticeById(Long id) {
        return noticesRepository.findById(id).orElse(null);
    }

    // 새로운 공지사항을 생성하고 저장하는 메서드
    @Override
    public Notices createNotice(Notices notices) {
        return noticesRepository.save(notices);
    }

    // 주어진 ID의 공지사항을 삭제하는 메서드
    @Override
    public void deleteNotice(Long id) {
        noticesRepository.deleteById(id);
    }
}
