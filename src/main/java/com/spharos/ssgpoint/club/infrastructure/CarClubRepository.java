package com.spharos.ssgpoint.club.infrastructure;

import com.spharos.ssgpoint.club.domain.CarClub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarClubRepository extends JpaRepository<CarClub, Long> {
}
