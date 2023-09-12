package com.spharos.ssgpoint.pointgift.infrastructure;

import com.spharos.ssgpoint.point.infrastructure.PointRepositoryCustom;
import com.spharos.ssgpoint.pointgift.domain.PointGift;
import com.spharos.ssgpoint.pointgift.dto.PointGiftListDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointGiftRepository extends JpaRepository<PointGift, Long>, PointGiftRepositoryCustom {

    List<PointGift> findByUUID(String UUID);




}
