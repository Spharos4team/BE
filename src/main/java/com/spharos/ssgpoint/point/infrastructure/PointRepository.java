package com.spharos.ssgpoint.point.infrastructure;

import com.spharos.ssgpoint.point.domain.Point;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
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
    @Query("SELECT p.totalPoint FROM Point p"
            + " JOIN p.user u on u.id = p.user.id"
            + " WHERE u.uuid = :uuid"
            + " ORDER BY p.id DESC LIMIT 1")
    Integer findByUserIdOrderById(@Param("uuid") String uuid);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p.totalPoint FROM Point p where p.user.id = :user_id order by p.createdDate desc")
    Integer findTotalPoint(@Param("user_id") Long user_id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Long countByUserId(Long user_id);

    @Query("select p from Point p where p.user.id = :user_id")
    Point findByUserId(String userId);


}
