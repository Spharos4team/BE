package com.spharos.ssgpoint.user.infrastructure;

import com.querydsl.core.Tuple;
import com.spharos.ssgpoint.user.dto.FrequentBrandTop3Dto;

import java.util.List;

public interface UserRepositoryCustom {
    Integer findSavePointByUUID(String uuid);
    Integer findUsePointByUUID(String uuid);
    Long findVisitDateByReceipt(String uuid);
    Integer findTotalPointByReceipt(String uuid);
    List<Tuple>  findListTop3ByUUID(String uuid);
}
