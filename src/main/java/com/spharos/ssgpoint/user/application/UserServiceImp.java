package com.spharos.ssgpoint.user.application;


import com.spharos.ssgpoint.exception.AllException;
import com.spharos.ssgpoint.user.domain.User;
import com.spharos.ssgpoint.user.dto.*;
import com.spharos.ssgpoint.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
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
        log.info("user id={}",user.getId());
    }

    @Override
    public UserGetDto getUserByLoginId(String loginId) {

        Optional<User> byLoginId = userRepository.findByLoginId(loginId);
        if(byLoginId.isPresent()) {
            User user = byLoginId.get();


            log.info("user is : {}", user);
            UserGetDto userGetDto = UserGetDto.builder()
                    .loginId(user.getLoginId())
                    .name(user.getUsername())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .address(user.getAddress())
                    .build();
            return userGetDto;
        }
        else{
            return null;
        }

    }

    @Override
    public UserGetDto getUserByUUID(String UUID) {
        User user = userRepository.findByUuid(UUID).orElseThrow(() -> new IllegalArgumentException("UUID정보 없음 = " + UUID));
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
        User user = userRepository.findByUuid(UUID).orElseThrow(() -> new IllegalArgumentException("UUID정보 없음 = " + UUID));

        user.updateUserInfo(userUpdateRequestDto.getAddress(), userUpdateRequestDto.getEmail());

    }

    /**
     * 회원가입시 아이디 중복 확인
     */

    public Boolean validateDuplicateLoginId(UserSignUpDto userSignUpDto) {
        log.info("userSignUpDto={}", userSignUpDto.getLoginId());
        Optional<User> byLoginId = userRepository.findByLoginId(userSignUpDto.getLoginId());
        log.info("byLoginId={}", byLoginId);
        return byLoginId.isPresent();
    }


    /**
     * 비밀번호 찾기 하면 리셋
     */
    @Transactional
    @Override
    public void resetPassword(String UUID, PasswordResetDto passwordResetDto) {
        User user = userRepository.findByUuid(UUID).orElseThrow(() -> new IllegalArgumentException("UUID정보 없음 = " + UUID));
        user.hashPassword(passwordResetDto.getPassword());
    }

    /**
     * 비밀번호 수정
     */
    @Transactional
    @Override
    public void updatePassword(String UUID, PasswordUpdateDto passwordUpdateDto) {
        User user = userRepository.findByUuid(UUID).orElseThrow(() -> new IllegalArgumentException("UUID정보 없음 = " + UUID));
        user.hashPassword(passwordUpdateDto.getPassword());
    }

    /**
     * 포인트 비밀번호 초기설정 + 변경
     */
    @Transactional
    @Override
    public void updatePointPassword(String UUID, PointPasswordUpdateDto pointPasswordUpdateDto) {
        User user = userRepository.findByUuid(UUID).orElseThrow(() -> new IllegalArgumentException("UUID정보 없음 = " + UUID));
        log.info("pointpass={}",pointPasswordUpdateDto.getPointPassword());
        user.updatePointPassword(pointPasswordUpdateDto.getPointPassword());
    }


}
