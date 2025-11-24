package com.host.hostRifas.helpers.responses;

import com.host.hostRifas.helpers.raffle.NumberStatus;
import com.host.hostRifas.models.raffle.WinnerModel;

public class NumberResponse {
    
    private Long id;
    private int number;
    private NumberStatus status;
    private WinnerModel winner;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public NumberStatus getStatus() {
        return status;
    }
    public void setStatus(NumberStatus status) {
        this.status = status;
    }
    public WinnerModel getWinner() {
        return winner;
    }
    public void setWinner(WinnerModel winner) {
        this.winner = winner;
    }


    
}
