package com.kavindu.fabrikza.payment.dto;

import com.kavindu.fabrikza.payment.models.enums.PaymentStatus;
import com.kavindu.fabrikza.payment.models.enums.PaymentType;
import jakarta.persistence.Access;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private UUID orderId;
    private PaymentType paymentMethod;
    private PaymentStatus paymentStatus;
    private String transactionId;
}
