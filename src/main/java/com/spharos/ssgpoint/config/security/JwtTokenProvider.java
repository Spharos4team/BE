package com.spharos.ssgpoint.config.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final Environment env;

    /**
     * 2
     * @param token
     * @param claimsResolver jwt토큰에서 추출한 정보를 어떻게 처리할지 결정하는 함수
     * @return jwt토큰에서 모든 클레임(클레임은 토큰에 담긴 정보 의미 ) 추출한 다음 claimsResolver함수를 처리해 결과 반환
     *
     */

    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /** 3
     * @param token
     * 주어진 JWT 토큰에서 모든 클레임을 추출하여 반환합니다.
     * 토큰의 서명을 확인하기 위해 사용할 서명 키(getSigningKey())를 설정하고 토큰을 파싱하여 클레임들을 추출합니다.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    /** 4
     *  JWT 토큰의 서명을 확인하기 위해 사용할 서명 키를 생성하여 반환합니다
     */
    private Key getSigningKey() {
        byte[] keyByte = Decoders.BASE64.decode(env.getProperty("JWT.SECRET_KEY"));
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(Map.of(), userDetails);
    }
    /**
     * 1.
     * @param extractClaims 생성할 jwt토큰의 클레임 정보
     * @param userDetails 사용자 정보
     * @return 클레임 정보와 사용자 정보를 기반으로 jwt 토큰 생성
     * 클레임 정보, 사용자 ID, 생성 시간, 만료 시간 등을 설정하고, 서명 알고리즘과 서명 키를 사용하여 토큰을 생성합니다.
     */
    public String generateToken(Map<String,Object> extractClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername()) //UUID 넘어온다
                .setIssuedAt(new java.util.Date(System.currentTimeMillis())) //생성일
                .setExpiration(new java.util.Date(System.currentTimeMillis() + env.getProperty("JWT.EXPIRATION_TIME",Long.class))) //만료일
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // 알고리즘
                .compact();
    }

    /**
     * 5
     * @param token 검증할 토큰
     * @param userDetails 사용자 정보
     * @return jwt토큰 유효성 검사
     * 토큰에서 추출한 UUID가 userDetails에서 가져온 사용자 id가 일치하며
     * 토큰이 만료되지 않은경우 토큰 유효
     */
    public boolean validateToken(String token, UserDetails userDetails){
        final String UUID = getUUID(token);
        return (UUID.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    /** 6
     *  주어진 JWT 토큰에서 UUID(사용자 ID) 클레임을 추출하여 반환합니다.
     */
    public String getUUID(String token) {
        return extractClaims(token,Claims::getSubject);
    }

    /** 7
     * 만료 비교
     */
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new java.util.Date())
                ;    }

    /** 8
     * 주어진 JWT 토큰에서 만료 시간 클레임을 추출하여 반환합니다.
     */
    private Date extractExpiration(String token) {
        return extractClaims(token,Claims::getExpiration);
    }



}