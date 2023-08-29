package com.spharos.ssgpoint.term.application;

import com.spharos.ssgpoint.term.domain.ServiceTerm;
import com.spharos.ssgpoint.term.infrastructure.TermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class TermServiceImp implements TermService{

    private final TermRepository termRepository;

    @Override
    public ServiceTerm getContent(Long id) {
        return termRepository.findById(id).get();
    }
}
