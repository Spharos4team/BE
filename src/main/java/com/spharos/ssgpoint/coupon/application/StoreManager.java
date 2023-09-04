package com.spharos.ssgpoint.coupon.application;

import com.spharos.ssgpoint.coupon.exception.UnknownStoreException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class StoreManager {

    private final Map<String, String> storePrefixes = Map.of(
            "이마트24", "${store.emart24.prefix}",
            "이마트everyday", "${store.emarteveryday.prefix}",
            "신세계백화점", "${store.shinsegae.prefix}",
            "SpeedMate", "${store.speedmate.prefix}"
    );

    public String generateCouponNumber(String storeName) {
        String storePrefix = storePrefixes.getOrDefault(storeName, null);
        if (storePrefix == null) {
            throw new UnknownStoreException(storeName);
        }
        Random random = new Random();
        String randomDigits = String.format("%012d", random.nextLong() % 1000000000000L);
        return storePrefix + randomDigits;
    }
}
