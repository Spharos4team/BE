package com.spharos.ssgpoint.user.presentation;

import com.spharos.ssgpoint.config.security.JwtTokenProvider;
import com.spharos.ssgpoint.user.application.UserService;
import com.spharos.ssgpoint.user.dto.*;
import com.spharos.ssgpoint.user.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Environment env;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/user")
    public void createUser(@RequestBody UserSignUpIn userSignUpIn) {
        log.info("INPUT Object Data is : {}" , userSignUpIn);
        ModelMapper modelMapper = new ModelMapper();
        UserSignUpDto userSignUpDto = modelMapper.map(userSignUpIn, UserSignUpDto.class);

        userService.createUser(userSignUpDto);
    }

    @GetMapping("/user/{UUID}")
    public ResponseEntity<UserGetOut> getUserByUUID(@PathVariable String UUID) {
        log.info("INPUT UUID is : {}" , UUID);
        UserGetDto userGetDto = userService.getUserByUUID(UUID);
        log.info("OUTPUT userGetDto is : {}" , userGetDto);
        UserGetOut userGetOut = UserGetOut.builder()
                .loginId(userGetDto.getLoginId())
                .name(userGetDto.getName())
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
    @PutMapping("/user/{UUID}")
    public ResponseEntity<String> updateUser(@PathVariable String UUID,@RequestBody UserUpdateIn userUpdateIn){
        ModelMapper modelMapper = new ModelMapper();
        UserUpdateDto userUpdateDto = modelMapper.map(userUpdateIn,UserUpdateDto.class);
        userService.updateUserInfo(UUID,userUpdateDto);
        return ResponseEntity.ok("회원정보 변경 성공");
    }

    /**
     * 회원가입 시 아이디 중복 확인
     */
    @GetMapping("/user/check-loginId")
    public ResponseEntity<String> validateDuplicateLoginId(@RequestBody UserSignUpIn userSignUpIn){
        log.info("INPUT Object Data is : {}" , userSignUpIn);
        UserSignUpDto userSignUpDto = UserSignUpDto.builder()
                .loginId(userSignUpIn.getLoginId())
                .build();
        userService.validateDuplicateLoginId(userSignUpDto);
        return ResponseEntity.ok("사용 가능한 아이디");
    }


    /**
     * 비밀번호 변경
     */
    @PutMapping("/user/password/{UUID}")
    public ResponseEntity<String> updatePassword(@PathVariable String UUID, @RequestBody PasswordUpdateInfo passwordUpdateInfo) {
            ModelMapper modelMapper = new ModelMapper();
            PasswordUpdateDto passwordUpdateDto = modelMapper.map(passwordUpdateInfo,PasswordUpdateDto.class);
            userService.updatePassword(UUID,passwordUpdateDto);
            return ResponseEntity.ok("비밀번호 변경 성공");
    }

    /**
     * 광고정보 수신관리 조회
     */
    @GetMapping("/user/term/{UUID}")
    public ResponseEntity<Map<String,Boolean>> getTerm(@PathVariable String UUID) {
        Map<String, Boolean> term = userService.getTerm(UUID);
        return ResponseEntity.ok(term);
    }

    /**
     * 광고정보 수신관리 업데이트
     */
    @PutMapping("/user/term/{UUID}")
    public ResponseEntity<Map<String,Boolean>> updateTerm(@PathVariable String UUID,@RequestBody TermUpdateInfo termUpdateInfo) {
        ModelMapper modelMapper = new ModelMapper();
        TermUpdateDto termUpdateDto = modelMapper.map(termUpdateInfo, TermUpdateDto.class);
        Map<String, Boolean> term = userService.updateTerm(UUID, termUpdateDto);
        return ResponseEntity.ok(term);
    }
}






