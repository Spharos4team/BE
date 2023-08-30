package com.spharos.ssgpoint.coupon.application;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class StoreManager {

    private final Map<String, String> STORE_PREFIXES = new HashMap<>();

    public StoreManager() {
        STORE_PREFIXES.put("이마트24", "12345678");
        STORE_PREFIXES.put("이마트everyday", "87654321");
        STORE_PREFIXES.put("신세계백화점", "11223344");
        STORE_PREFIXES.put("SpeedMate", "44332211");
    }

    public String getStorePrefix(String storeName) {
        String prefix = STORE_PREFIXES.get(storeName);
        if (prefix == null) {
            throw new IllegalArgumentException("가맹점을 찾을 수 없습니다: " + storeName);
        }
        return prefix;
    }

    public String generateCouponNumber(String storeName) {
        String storePrefix = getStorePrefix(storeName);
        Random random = new Random();
        String randomDigits = String.format("%012d", random.nextLong() % 1000000000000L);
        return storePrefix + randomDigits;
    }
}
