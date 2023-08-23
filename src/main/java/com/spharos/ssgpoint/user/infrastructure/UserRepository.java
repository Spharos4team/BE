package com.spharos.ssgpoint.user.infrastructure;

import com.spharos.ssgpoint.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByUuid(String UUID);
    Optional<User> findByBarCode(String barCode);

   /* @Query("SELECT utl.term_json FROM User u JOIN u.term utl WHERE u.id = :UUID")
    String findTermJsonByUserId(@Param("UUID") String UUID);*/
}
