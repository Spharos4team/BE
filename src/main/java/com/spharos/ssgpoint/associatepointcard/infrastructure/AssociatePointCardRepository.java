package com.spharos.ssgpoint.associatepointcard.infrastructure;

import com.spharos.ssgpoint.associatepointcard.domain.AssociatePointCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssociatePointCardRepository extends JpaRepository<AssociatePointCard, Long> {

    List<AssociatePointCard> findByUUID(String UUID);

}
