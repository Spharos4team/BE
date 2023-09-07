package com.spharos.ssgpoint.event.infrastructure;

import com.spharos.ssgpoint.event.domain.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEventRepository extends JpaRepository<UserEvent, Long> {

    List<UserEvent> findByUuid(String uuid);

    List<UserEvent> findByUuidAndWinning(String uuid, boolean isWinning);

    UserEvent findByUuidAndEventId(String uuid, Long eventId);

}
