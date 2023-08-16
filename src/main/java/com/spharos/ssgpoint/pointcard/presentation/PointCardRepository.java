package com.spharos.ssgpoint.pointcard.presentation;

import com.spharos.ssgpoint.pointcard.domain.PointCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointCardRepository extends JpaRepository<PointCard, Long> {
}
