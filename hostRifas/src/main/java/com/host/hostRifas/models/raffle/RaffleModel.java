package com.host.hostRifas.models.raffle;

import java.time.LocalDateTime;

import com.host.hostRifas.helpers.raffle.RaffleStatus;
import com.host.hostRifas.models.user.UserModel;

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
@Table(name = "raffle")
public class RaffleModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, nullable = false)
    private String name;
    @Column(length = 30, nullable = false)
    private String homologatePlatform;
    @Column(length = 100, nullable = false)
    private String homologateCode;
    @Column(length = 250, nullable = false)
    private String raffleImage;
    @Column(length = 250, nullable = false)
    private String prizeImage;
    @Column
    private Double value;
    @Column
    private RaffleStatus status;
    @Column
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel user;

}
