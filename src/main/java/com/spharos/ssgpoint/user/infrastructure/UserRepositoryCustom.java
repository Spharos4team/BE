package com.spharos.ssgpoint.user.infrastructure;

import java.util.List;

public interface UserRepositoryCustom {
    Integer findSavePointByUUID(String uuid);
    Integer findUsePointByUUID(String uuid);
    Integer findVisitDateByReceipt(String uuid);
}
