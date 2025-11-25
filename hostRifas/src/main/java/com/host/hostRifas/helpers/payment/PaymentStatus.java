package com.host.hostRifas.helpers.payment;

import com.mercadopago.resources.payment.Payment;

public enum PaymentStatus {
    waiting("waiting"),
    approved("approved"),
    disapproved("disapproved");

    private String status;

    PaymentStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
