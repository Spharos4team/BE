package com.spharos.ssgpoint.temporarycard.infrastructure;

import com.spharos.ssgpoint.temporarycard.domain.TemporaryCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemporaryCardRepository extends JpaRepository<TemporaryCard, Long> {

    Optional<TemporaryCard> findByBirthAndNameAndNumberAndCVC(String birth, String name,
                                                              String number, Integer CVC);

}
