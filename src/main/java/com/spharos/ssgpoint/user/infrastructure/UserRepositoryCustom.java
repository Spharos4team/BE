package com.spharos.ssgpoint.user.infrastructure;

import com.querydsl.core.Tuple;

import java.util.List;

public interface UserRepositoryCustom {
    Integer findSavePointByUUID(String uuid);
    Integer findUsePointByUUID(String uuid);
    Long findVisitDateByReceipt(String uuid);
    Integer findTotalPointByReceipt(String uuid);
    List<Tuple>  findCountListTop3ByUUID(String uuid);
    List<Tuple> findSumListTop3ByUUID(String uuid);
}
