package com.spharos.ssgpoint.coupon.presentation;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class StoreManager {

    // 상점명에 따른 상점 코드를 저장하는 맵 상수
    private static final Map<String, String> STORE_PREFIXES;

    static {
        STORE_PREFIXES = new HashMap<>();
        // 각 상점명에 따른 상점 코드를 맵에 할당
        STORE_PREFIXES.put("이마트24", "12345678");
        STORE_PREFIXES.put("이마트everyday", "87654321");
        STORE_PREFIXES.put("신세계백화점", "11223344");
        STORE_PREFIXES.put("SpeedMate", "44332211");
    }

    // 주어진 상점명에 따른 상점 코드를 반환하는 메서드
    public String getStorePrefix(String storeName) {
        return STORE_PREFIXES.get(storeName);
    }

    // 주어진 상점명을 기반으로 쿠폰 번호를 생성하는 메서드
    public String generateCouponNumber(String storeName) {
        String storePrefix = getStorePrefix(storeName);
        // 상점 코드가 없을 경우 예외 발생
        if (storePrefix == null) {
            throw new IllegalArgumentException("발행처: " + storeName);
        }


        Random random = new Random();
        // 랜덤한 12자리 숫자를 생성
        String randomDigits = String.format("%012d", random.nextLong() % 1000000000000L);

        // 상점 코드와 랜덤 숫자를 결합하여 쿠폰 번호 반환
        return storePrefix + randomDigits;
    }

    // 테스트를 위한 메인 메서드
    public static void main(String[] args) {
        StoreManager manager = new StoreManager();
        // 모든 상점명에 대해 쿠폰 번호 생성 및 출력
        for (String storeName : STORE_PREFIXES.keySet()) {
            System.out.println(storeName + " 쿠폰: " + manager.generateCouponNumber(storeName));
        }
    }
}