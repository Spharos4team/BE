package com.spharos.ssgpoint.term.infrastructure;

import com.spharos.ssgpoint.term.domain.ServiceTerm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<ServiceTerm, Long> {

}
