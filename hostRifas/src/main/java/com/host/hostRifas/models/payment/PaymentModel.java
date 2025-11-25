package com.host.hostRifas.models.payment;

import java.time.LocalDateTime;

import com.host.hostRifas.helpers.payment.PaymentStatus;
import com.host.hostRifas.models.raffle.RaffleModel;

public class PaymentModel {
    
    private Long id;
    private RaffleModel raffle;
    private double paymentValue;
    private PaymentStatus status;
    private LocalDateTime paymentDate;
    private String receipt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RaffleModel getRaffle() {
        return raffle;
    }

    public void setRaffle(RaffleModel raffle) {
        this.raffle = raffle;
    }

    public double getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(double paymentValue) {
        this.paymentValue = paymentValue;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    

}
