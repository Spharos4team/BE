package com.spharos.ssgpoint.auth;


import com.spharos.ssgpoint.auth.vo.AuthenticationRequest;
import com.spharos.ssgpoint.auth.vo.AuthenticationResponse;
import com.spharos.ssgpoint.user.dto.UserSignUpDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(
            @RequestBody UserSignUpDto userSignUpDto
    ) {
        return ResponseEntity.ok(authenticationService.signup(userSignUpDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest authenticationRequest,
            HttpServletResponse response
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest,response));
    }


    /*@PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }*/
}
