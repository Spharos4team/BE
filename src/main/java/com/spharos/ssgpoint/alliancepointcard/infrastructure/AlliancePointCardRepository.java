package com.spharos.ssgpoint.alliancepointcard.infrastructure;

import com.spharos.ssgpoint.alliancepointcard.domain.AlliancePointCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlliancePointCardRepository extends JpaRepository<AlliancePointCard, Long> {

    List<AlliancePointCard> findByUUID(String UUID);

}
