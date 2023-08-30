package com.spharos.ssgpoint.term.application;

import com.spharos.ssgpoint.term.domain.ServiceTerm;
import com.spharos.ssgpoint.term.domain.UserServiceTerm;
import com.spharos.ssgpoint.term.dto.ServiceTermListDto;
import com.spharos.ssgpoint.term.dto.ServiceTermRequestDto;
import com.spharos.ssgpoint.term.dto.ServiceTermResponseDto;

import java.util.List;

public interface TermService {
    ServiceTerm getContent(Long id);

    ServiceTermResponseDto checkTerm(String UUID, ServiceTermRequestDto serviceTermDto);

    List<ServiceTermListDto> getTermList(String UUID);
}
