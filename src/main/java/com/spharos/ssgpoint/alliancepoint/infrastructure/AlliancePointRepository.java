package com.spharos.ssgpoint.alliancepoint.infrastructure;

import com.spharos.ssgpoint.alliancepoint.domain.AlliancePoint;
import com.spharos.ssgpoint.alliancepoint.domain.AlliancePointType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlliancePointRepository extends JpaRepository<AlliancePoint, Long> {

    List<AlliancePoint> findByUUID(String UUID);

    List<AlliancePoint> findByUUIDAndType(String UUID, AlliancePointType type);

}
