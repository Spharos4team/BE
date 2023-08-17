package com.spharos.ssgpoint.notice.infrastructure;

import com.spharos.ssgpoint.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
