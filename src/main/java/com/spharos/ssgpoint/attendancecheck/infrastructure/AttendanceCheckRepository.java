package com.spharos.ssgpoint.attendancecheck.infrastructure;

import com.spharos.ssgpoint.attendancecheck.domain.AttendanceCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public interface AttendanceCheckRepository extends JpaRepository<AttendanceCheck, Long> {

    @Query("SELECT a FROM AttendanceCheck a"
            + " WHERE a.UUID = :UUID"
            + " AND DATE_FORMAT(a.createdDate, '%Y-%m-%d') = :date"
            + " AND DATE_FORMAT(a.createdDate, '%c') = :month"
            + " ORDER BY a.id DESC LIMIT 1")
    List<AttendanceCheck> findByUUIDOrderById(@Param("UUID") String UUID,
                                              @Param("date") LocalDate date,
                                              @Param("month") int month);

    @Query("SELECT COUNT(*) FROM AttendanceCheck a"
            + " WHERE a.UUID = :UUID"
            + " AND DATE_FORMAT(a.createdDate, '%Y-%m-%d') = :date"
            + " AND DATE_FORMAT(a.createdDate, '%c') = :month")
    Long countByUUID(@Param("UUID") String UUID,
                     @Param("date") LocalDate date,
                     @Param("month") int month);

}
