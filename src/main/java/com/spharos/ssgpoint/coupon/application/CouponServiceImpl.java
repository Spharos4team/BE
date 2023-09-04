package com.spharos.ssgpoint.coupon.application;

import com.spharos.ssgpoint.coupon.domain.Coupon;
import com.spharos.ssgpoint.coupon.domain.UserCoupon;
import com.spharos.ssgpoint.coupon.dto.CouponDto;
import com.spharos.ssgpoint.coupon.dto.UserCouponDto;
import com.spharos.ssgpoint.coupon.exception.CouponNotFoundException;
import com.spharos.ssgpoint.coupon.infrastructure.CouponRepository;
import com.spharos.ssgpoint.coupon.infrastructure.UserCouponRepository;
import com.spharos.ssgpoint.coupon.vo.CouponAdd;
import com.spharos.ssgpoint.coupon.vo.CouponOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;
    private final StoreManager storeManager;

    public void registerCoupon(CouponAdd couponAdd) {
        Coupon coupon = Coupon.builder()
                .title(couponAdd.getTitle())
                .description(couponAdd.getDescription())
                .startDate(couponAdd.getStartDate())
                .endDate(couponAdd.getEndDate())
                .store(couponAdd.getStore())
                .image(couponAdd.getImage())
                .content(couponAdd.getContent())
                .build();

        couponRepository.save(coupon);
    }

    public void deleteCoupon(Long couponId) {
        couponRepository.deleteById(couponId);
    }

    public void useCoupon(CouponOut couponOut) {
        UserCoupon userCoupon = userCouponRepository.findById(couponOut.getCouponId())
                .orElseThrow(() -> new CouponNotFoundException(couponOut.getCouponId()));
        userCoupon.use();
        userCouponRepository.save(userCoupon);
    }

    public List<CouponDto> getAvailableCoupons() {
        LocalDate now = LocalDate.now();  // java.time.LocalDate로 변경
        return couponRepository.findAvailableCoupons(now, now)
                .stream()
                .map(CouponDto::new)
                .collect(Collectors.toList());
    }

    public List<UserCouponDto> getMyCoupons(String uuid) {
        return userCouponRepository.findAllByUuid(uuid)
                .stream()
                .map(UserCouponDto::new)
                .collect(Collectors.toList());
    }

    public List<CouponDto> getExpiredCoupons() {
        LocalDate now = LocalDate.now();  // java.time.LocalDate로 변경
        return couponRepository.findAllByEndDateBefore(now)
                .stream()
                .map(CouponDto::new)
                .collect(Collectors.toList());
    }
}
