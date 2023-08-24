package com.spharos.ssgpoint.receipt.infrastructure;

import com.spharos.ssgpoint.receipt.domain.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    List<Receipt> findByNumber(String number);

}
