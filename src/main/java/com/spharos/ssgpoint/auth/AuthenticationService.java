package com.spharos.ssgpoint.auth;

import com.spharos.ssgpoint.auth.vo.AuthenticationRequest;
import com.spharos.ssgpoint.auth.vo.AuthenticationResponse;
import com.spharos.ssgpoint.config.security.JwtTokenProvider;
import com.spharos.ssgpoint.user.domain.User;
import com.spharos.ssgpoint.user.dto.UserSignUpDto;
import com.spharos.ssgpoint.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

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
        //todo: 바코드 추가해야함



        userRepository.save(user);

        String JwtToken = jwtTokenProvider.generateToken(user);
        log.info("JwtToken is : {}" , JwtToken);
        return AuthenticationResponse.builder()
                .token(JwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getLoginId(),
                        authenticationRequest.getPassword()
                )
        );

        User user = userRepository.findByLoginId(authenticationRequest.getLoginId()).orElseThrow();

        String JwtToken = jwtTokenProvider.generateToken(user);
        log.info("JwtToken is : {}" , JwtToken);
        return AuthenticationResponse.builder()
                .token(JwtToken)
                .build();
    }
}
