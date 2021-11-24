package br.com.jobem.api.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "information")
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String streetLocation;
    private String complement;
    private String zipCode;
    private String district;
    private String city;
    private String state;
    private String country;
    private String number;
    private String cellNumber;
    private String phoneNumber;
    private String imageType;
    private String imageContent;
    private String imageBackground;
    @OneToOne
    @JoinColumn(name = "user_fk")
    private User user;


    public Information(
            String streetLocation,
            String complement,
            String zipCode,
            String district,
            String city,
            String state,
            String country,
            String number,
            String cellNumber,
            String phoneNumber,
            User user) {
        this.streetLocation = streetLocation;
        this.complement = complement;
        this.zipCode = zipCode;
        this.district = district;
        this.city = city;
        this.state = state;
        this.country = country;
        this.number = number;
        this.cellNumber = cellNumber;
        this.phoneNumber = phoneNumber;
        this.user = user;
    }

    public Information() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreetLocation() {
        return streetLocation;
    }

    public void setStreetLocation(String streetLocation) {
        this.streetLocation = streetLocation;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complent) {
        this.complement = complent;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageContent() {
        return imageContent;
    }

    public void setImageContent(String imageContent) {
        this.imageContent = imageContent;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImageBackground() {
        return imageBackground;
    }

    public void setImageBackground(String imageBackground) {
        this.imageBackground = imageBackground;
    }
}
