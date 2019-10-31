package com.choco.ithink.pojo;

public class UserOtherInfo {
    private Integer id;

    private Integer userId;

    private String userAddress;

    private String userIndustry;

    private String userSchool;

    private String userSelfintroduction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getUserIndustry() {
        return userIndustry;
    }

    public void setUserIndustry(String userIndustry) {
        this.userIndustry = userIndustry == null ? null : userIndustry.trim();
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool == null ? null : userSchool.trim();
    }

    public String getUserSelfintroduction() {
        return userSelfintroduction;
    }

    public void setUserSelfintroduction(String userSelfintroduction) {
        this.userSelfintroduction = userSelfintroduction == null ? null : userSelfintroduction.trim();
    }
}