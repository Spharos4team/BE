package com.spharos.ssgpoint.term.infrastructure;

import com.spharos.ssgpoint.term.domain.UserServiceTerm;
import com.spharos.ssgpoint.term.dto.ServiceTermListDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserServiceRepository extends JpaRepository<UserServiceTerm, Long> {
    @Query("SELECT t from UserServiceTerm t join t.user u on u.id = t.user.id where u.uuid = :uuid and t.title = :title")
    Optional<UserServiceTerm> findByUserUuid(@Param("uuid") String uuid, @Param("title") String title);
    @Query("SELECT new com.spharos.ssgpoint.term.dto.ServiceTermListDto(t.title,t.agreed,t.updatedDate) from UserServiceTerm t join t.user u where u.uuid = :uuid")
    List<ServiceTermListDto> findByServiceList(@Param("uuid") String uuid);

}
