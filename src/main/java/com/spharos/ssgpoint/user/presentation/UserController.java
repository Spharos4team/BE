package com.spharos.ssgpoint.user.presentation;

import com.spharos.ssgpoint.user.application.UserService;
import com.spharos.ssgpoint.user.dto.*;
import com.spharos.ssgpoint.user.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public void createUser(@RequestBody UserSignUpIn userSignUpIn) {
        log.info("INPUT Object Data is : {}" , userSignUpIn);
        UserSignUpDto userSignUpDto = UserSignUpDto.builder()
                .loginId(userSignUpIn.getLoginId())
                .password(userSignUpIn.getPassword())
                .name(userSignUpIn.getName())
                .email(userSignUpIn.getEmail())
                .phone(userSignUpIn.getPhone())
                .address(userSignUpIn.getAddress())
                .build();
        userService.createUser(userSignUpDto);
    }

    @GetMapping("/user/{UUID}")
    public ResponseEntity<UserGetOut> getUserByUUID(@PathVariable String UUID) {
        log.info("INPUT UUID is : {}" , UUID);
        UserGetDto userGetDto = userService.getUserByUUID(UUID);
        log.info("OUTPUT userGetDto is : {}" , userGetDto);
        UserGetOut userGetOut = UserGetOut.builder()
                .loginId(userGetDto.getLoginId())
                .userName(userGetDto.getUserName())
                .email(userGetDto.getEmail())
                .phone(userGetDto.getPhone())
                .address(userGetDto.getAddress())
                .build();
        log.info("OUTPUT userGetOut is : {}" , userGetOut);
        return ResponseEntity.ok(userGetOut);
    }

    /**
     * 회원 수정
     */
    @PutMapping("/user/update/{UUID}")
    public void updateUser(@PathVariable String UUID,@RequestBody UserInfoIn userInfoIn){
        UserUpdateDto userUpdateDto = UserUpdateDto.builder()
                .address(userInfoIn.getAddress())
                .email(userInfoIn.getEmail())
                .build();
        userService.updateUserInfo(UUID,userUpdateDto);
    }
    /**
     * 회원가입 시 아이디 중복 확인
     */
    @GetMapping("/user/check-loginId")
    public void validateDuplicateLoginId(@RequestBody UserSignUpIn userSignUpIn){
        log.info("INPUT Object Data is : {}" , userSignUpIn);
        UserSignUpDto userSignUpDto = UserSignUpDto.builder()
                .loginId(userSignUpIn.getLoginId())
                .build();
        userService.validateDuplicateLoginId(userSignUpDto);
    }

    /**
     * 비밀번호 찾기인데 하면 리셋해라함
     */
    @PutMapping("/user/reset-password/{UUID}")
    public void resetPassword(@PathVariable String UUID,@RequestBody PasswordResetInfo passwordResetInfo){
        PasswordResetDto passwordUpdateDto = PasswordResetDto.builder()
                .password(passwordResetInfo.getPassword())
                .build();
        userService.resetPassword(UUID,passwordUpdateDto);
    }
    /**
     * 비밀번호 변경
     */
    @PutMapping("/user/update-password/{UUID}")
    public void updatePassword(@PathVariable String UUID,@RequestBody PasswordUpdateInfo passwordUpdateInfo){
        PasswordUpdateDto passwordUpdateDto = PasswordUpdateDto.builder()
                .currentPassword(passwordUpdateInfo.getCurrentPassword())
                .updatePassword(passwordUpdateInfo.getUpdatePassword())
                .build();
        userService.updatePassword(UUID,passwordUpdateDto);
    }
}
