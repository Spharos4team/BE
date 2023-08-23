package com.spharos.ssgpoint.term.application;

import com.fasterxml.jackson.core.JsonParser;
import com.spharos.ssgpoint.term.infrastructure.TermRepository;
import com.spharos.ssgpoint.user.domain.User;
import com.spharos.ssgpoint.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TermServiceImp implements TermService{

    private final TermRepository termRepository;
    private final UserRepository userRepository;

    @Override
    public void saveTerm(String UUID , Map<String,Boolean> agreements){

    }
}
