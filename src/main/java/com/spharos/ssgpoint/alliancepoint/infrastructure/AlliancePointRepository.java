package com.spharos.ssgpoint.alliancepoint.infrastructure;

import com.spharos.ssgpoint.alliancepoint.domain.AlliancePoint;
import com.spharos.ssgpoint.alliancepoint.domain.AlliancePointType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AlliancePointRepository extends JpaRepository<AlliancePoint, Long> ,AlliancePointRepositoryCustom {

    List<AlliancePoint> findByUUID(String UUID);

    @Query("select a from AlliancePoint a where a.UUID = :UUID and a.type = :alliancePointType")
    Optional<AlliancePoint> findByAllianceUUID(@Param("UUID") String UUID,@Param("alliancePointType") AlliancePointType alliancePointType);

    List<AlliancePoint> findByUUIDAndType(String UUID, AlliancePointType type);

}
