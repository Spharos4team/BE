package com.spharos.ssgpoint.coupon.presentation;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class StoreManager {

    private static final Map<String, String> STORE_PREFIXES;

    static {
        STORE_PREFIXES = new HashMap<>();
        STORE_PREFIXES.put("이마트24", "12345678");
        STORE_PREFIXES.put("이마트everyday", "87654321");
        STORE_PREFIXES.put("신세계백화점", "11223344");
        STORE_PREFIXES.put("SpeedMate", "44332211");
    }

    public String getStorePrefix(String storeName) {
        return STORE_PREFIXES.get(storeName);
    }

    public String generateCouponNumber(String storeName) {
        String storePrefix = getStorePrefix(storeName);
        if (storePrefix == null) {
            throw new IllegalArgumentException("발행처: " + storeName);
        }

        if (storePrefix.length() != 8) {
            throw new IllegalArgumentException("스토어 코드는 8자리여야 합니다.");
        }

        Random random = new Random();
        String randomDigits = String.format("%012d", random.nextLong() % 1000000000000L);

        return storePrefix + randomDigits;
    }

    public static void main(String[] args) {
        StoreManager manager = new StoreManager();
        for (String storeName : STORE_PREFIXES.keySet()) {
            System.out.println(storeName + " 쿠폰: " + manager.generateCouponNumber(storeName));
        }
    }
}
