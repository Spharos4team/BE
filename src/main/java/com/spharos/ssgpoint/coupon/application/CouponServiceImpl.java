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
@Transactional
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;
    private final StoreManager storeManager;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 새로운 쿠폰을 등록합니다.
     *
     * @param couponAdd 쿠폰 생성에 필요한 정보가 담긴 VO.
     */
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
        logger.info("쿠폰 '{}'이(가) 성공적으로 등록되었습니다.", couponAdd.getTitle());
    }

    /**
     * 쿠폰을 삭제합니다.
     *
     * @param couponId 삭제할 쿠폰의 ID.
     * @throws IllegalStateException 쿠폰이 사용자에게 할당되어 있을 때 발생합니다.
     */
    public void deleteCoupon(Long couponId) {
        List<UserCoupon> assignedCoupons = userCouponRepository.findAllByCouponId(couponId);
        if (!assignedCoupons.isEmpty()) {
            logger.warn("사용자에게 할당된 ID {} 쿠폰을 삭제하려고 했습니다.", couponId);
            throw new IllegalStateException("쿠폰이 사용자에게 할당되어 있어 삭제할 수 없습니다");
        }

        couponRepository.deleteById(couponId);
        logger.info("쿠폰 ID {}가 성공적으로 삭제되었습니다.", couponId);
    }

    /**
     * 쿠폰을 사용합니다.
     *
     * @param couponOut 쿠폰 사용에 필요한 정보가 담긴 VO.
     * @return 사용된 쿠폰.
     * @throws CouponNotFoundException 쿠폰을 찾을 수 없을 때 발생합니다.
     */
    public CouponDto useCoupon(CouponOut couponOut) {
        UserCoupon userCoupon = userCouponRepository.findById(couponOut.getCouponId())
                .orElseThrow(() -> new CouponNotFoundException(couponOut.getCouponId()));
        Coupon coupon = couponRepository.findById(userCoupon.getCoupon().getId())
                .orElseThrow(() -> new CouponNotFoundException(couponOut.getCouponId()));
        return new CouponDto(coupon);
    }

    /**
     * 사용 가능한 쿠폰 목록을 가져옵니다.
     *
     * @return 사용 가능한 쿠폰 목록.
     */
    public List<CouponDto> getAvailableCoupons() {
        LocalDate now = LocalDate.now();
        return couponRepository.findAvailableCoupons(now)
                .stream()
                .map(CouponDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 사용자가 가진 쿠폰 목록을 가져옵니다.
     *
     * @param uuid 사용자 UUID.
     * @return 사용자가 가진 쿠폰 목록.
     */
    public List<UserCouponDto> getMyCoupons(String uuid) {
        return userCouponRepository.findAllByUuid(uuid)
                .stream()
                .map(UserCouponDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 만료된 쿠폰 목록을 가져옵니다.
     *
     * @return 만료된 쿠폰 목록.
     */
    public List<CouponDto> getExpiredCoupons() {
        LocalDate now = LocalDate.now();
        return couponRepository.findAllByEndDateBefore(now)
                .stream()
                .map(CouponDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 주어진 ID를 가진 쿠폰을 가져옵니다.
     *
     * @param couponId 쿠폰 ID.
     * @return 해당 ID를 가진 쿠폰.
     * @throws CouponNotFoundException 쿠폰을 찾을 수 없을 때 발생합니다.
     */
    @Override
    public CouponDto getCouponById(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new CouponNotFoundException(couponId));
        return new CouponDto(coupon);
    }

    /**
     * 사용자에게 쿠폰을 할당합니다.
     *
     * @param uuid     사용자 UUID.
     * @param couponId 쿠폰 ID.
     * @throws CouponNotFoundException 쿠폰을 찾을 수 없을 때 발생합니다.
     * @throws IllegalStateException  사용자가 이미 해당 쿠폰을 가지고 있을 때 발생합니다.
     */
    @Override
    public void assignCoupon(String uuid, Long couponId) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new CouponNotFoundException(couponId));

        UserCoupon existingUserCoupon = userCouponRepository.findByUuidAndCouponId(uuid, couponId)
                .orElse(null);
        if (existingUserCoupon != null) {
            logger.warn("사용자가 이미 해당 쿠폰을 가지고 있습니다. 쿠폰 ID: {}, 사용자 UUID: {}",
                    couponId, uuid);
            throw new IllegalStateException("사용자가 이미 해당 쿠폰을 가지고 있습니다.");
        }

        UserCoupon userCoupon = UserCoupon.builder()
                .uuid(uuid)
                .coupon(coupon)
                .build();
        userCouponRepository.save(userCoupon);
        logger.info("쿠폰 ID {}가 사용자 UUID {}에게 할당되었습니다.", couponId, uuid);
    }

}
