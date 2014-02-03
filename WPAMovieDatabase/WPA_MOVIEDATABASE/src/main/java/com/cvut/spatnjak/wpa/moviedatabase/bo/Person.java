/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.bo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Kuba
 */

@MappedSuperclass
public abstract class Person extends AbstractBusinessObject {
    
    @Column(nullable = false)
    private String first_name;
    
    @Column(nullable = false)
    private String last_name;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date_of_birth;    
    
    private String image_url;    
    
    @Column(columnDefinition="TEXT")
    private String biography;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    
    public void setDate_of_birth(int year, int month, int day){
        this.date_of_birth = new Date(year - 1900, month - 1, day);
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
    
}
