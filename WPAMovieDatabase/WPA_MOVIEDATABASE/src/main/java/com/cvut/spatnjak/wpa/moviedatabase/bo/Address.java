package com.cvut.spatnjak.wpa.moviedatabase.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Kuba
 */
@Entity
@Table(name = "Addresses")
public class Address extends AbstractBusinessObject {
    
    @OneToOne(mappedBy = "user_address")
    private User address_user;
    
    @Column(nullable = false)
    private String street;
    
    @Column(nullable = false)
    private Integer street_number;
    
    @Column(nullable = false)
    private String city;
    
    @Column(nullable = false)
    private Integer city_post_code;
    
    @Column(nullable = false)
    private String country;
    
    public Address(){
    }

    public Address(String street, Integer street_number, String city, Integer city_post_code, String country) {
        this.street = street;
        this.street_number = street_number;
        this.city = city;
        this.city_post_code = city_post_code;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getStreet_number() {
        return street_number;
    }

    public void setStreet_number(Integer street_number) {
        this.street_number = street_number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCity_post_code() {
        return city_post_code;
    }

    public void setCity_post_code(Integer city_post_code) {
        this.city_post_code = city_post_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    
    
}
