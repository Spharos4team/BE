package com.spharos.ssgpoint.user.infrastructure;

import com.spharos.ssgpoint.term.domain.UserTermList;
import com.spharos.ssgpoint.user.domain.PointHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PasswordHistoryRepository extends JpaRepository<PointHistory,Long>    {
    @Query("SELECT p FROM PointHistory p JOIN p.user u ON u.id = p.user.id WHERE u.uuid = :uuid")
    PointHistory findHistoryByUuid(@Param("uuid") String uuid);
}
