package com.spharos.ssgpoint.club.infrastructure;

import com.spharos.ssgpoint.club.domain.ClubList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubListRepository extends JpaRepository<ClubList, Long> {
}
