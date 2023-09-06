package com.spharos.ssgpoint.offlinepointcard.infrastructure;

import com.spharos.ssgpoint.offlinepointcard.domain.OfflinePointCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfflinePointCardRepository extends JpaRepository<OfflinePointCard, Long> {

    Optional<OfflinePointCard> findByNumberAndCVCAndAllianceAndStore(String number, Integer CVC,
                                                                     String alliance, String store);

}
