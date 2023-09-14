package com.spharos.ssgpoint.auth;


import com.spharos.ssgpoint.auth.vo.AuthenticationRequest;
import com.spharos.ssgpoint.auth.vo.AuthenticationResponse;
import com.spharos.ssgpoint.auth.vo.RefreshTokenVo;
import com.spharos.ssgpoint.user.dto.user.UserSignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
//@CrossOrigin(allowedHeaders = "*")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(
            @RequestBody UserSignUpDto userSignUpDto
    ) {
        authenticationService.signup(userSignUpDto);
        return ResponseEntity.ok("회원가입 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest authenticationRequest
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @RequestBody RefreshTokenVo refreshTokenVo
    ) {
        authenticationService.logout(refreshTokenVo.getRefreshToken());
        return ResponseEntity.ok("로그아웃 완료");
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenVo refreshTokenVo
    )  {
        return ResponseEntity.ok( authenticationService.refreshToken(refreshTokenVo.getRefreshToken()));
    }
}
