package com.spharos.ssgpoint.term.infrastructure;

import com.spharos.ssgpoint.point.domain.Point;
import com.spharos.ssgpoint.term.domain.ServiceTerm;
import com.spharos.ssgpoint.term.domain.UserServiceTerm;
import com.spharos.ssgpoint.term.dto.ServiceTermResponseDto;
import com.spharos.ssgpoint.term.dto.TermContentDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TermRepository extends JpaRepository<ServiceTerm, Long> {

    @Query("select new com.spharos.ssgpoint.term.dto.TermContentDto( t.termContent) from ServiceTerm t where t.id = :id")
    TermContentDto findTermContentByTitle(@Param("id") Long id);

}
