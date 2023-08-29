package com.spharos.ssgpoint.term.application;

import com.spharos.ssgpoint.term.domain.ServiceTerm;

public interface TermService {
    ServiceTerm getContent(Long id);
}
