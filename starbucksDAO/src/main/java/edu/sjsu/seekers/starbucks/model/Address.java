package edu.sjsu.seekers.starbucks.model;

import javax.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Address_Key")
    private Integer addressKey;

    @Column(name="Address_Line_1")
    private String addressLine1;

    @Column(name="Address_Line_2")
    private String addressLine2;

    @Column(name="City")
    private String city;

    @Column(name="State")
    private String state;

    @Column(name="Country")
    private String country;

    @Column(name="Zip_Code")
    private Integer zipCode;

    public Integer getAddressKey() {
        return addressKey;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setAddressKey(Integer addressKey) {
        this.addressKey = addressKey;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressKey=" + addressKey +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\''  +
                '}';
    }


}
