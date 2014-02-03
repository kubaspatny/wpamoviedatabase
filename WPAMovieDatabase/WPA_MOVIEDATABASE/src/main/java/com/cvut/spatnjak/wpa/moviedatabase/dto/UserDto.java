/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.dto;

import com.cvut.spatnjak.wpa.moviedatabase.bo.Movie;
import com.cvut.spatnjak.wpa.moviedatabase.convert.BasicMovieDtoConvert;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Kuba
 */
public class UserDto extends PersonDto {
    
    private String username;
    private boolean isAdmin;
    private String email;
    
    private Set<BasicMovieDto> created_movies;
    private Set<Long> rated_movies;
    
    public UserDto() {
    }

    public UserDto(Long id, String first_name, String last_name, Date date_of_birth, String image_url, String biography, String email) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.image_url = image_url;
        this.biography = biography;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<BasicMovieDto> getCreated_movies() {
        return created_movies;
    }

    public void setCreated_movies(Set<BasicMovieDto> created_movies) {
        this.created_movies = created_movies;
    }
    
    private AddressDto address;

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public Set<Long> getRated_movies() {
        return rated_movies;
    }

    public void setRated_movies(Set<Long> rated_movies) {
        this.rated_movies = rated_movies;
    }
    
    public boolean hasRated(Long id){
        return rated_movies.contains(id);
    }
    
    
    
}
