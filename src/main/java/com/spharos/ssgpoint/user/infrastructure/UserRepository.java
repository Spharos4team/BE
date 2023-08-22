package com.spharos.ssgpoint.user.infrastructure;

import com.spharos.ssgpoint.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByUuid(String UUID);
    Optional<User> findByBarCode(String barCode);
}
