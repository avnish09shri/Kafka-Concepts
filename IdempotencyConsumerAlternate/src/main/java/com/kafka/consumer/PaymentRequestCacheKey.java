package com.kafka.consumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestCacheKey {
    private String paymentNumber;
    private int amount;
    private String transactionType;
}
