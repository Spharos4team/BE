package com.spharos.ssgpoint.offlinepointcard.infrastructure;

import com.spharos.ssgpoint.offlinepointcard.domain.OfflinePointCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfflinePointCardRepository extends JpaRepository<OfflinePointCard, Long> {

    List<OfflinePointCard> findByNumber(String number);

}
