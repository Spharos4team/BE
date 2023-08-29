package com.spharos.ssgpoint.user.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spharos.ssgpoint.exception.CustomException;
import com.spharos.ssgpoint.point.domain.Point;
import com.spharos.ssgpoint.term.domain.UserTermList;
import com.spharos.ssgpoint.user.domain.User;
import com.spharos.ssgpoint.user.dto.*;
import com.spharos.ssgpoint.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;
    private ObjectMapper objectMapper;
    @Override
    public void createUser(UserSignUpDto userSignUpDto) {

        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();

        User user = User.builder()
                .loginId(userSignUpDto.getLoginId())
                .uuid(uuidString)
                .name(userSignUpDto.getName())
                .password(userSignUpDto.getPassword())
                .email(userSignUpDto.getEmail())
                .phone(userSignUpDto.getPhone())
                .address(userSignUpDto.getAddress())
                .status(1)
                .build();
        userRepository.save(user);
    }

    @Override
    public UserGetDto getUserByLoginId(String loginId) {
        User user = userRepository.findByLoginId(loginId).orElseThrow(() ->
                new IllegalArgumentException("아이디가 존재하지 않습니다."));
        UserGetDto userGetDto = UserGetDto.builder()
                    .loginId(user.getLoginId())
                    .name(user.getUsername())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .address(user.getAddress())
                    .build();
            return userGetDto;
    }

    @Override
    public UserGetDto getUserByUUID(String UUID) {
        User user = userRepository.findByUuid(UUID).orElseThrow(() -> new IllegalArgumentException("UUID정보 없음 "));
        log.info("user is : {}" , user);
        UserGetDto userGetDto = UserGetDto.builder()
                .loginId(user.getLoginId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .build();
        return userGetDto;
    }

    /**
     * 회원정보 수정
     */
    @Transactional
    @Override
    public void updateUserInfo(String UUID, UserUpdateDto userUpdateRequestDto) {
        User user = userRepository.findByUuid(UUID).orElseThrow(() -> new IllegalArgumentException("UUID정보 없음  "));
        user.updateUserInfo(userUpdateRequestDto.getAddress(), userUpdateRequestDto.getEmail());
    }

    /**
     * 회원가입시 아이디 중복 확인
     */

    public void validateDuplicateLoginId(UserSignUpDto userSignUpDto) {
        log.info("userSignUpDto={}", userSignUpDto.getLoginId());
        Optional<User> byLoginId = userRepository.findByLoginId(userSignUpDto.getLoginId());
        if(byLoginId.isPresent()){
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }
    }


    /**
     * 비밀번호 수정
     */
    @Transactional
    @Override
    public void updatePassword(String UUID, PasswordUpdateDto passwordUpdateDto) {
        User user = userRepository.findByUuid(UUID).orElseThrow(() -> new IllegalArgumentException("UUID정보 없음"));
        user.hashPassword(passwordUpdateDto.getPassword());
    }


    /**
     * 광고정보 수신관리 조회
     */

    @Override
    public Map<String,Boolean> getTerm(String UUID) {
        UserTermList termJsonByUuid = userRepository.findTermJsonByUuid(UUID).orElseThrow(() -> new IllegalArgumentException("UUID정보 없음"));
        Map<String, Boolean> termJson = termJsonByUuid.getTermJson();
        log.info("term={}",termJson);
        return termJson;
    }

    /**
     * 광고정보 수신관리 수정
     */
    @Transactional
    @Override
    public Map<String, Boolean> updateTerm(String UUID,TermUpdateDto termUpdateDto) {
        log.info("termUpdateDto={}",termUpdateDto.getTermJson());
        UserTermList termJsonByUuid = userRepository.findTermJsonByUuid(UUID).orElseThrow(() -> new IllegalArgumentException("UUID정보 없음"));
        termJsonByUuid.updateTermJson(termUpdateDto.getTermJson());
        return termJsonByUuid.getTermJson();
    }

    /**
     * 회원탈퇴 status 1->0으로
     */
    @Transactional
    @Override
    public void softDeleteUser(String UUID) {
        User user = userRepository.findByUuid(UUID).orElseThrow(() -> new IllegalArgumentException("UUID정보 없음"));
        user.changeStatus(0);
    }

    /**
     * 포인트 조회
     */
    @Override
    public PointGetDto getPoint(String UUID) {
        Point topByUuid = userRepository.findTotalByUuid(UUID);
        Integer totalPoint = topByUuid.getTotalPoint();
        return PointGetDto.builder()
                .totalPoint(totalPoint)
                .build();

    }


}
