package com.spharos.ssgpoint.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spharos.ssgpoint.auth.vo.AuthenticationRequest;
import com.spharos.ssgpoint.auth.vo.AuthenticationResponse;
import com.spharos.ssgpoint.config.security.JwtTokenProvider;
import com.spharos.ssgpoint.term.domain.UserTermList;
import com.spharos.ssgpoint.term.infrastructure.TermRepository;
import com.spharos.ssgpoint.token.domain.RefreshToken;
import com.spharos.ssgpoint.token.infrastructure.RefreshTokenRepository;
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
import java.util.Map;
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
    private final TermRepository termRepository;
    private final RefreshTokenRepository tokenRepository;

    @Transactional
    public AuthenticationResponse signup(UserSignUpDto userSignUpDto) {
        UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        log.info("userSignUpDto name={}", userSignUpDto.getName() );
        Map<String, Boolean> term = userSignUpDto.getTerm();
        UserTermList userTermList = new UserTermList();
        UserTermList termList = userTermList.builder().termJson(term).build();

        User user = User.builder()
                .loginId(userSignUpDto.getLoginId())
                .uuid(uuidString)
                .name(userSignUpDto.getName())
                .password(userSignUpDto.getPassword())
                .email(userSignUpDto.getEmail())
                .phone(userSignUpDto.getPhone())
                .address(userSignUpDto.getAddress())
                .term(termList)
                .status(1)

                .build();

        String jwtToken = jwtTokenProvider.generateToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        user.hashPassword(user.getPassword());
        User saveUser = userRepository.save(user);

        //저장후 바코드 생성
        String pointBardCode = createPointBardCode(user);
        String validateBarcode = validateBarcode(pointBardCode);
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


        return AuthenticationResponse.builder()
                        .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();




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
     *
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

        User user = userRepository.findByLoginId(authenticationRequest.getLoginId()).orElseThrow(()-> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        log.info("user is : {}" , user);
        String accessToken = jwtTokenProvider.generateToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);
        String uuid = jwtTokenProvider.getUUID(accessToken);

        response.setHeader("authorization", "bearer "+ accessToken);
        response.setHeader("refreshToken", "bearer "+ refreshToken);

        log.info("uuid is : {}" , uuid);
        log.info("accessToken is : {}" , accessToken);
        log.info("refreshToken is : {}" , refreshToken);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
  /*  public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String UUID;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        UUID = jwtTokenProvider.getUUID(refreshToken);
        if (UUID != null) {
            User user = this.userRepository.findByUuid(UUID)
                    .orElseThrow();
            if (jwtTokenProvider.validateToken(refreshToken, user)) {
                var accessToken = jwtTokenProvider.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
    private void saveUserToken(User user, String jwtToken) {
        var token = RefreshToken.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUuid());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }*/


}
