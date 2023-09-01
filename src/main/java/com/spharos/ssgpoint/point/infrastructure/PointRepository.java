package com.spharos.ssgpoint.point.infrastructure;

import com.spharos.ssgpoint.point.domain.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> {

    @Query("SELECT p FROM Point p"
            + " JOIN p.user u on u.id = p.user.id"
            + " WHERE u.uuid = :uuid"
            + " ORDER BY DATE_FORMAT(p.createdDate, '%Y-%m-%d') DESC, p.type DESC")
    List<Point> findByUserId(@Param("uuid") String uuid);

    @Query("SELECT p FROM Point p"
            + " JOIN p.user u on u.id = p.user.id"
            + " WHERE u.uuid = :uuid"
            + " ORDER BY p.id DESC LIMIT 1")
    List<Point> findByUserIdOrderById(@Param("uuid") String uuid);

    Long countByUserId(Long user_id);

}
