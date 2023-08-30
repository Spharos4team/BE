package com.spharos.ssgpoint.term.application;

import com.spharos.ssgpoint.term.domain.ServiceTerm;
import com.spharos.ssgpoint.term.domain.UserServiceTerm;
import com.spharos.ssgpoint.term.dto.ServiceTermListDto;
import com.spharos.ssgpoint.term.dto.ServiceTermRequestDto;
import com.spharos.ssgpoint.term.dto.ServiceTermResponseDto;
import com.spharos.ssgpoint.term.infrastructure.TermRepository;

import com.spharos.ssgpoint.term.infrastructure.UserServiceRepository;
import com.spharos.ssgpoint.user.domain.User;
import com.spharos.ssgpoint.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TermServiceImp implements TermService{

    private final TermRepository termRepository;
    private final UserRepository userRepository;
    private final UserServiceRepository userServiceRepository;
    @Override
    public ServiceTerm getContent(Long id) {
        return termRepository.findById(id).get();
    }


    @Transactional
    @Override
    public ServiceTermResponseDto checkTerm(String UUID, ServiceTermRequestDto serviceTermDto) {
        Optional<UserServiceTerm> byUserUuid = userServiceRepository.findByUserUuid(UUID, serviceTermDto.getTitle());
        if(!byUserUuid.isPresent()){
            User user = userRepository.findByUuid(UUID).get();
            UserServiceTerm build = UserServiceTerm.builder()
                    .title(serviceTermDto.getTitle())
                    .agreed(serviceTermDto.isAgreed())
                    .user(user)
                    .build();
            userServiceRepository.save(build);
            return ServiceTermResponseDto.builder()
                    .title(build.getTitle())
                    .agreed(build.isAgreed())
                    .updateDate(LocalDate.now())
                    .build();
        }
        else{
            byUserUuid.get().setAgreed(serviceTermDto.isAgreed());
            return ServiceTermResponseDto.builder()
                .title(byUserUuid.get().getTitle())
                .agreed(byUserUuid.get().isAgreed())
                    .updateDate(LocalDate.now())
                .build();
        }

    }

    @Override
    public List<ServiceTermListDto> getTermList(String UUID) {
        List<ServiceTermListDto> byServiceList = userServiceRepository.findByServiceList(UUID);

return byServiceList;
    }

}
