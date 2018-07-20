package edu.sjsu.seekers.starbucks.model;


import javax.persistence.*;

@Entity
public class User {
    /*
        To be modified as per current table structure

     */

    @Id
    // @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="User_Key")
    private Integer userKey;

    @Column(name="User_Name")
    private String userName;

    @Column(name="Full_Name")
    private String fullName;

    @Column(name="Password")
    private String password;

    @Column(name="Address_Key")
    private Integer addresskey;

    @Column(name="Date_of_Birth")
    private String dateOfBirth;

    @Column(name="Email_ID")
    private String emailId;

    @Column(name="Phone_Number")
    private String phoneNumber;

    @Column(name="Is_Verified_Phone_Number")
    private String isVerifiedphoneNumber;

    @Column(name="Home_Address_id")
    private String homeAddressId;

    @Column(name="Office_Address_id")
    private String officeAddressId;

    @Column(name="Is_Active_Customer")
    private String idActiveCustomer;

    @Column(name="Default_Store_Id")
    private String defaultStoreId;

    @Column(name="Reward_Points")
    private String rewardPoints;

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddresskey(Integer addresskey) {
        this.addresskey = addresskey;
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

    public void setHomeAddressId(String homeAddressId) {
        this.homeAddressId = homeAddressId;
    }

    public void setOfficeAddressId(String officeAddressId) {
        this.officeAddressId = officeAddressId;
    }

    public void setIdActiveCustomer(String idActiveCustomer) {
        this.idActiveCustomer = idActiveCustomer;
    }

    public void setDefaultStoreId(String defaultStoreId) {
        this.defaultStoreId = defaultStoreId;
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

    public Integer getAddresskey() {
        return addresskey;
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

    public String getHomeAddressId() {
        return homeAddressId;
    }

    public String getOfficeAddressId() {
        return officeAddressId;
    }

    public String getIdActiveCustomer() {
        return idActiveCustomer;
    }

    public String getDefaultStoreId() {
        return defaultStoreId;
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
