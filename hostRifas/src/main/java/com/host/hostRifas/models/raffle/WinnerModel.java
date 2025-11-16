package com.host.hostRifas.models.raffle;

import java.time.LocalDateTime;

import com.host.hostRifas.models.user.UserModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class WinnerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raffleId")
    private RaffleModel raffle;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserModel user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numberId")
    private NumberModel number;

    @Column
    private String confirmationImage;

    @Column
    private LocalDateTime realizatedAt;

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

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public NumberModel getNumber() {
        return number;
    }

    public void setNumber(NumberModel number) {
        this.number = number;
    }

    public String getConfirmationImage() {
        return confirmationImage;
    }

    public void setConfirmationImage(String confirmationImage) {
        this.confirmationImage = confirmationImage;
    }

    public LocalDateTime getRealizatedAt() {
        return realizatedAt;
    }

    public void setRealizatedAt(LocalDateTime realizatedAt) {
        this.realizatedAt = realizatedAt;
    }

    
}
