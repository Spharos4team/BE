package com.spharos.ssgpoint.token.infrastructure;

import com.spharos.ssgpoint.token.domain.RefreshToken;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long>{
    @Query("")
    List<RefreshToken> findAllValidateTokenByUuidr(String UUID);
}
