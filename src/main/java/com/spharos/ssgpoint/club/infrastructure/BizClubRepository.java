package com.spharos.ssgpoint.club.infrastructure;

import com.spharos.ssgpoint.club.domain.BizClub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BizClubRepository extends JpaRepository<BizClub, Long> {
}
