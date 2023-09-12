package com.spharos.ssgpoint.receipt.infrastructure;

import com.spharos.ssgpoint.receipt.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    Optional<Receipt> findByAllianceAndBrandAndStoreNameAndNumber(String alliance, String brand,
                                                                  String storeName, String number);

}
