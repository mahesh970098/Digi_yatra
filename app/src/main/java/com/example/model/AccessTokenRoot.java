package com.example.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AccessTokenRoot {

    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("expires_in")
    @Expose
    private Integer expiresIn;
    @SerializedName("token_type")
    @Expose
    private String tokenType;
    @SerializedName("scope")
    @Expose
    private Object scope;
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;
    @SerializedName("digilockerid")
    @Expose
    private String digilockerid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("eaadhaar")
    @Expose
    private String eaadhaar;
    @SerializedName("reference_key")
    @Expose
    private String referenceKey;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("new_account")
    @Expose
    private String newAccount;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Object getScope() {
        return scope;
    }

    public void setScope(Object scope) {
        this.scope = scope;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getDigilockerid() {
        return digilockerid;
    }

    public void setDigilockerid(String digilockerid) {
        this.digilockerid = digilockerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEaadhaar() {
        return eaadhaar;
    }

    public void setEaadhaar(String eaadhaar) {
        this.eaadhaar = eaadhaar;
    }

    public String getReferenceKey() {
        return referenceKey;
    }

    public void setReferenceKey(String referenceKey) {
        this.referenceKey = referenceKey;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNewAccount() {
        return newAccount;
    }

    public void setNewAccount(String newAccount) {
        this.newAccount = newAccount;
    }

}