package edu.sjsu.seekers.starbucks.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class User {
    /*
        To be modified as per current table structure

     */

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="User_Key")
    private Integer userKey;

    @ManyToOne
    @JoinColumn(name = "Address_Key", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Address addressKey;

    @Column(name="User_Name")
    private String userName;

    @Column(name="Full_Name")
    private String fullName;

    @Column(name="Password")
    private String password;

    @Column(name="Date_of_Birth")
    private String dateOfBirth;

    @Column(name="Email_ID")
    private String emailId;

    @Column(name="Phone_Number")
    private String phoneNumber;

    @Column(name="Is_Logged_In")
    private String isLoggedIn;

    @Column(name="Is_Active_Customer")
    private String idActiveCustomer;

    @Column(name="Verification_Code")
    private String verificationCode;

    @Column(name="Default_Store_Key")
    private Integer defaultStoreKey;

    @Column(name="Reward_Points")
    private double rewardPoints;

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setIdActiveCustomer(String idActiveCustomer) {
        this.idActiveCustomer = idActiveCustomer;
    }

    public void setDefaultStoreKey(Integer defaultStoreKey) {
        this.defaultStoreKey = defaultStoreKey;
    }

    public void setRewardPoints(double rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getPassword() {
        return password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getIdActiveCustomer() {
        return idActiveCustomer;
    }

    public Integer getDefaultStoreKey() {
        return defaultStoreKey;
    }

    public double getRewardPoints() {
        return rewardPoints;
    }

    public String getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setIsLoggedIn(String isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public Address getAddressKey() {
        return addressKey;
    }

    public void setAddressKey(Address addressKey) {
        this.addressKey= addressKey;
    }


    @Override
    public String toString() {
        return "User{" +
                "userKey=" + userKey +
                ", addressKey=" + addressKey +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isLoggedIn='" + isLoggedIn + '\'' +
                ", idActiveCustomer='" + idActiveCustomer + '\'' +
                ", idAccountVerified='" + idAccountVerified + '\'' +
                ", defaultStoreKey='" + defaultStoreKey + '\'' +
                ", rewardPoints='" + rewardPoints + '\'' +
                '}';
    }
}
