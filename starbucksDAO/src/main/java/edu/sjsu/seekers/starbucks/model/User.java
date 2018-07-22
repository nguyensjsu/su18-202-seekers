package edu.sjsu.seekers.starbucks.model;


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

    @Column(name="Is_Verified_Phone_Number")
    private String isVerifiedphoneNumber;

    @Column(name="Is_Active_Customer")
    private String idActiveCustomer;

    @Column(name="Default_Store_Key")
    private String defaultStoreKey;

    @Column(name="Reward_Points")
    private String rewardPoints;

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

    public void setIsVerifiedphoneNumber(String isVerifiedphoneNumber) {
        this.isVerifiedphoneNumber = isVerifiedphoneNumber;
    }

    public void setIdActiveCustomer(String idActiveCustomer) {
        this.idActiveCustomer = idActiveCustomer;
    }

    public void setDefaultStoreKey(String defaultStoreKey) {
        this.defaultStoreKey = defaultStoreKey;
    }

    public void setRewardPoints(String rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public Integer getUserKey() {
        return userKey;

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

    public String getIsVerifiedphoneNumber() {
        return isVerifiedphoneNumber;
    }

    public String getIdActiveCustomer() {
        return idActiveCustomer;
    }

    public String getDefaultStoreKey() {
        return defaultStoreKey;
    }

    public String getRewardPoints() {
        return rewardPoints;
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


    @Override
    public String toString() {
        return "User{" +
                "userKey=" + userKey +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }

}
