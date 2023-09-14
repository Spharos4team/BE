package com.spharos.ssgpoint.pointgift.infrastructure;

import com.spharos.ssgpoint.point.infrastructure.PointRepositoryCustom;
import com.spharos.ssgpoint.pointgift.domain.PointGift;
import com.spharos.ssgpoint.pointgift.dto.PointGiftListDto;
import com.spharos.ssgpoint.pointgift.dto.PointGiftMessageDto;
import com.spharos.ssgpoint.receipt.dto.ReceiptGetDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PointGiftRepository extends JpaRepository<PointGift, Long>, PointGiftRepositoryCustom {

    List<PointGift> findByUUID(String UUID);

    @Query("select new com.spharos.ssgpoint.pointgift.dto.PointGiftMessageDto(p.message) from PointGift p where p.id = :id")
    PointGiftMessageDto findGiftMessageById(Long id);


}
