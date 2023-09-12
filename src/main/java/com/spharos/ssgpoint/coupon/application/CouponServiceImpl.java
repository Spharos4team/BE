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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;
    private final StoreManager storeManager;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Transactional
    public void registerCoupon(CouponAdd couponAdd) {
        String generatedCouponNumber = storeManager.generateCouponNumber(couponAdd.getStore());
        Coupon coupon = Coupon.builder()
                .title(couponAdd.getTitle())
                .description(couponAdd.getDescription())
                .startDate(couponAdd.getStartDate())
                .endDate(couponAdd.getEndDate())
                .store(couponAdd.getStore())
                .image(couponAdd.getImage())
                .content(couponAdd.getContent())
                .number(generatedCouponNumber)
                .build();

        couponRepository.save(coupon);
        logger.info("Coupon with title {} registered successfully.", couponAdd.getTitle());
    }

    @Transactional
    public void deleteCoupon(Long couponId) {
        List<UserCoupon> assignedCoupons = userCouponRepository.findAllByCouponId(couponId);
        if (!assignedCoupons.isEmpty()) {
            logger.warn("Attempt to delete coupon with ID {} that is assigned to users.", couponId);
            throw new IllegalStateException("Cannot delete coupon that is assigned to users.");
        }

        couponRepository.deleteById(couponId);
        logger.info("Coupon with ID {} deleted successfully.", couponId);
    }

    public CouponDto useCoupon(CouponOut couponOut) {
        UserCoupon userCoupon = userCouponRepository.findById(couponOut.getCouponId())
                .orElseThrow(() -> new CouponNotFoundException(couponOut.getCouponId()));
        Coupon coupon = couponRepository.findById(userCoupon.getCoupon().getId())
                .orElseThrow(() -> new CouponNotFoundException(couponOut.getCouponId()));
        return new CouponDto(coupon);
    }


    public List<CouponDto> getAvailableCoupons() {
        LocalDate now = LocalDate.now();
        return couponRepository.findAvailableCoupons(now)
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
        LocalDate now = LocalDate.now();
        return couponRepository.findAllByEndDateBefore(now)
                .stream()
                .map(CouponDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public CouponDto getCouponById(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new CouponNotFoundException(couponId));
        return new CouponDto(coupon);
    }

    @Transactional
    @Override
    public void assignCoupon(String uuid, Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new CouponNotFoundException(couponId));

        UserCoupon existingUserCoupon = userCouponRepository.findByUuidAndCouponId(uuid, couponId)
                .orElse(null);
        if (existingUserCoupon != null) {
            logger.warn("User with UUID {} already has the coupon with ID {}", uuid, couponId);
            throw new IllegalStateException("User already has this coupon.");
        }

        UserCoupon userCoupon = UserCoupon.builder()
                .uuid(uuid)
                .coupon(coupon)
                .build();
        userCouponRepository.save(userCoupon);
        logger.info("Coupon with ID {} has been assigned to user with UUID {}", couponId, uuid);
    }

}
