package com.spharos.ssgpoint.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spharos.ssgpoint.auth.vo.AuthenticationRequest;
import com.spharos.ssgpoint.auth.vo.AuthenticationResponse;
import com.spharos.ssgpoint.config.security.JwtTokenProvider;
import com.spharos.ssgpoint.user.domain.User;
import com.spharos.ssgpoint.user.dto.UserSignUpDto;
import com.spharos.ssgpoint.user.infrastructure.UserRepository;
import jakarta.persistence.PostPersist;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;


    @Transactional
    public AuthenticationResponse signup(UserSignUpDto userSignUpDto) {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        log.info("userSignUpDto name={}", userSignUpDto.getName() );
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
        user.hashPassword(user.getPassword());
        log.info("user info={}", user.getName() );
        User saveUser = userRepository.save(user);

        String pointBardCode = createPointBardCode(user);
        log.info("pointBardCode is : {}" , pointBardCode);

        String validateBarcode = validateBarcode(pointBardCode);
        log.info("validateBarcode is : {}" , validateBarcode);

        saveUser.generateBarcode(validateBarcode);

        //16자리랑 12자리 비교랑 차이난다 정규식 뒤에 4->8->12자리로
        //userid칸을 늘리단 8-> 11자리로
        //10억개가 넘으면 일련번호 비어있는걸 찾아서 사용
        // 5~8 지역, 9~10 성별

        //저장하고 userSignUpDto로 uuid 조회해서 user 찾아 userid을 뽑아서 바코드 만들기?
        //아니면 전체 조회해서 id 제일큰거 +1
        // 그런데 사람 너무 많으면 너무많이 조회하는데
        // 그런데 회원1억명 넘는거 떄문에 중복검사하는건데 너무 많이 조회하는게 아닌가
        // 그러면 1억게 다 조회하는데...


        return AuthenticationResponse.builder().build();

    }

    /**
     * 전체 바코드 숫자 만들기
     */

    private String createPointBardCode(User user) {
        String generateBarcode = generateBarcode();
        String formattedId;

        if (user.getId() > 999999999) {
            long l = user.getId() - 99999999;
            formattedId =String.valueOf(1000000000-l);  // 아니면 id - 1억해서 ?
        } else {
            formattedId = String.format("%09d", user.getId());
        }

        return generateBarcode+formattedId;

    }
    /**
     * 바코드 앞에 7자리 일단 랜덤숫자는 222 테스트할려고 고정
     * @return
     */
    private String generateBarcode() {
        String fixNumber = "9350";
        Random random = new Random();
        String randomDigits = String.format("%03d", random.nextInt(1000));
        return fixNumber + 222;
    }

    /**
     * 바코드만들고 만든거 중복 검사
     */
    private String validateBarcode(String checkBarcode) {
        Optional<User> byBarCode = userRepository.findByBarCode(checkBarcode);
        if (byBarCode.isPresent()){
            //중복인경우
            String substring = checkBarcode.substring(4, 7);
            int plus = Integer.parseInt(substring) + 1;
            log.info("plus={}",plus );
            String s = checkBarcode.substring(0, 4) + String.format("%03d", plus) + checkBarcode.substring(7);
            return validateBarcode(s); // 중복된 경우 재귀 호출하여 검사
        }
        else{
            return checkBarcode;
        }
    }



    /**
     * 로그인 인증
     * @param authenticationRequest json 요청
     * @param response 응답헤더
     * @return
     */

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getLoginId(),
                        authenticationRequest.getPassword()
                )
        );

        User user = userRepository.findByLoginId(authenticationRequest.getLoginId()).orElseThrow();
        log.info("user is : {}" , user);
        String accessToken = jwtTokenProvider.generateToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        response.setHeader("authorization", "bearer "+ accessToken);
        response.setHeader("refreshToken", "bearer "+ refreshToken);

        log.info("accessToken is : {}" , accessToken);
        log.info("refreshToken is : {}" , refreshToken);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


}
