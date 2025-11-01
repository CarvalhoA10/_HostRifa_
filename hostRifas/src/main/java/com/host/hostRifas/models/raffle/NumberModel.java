package com.host.hostRifas.models.raffle;

import java.time.LocalDateTime;

import com.host.hostRifas.helpers.raffle.NumberStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="raffle_number")
public class NumberModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private int number;
    @Column
    private NumberStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raffleId")
    private RaffleModel raffle;
    @Column
    private LocalDateTime createdAt;
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
    public RaffleModel getRaffle() {
        return raffle;
    }
    public void setRaffle(RaffleModel raffle) {
        this.raffle = raffle;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
