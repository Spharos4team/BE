package com.spharos.ssgpoint.pointgift.presentation;

import com.spharos.ssgpoint.pointgift.domain.PointGift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointGiftRepository extends JpaRepository<PointGift, Long> {

}
