package com.host.hostRifas.helpers.requests;

public class RaffleRequest{
    private String name;
    private String homologatePlatform;
    private String homologateCode;
    private String raffleImagePath;
    private String prizeImagePath;
    private Float value;
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
    public String getRaffleImagePath() {
        return raffleImagePath;
    }
    public void setRaffleImagePath(String raffleImagePath) {
        this.raffleImagePath = raffleImagePath;
    }
    public String getPrizeImagePath() {
        return prizeImagePath;
    }
    public void setPrizeImagePath(String prizeImagePath) {
        this.prizeImagePath = prizeImagePath;
    }
    public Float getValue() {
        return value;
    }
    public void setValue(Float value) {
        this.value = value;
    }

    
}
