package com.spharos.ssgpoint.user.presentation;

import com.spharos.ssgpoint.config.security.JwtTokenProvider;
import com.spharos.ssgpoint.user.application.UserService;
import com.spharos.ssgpoint.user.dto.password.PasswordUpdateDto;
import com.spharos.ssgpoint.user.dto.shoppinghistory.*;
import com.spharos.ssgpoint.user.dto.user.*;
import com.spharos.ssgpoint.user.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    /**
     * 회원 soft delete
     */
    @PutMapping("/user/soft-delete/{UUID}")
    public ResponseEntity<String> changeStatus(@PathVariable String UUID) {
        userService.softDeleteUser(UUID);
        return ResponseEntity.ok("회원탈퇴 성공");
    }
    /**
     * 사용가능 포인트 조회
     */
    @GetMapping("/user/point/{UUID}")
    public ResponseEntity<PointGetDto> getPoint(@PathVariable String UUID) {
        PointGetDto pointGetDto = userService.getPoint(UUID);
        return ResponseEntity.ok(pointGetDto);
    }
    /**
     * 신세계포인트 이용 - 적립부분
     */
    @GetMapping("/user/save-point/{UUID}")
    public ResponseEntity<UserSavePointDto> getSavePoint(@PathVariable String UUID) {
        UserSavePointDto userSavePointDto = userService.getSavePoint(UUID);
        return ResponseEntity.ok(userSavePointDto);
    }
    /**
     * 신세계포인트 이용 - 사용부분
     */
    @GetMapping("/user/use-point/{UUID}")
    public ResponseEntity<UserUsePointDto> getUsePoint(@PathVariable String UUID) {
        UserUsePointDto userUsePointDto = userService.getUsePoint(UUID);
        return ResponseEntity.ok(userUsePointDto);
    }

    /**
     * 방문 일수 - 바코드 테이블 count
     */
    @GetMapping("/user/visit/{UUID}")
    public ResponseEntity<VisitedCountDto> countDate(@PathVariable String UUID){
        VisitedCountDto visitedCount = userService.getVisitedCount(UUID);
        return ResponseEntity.ok(visitedCount);
    }
    /**
     * 구매 금액
     */
    @GetMapping("/user/total-point/{UUID}")
    public ResponseEntity<TotalPointDtoByReceipt> totalPoint(@PathVariable String UUID){
        TotalPointDtoByReceipt totalPoint = userService.getTotalPoint(UUID);
        return ResponseEntity.ok(totalPoint);
    }

    @GetMapping("/user/top3/{UUID}")
    public ResponseEntity<List<FrequentBrandTop3SumDto>> totaltp3Point(@PathVariable String UUID){
        List<FrequentBrandTop3SumDto> frequentBrandTop3Sum = userService.getFrequentBrandTop3Sum(UUID);
        return ResponseEntity.ok(frequentBrandTop3Sum);
    }


    @GetMapping("/shopping-history/{UUID}")
    public ResponseEntity<UserCompositeDto> getUserData(@PathVariable String UUID) {
        UserCompositeDto userCompositeDto = new UserCompositeDto();

        userCompositeDto.setPointGetDto(userService.getPoint(UUID));
        userCompositeDto.setUserSavePointDto(userService.getSavePoint(UUID));
        userCompositeDto.setUserUsePointDto(userService.getUsePoint(UUID));
        userCompositeDto.setVisitedCountDto(userService.getVisitedCount(UUID));
        userCompositeDto.setTotalPointDtoByReceipt(userService.getTotalPoint(UUID));
        userCompositeDto.setFrequentBrandTop3Dto(userService.getFrequentBrandTop3Count(UUID));
        userCompositeDto.setFrequentBrandTop3SumDto(userService.getFrequentBrandTop3Sum(UUID));
        return ResponseEntity.ok(userCompositeDto);
    }





}






