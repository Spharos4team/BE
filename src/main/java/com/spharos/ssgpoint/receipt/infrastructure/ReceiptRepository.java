package com.spharos.ssgpoint.receipt.infrastructure;

import com.spharos.ssgpoint.receipt.domain.Receipt;
import com.spharos.ssgpoint.receipt.dto.ReceiptGetDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    Optional<Receipt> findByAllianceAndBrandAndStoreNameAndNumber(String alliance, String brand,
                                                                  String storeName, String number);

    @Query("select new com.spharos.ssgpoint.receipt.dto.ReceiptGetDto(r.alliance,r.brand,r.storeName,r.cardName,r.point," +
            "r.pointCardNumber) from Receipt r where r.id = :id")
    ReceiptGetDto findReceiptById(Long id);

}
