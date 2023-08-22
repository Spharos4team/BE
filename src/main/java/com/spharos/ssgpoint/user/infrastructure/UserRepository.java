package com.spharos.ssgpoint.user.infrastructure;

import com.spharos.ssgpoint.user.domain.User;

import java.util.Optional;

public interface UserRepository  {
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByUuid(String UUID);
    Optional<User> findByBarCode(String barCode);
}
