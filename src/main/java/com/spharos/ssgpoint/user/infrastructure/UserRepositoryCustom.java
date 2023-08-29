package com.spharos.ssgpoint.user.infrastructure;

import com.spharos.ssgpoint.point.domain.Point;

public interface UserRepositoryCustom {
    Point findPointByUUID(String uuid);

}
