package com.spharos.ssgpoint.club.domain;

import com.spharos.ssgpoint.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity     // JPA의 엔티티 클래스임을 지정
@Getter // Getter 메소드 자동 생성
@Builder    // 빌더 패턴 적용
@NoArgsConstructor  // 인자가 없는 생성자를 생성
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자를 생성
public class UserClub { // User와 Club의 다대다 관계를 위한 클래스

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동생성
    private Long id;    // PK

    @ManyToOne(fetch = FetchType.LAZY)  // 지연로딩
    private User UUID;  // User클래스의 PK를 외래키로 지정

    @ManyToOne(fetch = FetchType.LAZY)  // 지연로딩
    private ClubList clubList;  // Club클래스의 PK를 외래키로 지정

}