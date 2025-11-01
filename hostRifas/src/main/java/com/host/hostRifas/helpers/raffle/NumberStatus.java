package com.host.hostRifas.helpers.raffle;

public enum NumberStatus {
    available("available"),
    purchased("Purchased"),
    drawn("Drawn");

    private String status;

    NumberStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
