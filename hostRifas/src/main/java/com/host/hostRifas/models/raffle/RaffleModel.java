package com.host.hostRifas.models.raffle;

import java.time.LocalDateTime;
import java.util.List;

import com.host.hostRifas.helpers.raffle.RaffleStatus;
import com.host.hostRifas.models.user.UserModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @OneToMany(mappedBy = "raffle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<NumberModel> numbers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomologatePlatform() {
        return homologatePlatform;
    }

    public void setHomologatePlatform(String homologatePlatform) {
        this.homologatePlatform = homologatePlatform;
    }

    public String getHomologateCode() {
        return homologateCode;
    }

    public void setHomologateCode(String homologateCode) {
        this.homologateCode = homologateCode;
    }

    public String getRaffleImage() {
        return raffleImage;
    }

    public void setRaffleImage(String raffleImage) {
        this.raffleImage = raffleImage;
    }

    public String getPrizeImage() {
        return prizeImage;
    }

    public void setPrizeImage(String prizeImage) {
        this.prizeImage = prizeImage;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public RaffleStatus getStatus() {
        return status;
    }

    public void setStatus(RaffleStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<NumberModel> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<NumberModel> numbers) {
        this.numbers = numbers;
    }

    

}
