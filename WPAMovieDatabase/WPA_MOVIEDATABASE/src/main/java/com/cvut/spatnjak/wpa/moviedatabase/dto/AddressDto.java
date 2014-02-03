package com.cvut.spatnjak.wpa.moviedatabase.dto;

import com.cvut.spatnjak.wpa.moviedatabase.dto.AbstractDto;

/**
 *
 * @author Kuba Spatny
 */
public class AddressDto extends AbstractDto {
    
    private String street;
    private Integer street_number;
    private String city;
    private Integer city_post_code;
    private String country;

    public AddressDto() {
    }

    public AddressDto(String street, Integer street_number, String city, Integer city_post_code, String country) {
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
