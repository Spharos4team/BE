package com.spharos.ssgpoint.temporarycard.infrastructure;

import com.spharos.ssgpoint.temporarycard.domain.TemporaryCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemporaryCardRepository extends JpaRepository<TemporaryCard, Long> {

    List<TemporaryCard> findByNumber(String number);

}
