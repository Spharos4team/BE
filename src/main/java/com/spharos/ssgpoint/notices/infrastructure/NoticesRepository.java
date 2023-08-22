package com.spharos.ssgpoint.notices.infrastructure;

import com.spharos.ssgpoint.notices.domain.Notices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticesRepository extends JpaRepository<Notices, Long> {
}
