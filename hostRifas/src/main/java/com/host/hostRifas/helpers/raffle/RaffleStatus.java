package com.host.hostRifas.helpers.raffle;

public enum RaffleStatus {
    approved("aproved"),
    rejected("rejected"),
    analyzing("analyzing");

    private String status;

    RaffleStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
