package com.spharos.ssgpoint.coupon.application;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

@Component
public class StoreManager {

    @Value("${store.emart24.prefix}")
    private String emart24Prefix;

    @Value("${store.emarteveryday.prefix}")
    private String emarteverydayPrefix;

    @Value("${store.shinsegae.prefix}")
    private String shinsegaePrefix;

    @Value("${store.speedmate.prefix}")
    private String speedmatePrefix;

    private Map<String, String> storePrefixes;

    @PostConstruct
    public void init() {
        storePrefixes = Map.of(
                "이마트24", emart24Prefix,
                "이마트everyday", emarteverydayPrefix,
                "신세계백화점", shinsegaePrefix,
                "SpeedMate", speedmatePrefix
        );
    }

    public String generateCouponNumber(String storeName) {
        String storePrefix = storePrefixes.get(storeName);
        if (storePrefix == null) {
            throw new IllegalArgumentException("Unknown store: " + storeName);
        }
        Random random = new Random();
        String randomDigits = String.format("%012d", Math.abs(random.nextLong()) % 1000000000000L);
        return storePrefix + randomDigits;
    }
}
