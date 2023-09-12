package com.spharos.ssgpoint.pointcard.infrastructure;

import com.spharos.ssgpoint.point.domain.Point;
import com.spharos.ssgpoint.pointcard.domain.PointCard;
import com.spharos.ssgpoint.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PointCardRepository extends JpaRepository<PointCard, Long> {

    List<PointCard> findByUUID(String UUID);
    //@Query("SELECT p.number from PointCard p where p.UUID = :uuid ")
    //String findNumberByUUID(String UUID);

    @Query("SELECT p from PointCard p where p.number = :number ")
    Optional<PointCard> findByNumber(@Param("number") String number);

    @Query("SELECT p.number from PointCard p where p.UUID = :UUID order by p.createdDate asc limit 1 ")
    String findNumberByUUID(@Param("UUID") String uuid);


}
