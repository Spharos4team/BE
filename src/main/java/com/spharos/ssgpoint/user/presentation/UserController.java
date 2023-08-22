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

@RestController
@RequestMapping("/api/v1")
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Environment env;

    @PostMapping("/user")
    public void createUser(@RequestBody UserSignUpIn userSignUpIn) {
        log.info("INPUT Object Data is : {}" , userSignUpIn);
        ModelMapper modelMapper = new ModelMapper();
        UserSignUpDto userSignUpDto = modelMapper.map(userSignUpIn,UserSignUpDto.class);
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
     * 회원 정보 수정
     */

    @PutMapping("/user")
    public void updateUser(@PathVariable String UUID,@RequestBody UserUpdateIn userUpdateIn){
        ModelMapper modelMapper = new ModelMapper();
        UserUpdateDto userUpdateDto = modelMapper.map(userUpdateIn,UserUpdateDto.class);
        userService.updateUserInfo(UUID,userUpdateDto);
    }
    /**
     * 회원가입 시 아이디 중복 확인
     */
    @GetMapping("/user/check-loginId")
    public void validateDuplicateLoginId(@RequestBody UserSignUpIn userSignUpIn){
        log.info("INPUT Object Data is : {}" , userSignUpIn);
        ModelMapper modelMapper = new ModelMapper();
        UserSignUpDto userSignUpDto = modelMapper.map(userSignUpIn,UserSignUpDto.class);
        userService.validateDuplicateLoginId(userSignUpDto);
    }


    /**
     * 비밀번호 변경
     */
    /*@PutMapping("/user/password/{UUID}")
    public void updatePassword(@PathVariable String UUID,@RequestBody PasswordUpdateInfo passwordUpdateInfo){
        ModelMapper modelMapper = new ModelMapper();
        PasswordUpdateDto passwordUpdateDto = modelMapper.map(passwordUpdateInfo,PasswordUpdateDto.class);
        userService.updatePassword(UUID,passwordUpdateDto);
    }*/
  /*  @PutMapping("/user/password/{UUID}")
    public ResponseEntity<String> updatePassword(@PathVariable String UUID, @RequestBody PasswordUpdateInfo passwordUpdateInfo) {
        ModelMapper modelMapper = new ModelMapper();
        PasswordUpdateDto passwordUpdateDto = modelMapper.map(passwordUpdateInfo, PasswordUpdateDto.class);

        boolean isUpdated = userService.updatePassword(UUID, passwordUpdateDto);

        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body("Password updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Password update failed.");
        }
    }
*/


    /**
     *  포인트 비밀번호 초기설정+변경
     */
    @PutMapping("/user/point/{UUID}")
    public void updatePointPassword(@PathVariable String UUID,@RequestBody PointPasswordUpdateInfo pointPasswordUpdateInfo){
        ModelMapper modelMapper = new ModelMapper();
        PointPasswordUpdateDto pointPasswordUpdateDto = modelMapper.map(pointPasswordUpdateInfo,PointPasswordUpdateDto.class);
        userService.updatePointPassword(UUID,pointPasswordUpdateDto);
    }
}
