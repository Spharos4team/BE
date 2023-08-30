package com.spharos.ssgpoint.user.infrastructure;

import java.util.List;

public interface UserRepositoryCustom {
    List<Integer> findPointByUUID(String uuid);

}
