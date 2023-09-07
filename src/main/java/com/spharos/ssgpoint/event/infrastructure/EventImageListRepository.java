package com.spharos.ssgpoint.event.infrastructure;

import com.spharos.ssgpoint.event.domain.EventImageList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventImageListRepository extends JpaRepository<EventImageList, Long> {

    @Query("SELECT e FROM EventImageList e WHERE e.event.id = ?1")
    List<EventImageList> findByEventId(Long eventId);

}
