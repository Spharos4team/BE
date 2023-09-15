package com.spharos.ssgpoint.point.infrastructure;

import com.spharos.ssgpoint.point.domain.Point;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PointRepository extends JpaRepository<Point, Long> , PointRepositoryCustom{

    @Query("SELECT p FROM Point p"
            + " JOIN p.user u on u.id = p.user.id"
            + " WHERE u.uuid = :uuid and p.createdDate >= CURRENT DATE - 30"
            + " ORDER BY p.createdDate DESC ")
    Page<Point> findByUserId(@Param("uuid") String uuid, Pageable pageRequest); //전체 적립

    @Query("SELECT p FROM Point p"
            + " JOIN p.user u on u.id = p.user.id"
            + " WHERE p.type = '1' or p.type = '2' or p.type = '3'or p.type='4' " +
            "and u.uuid = :uuid and p.createdDate >= CURRENT DATE - 30 "
            + " ORDER BY p.createdDate DESC ")
    Page<Point> findBySavePoint(@Param("uuid") String uuid, Pageable pageRequest);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="1000")})
    @Query("SELECT p.totalPoint FROM Point p"
            + " JOIN p.user u on u.id = p.user.id"
            + " WHERE u.uuid = :uuid"
            + " ORDER BY p.id DESC LIMIT 1")
    Integer findTotalPoint(@Param("uuid") String uuid);

    Long countByUserId(Long user_id);

    @Query("select p from Point p where p.user.id = :userid")
    Point findByUserId(@Param("userid")String userId);


}
