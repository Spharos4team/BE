package com.spharos.ssgpoint.point.infrastructure;

import com.spharos.ssgpoint.point.domain.Point;
import com.spharos.ssgpoint.pointcard.domain.PointCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {

    List<Point> findByUUID(String UUID);

}
