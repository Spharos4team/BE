package com.spharos.ssgpoint.pointcard.infrastructure;

import com.spharos.ssgpoint.point.domain.Point;
import com.spharos.ssgpoint.pointcard.domain.PointCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PointCardRepository extends JpaRepository<PointCard, Long> {

    List<PointCard> findByUUID(String UUID);
    Optional<PointCard> findByNumber(String number);

}
