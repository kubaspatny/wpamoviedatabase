/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.dto;

import com.cvut.spatnjak.wpa.moviedatabase.bo.Actor;
import com.cvut.spatnjak.wpa.moviedatabase.convert.ActorDtoConvertor;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kuba
 */
public class MovieDto extends AbstractDto {
    
    private String name;
    private String short_info;
    private String long_info;
    private String genre;
    private int movie_length;
    private String age_rating;
    private float user_rating;
    private int budget;
    private Date release_date;
    private String poster_url;
    
    public MovieDto() {
    }
    
    public MovieDto(Long id, String name, String short_info, String long_info, String genre, int movie_length, String age_rating, float user_rating, int budget, Date release_date, String poster_url) {
        this.id = id;
        this.name = name;
        this.short_info = short_info;
        this.long_info = long_info;
        this.genre = genre;
        this.movie_length = movie_length;
        this.age_rating = age_rating;
        this.user_rating = user_rating;
        this.budget = budget;
        this.release_date = release_date;
        this.poster_url = poster_url;
    }
    
    //private Map<String, BasicActorDto> cast;
    private List<RoleDto> cast;
    private BasicDirectorDto director;
    private BasicWriterDto writer;
    private ProductionCompanyDto producer;
    private UserDto createdBy;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_info() {
        return short_info;
    }

    public void setShort_info(String short_info) {
        this.short_info = short_info;
    }

    public String getLong_info() {
        return long_info;
    }

    public void setLong_info(String long_info) {
        this.long_info = long_info;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getMovie_length() {
        return movie_length;
    }

    public void setMovie_length(int movie_length) {
        this.movie_length = movie_length;
    }

    public String getAge_rating() {
        return age_rating;
    }

    public void setAge_rating(String age_rating) {
        this.age_rating = age_rating;
    }

    public float getUser_rating() {        
        return Float.parseFloat(String.format("%.1f", user_rating));
    }

    public void setUser_rating(float user_rating) {
        this.user_rating = user_rating;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }
    
    public void setRelease_date(int year, int month, int day) {
        this.release_date = new Date(year-1900, month-1, day);
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public List<RoleDto> getCast() {                               
        return cast;
    }

    public void setCast(Map<String, Actor> cast) {
        this.cast = ActorDtoConvertor.convertActorRoleMapToBasic(cast);         
    }

    public BasicDirectorDto getDirector() {
        return director;
    }

    public void setDirector(BasicDirectorDto director) {
        this.director = director;
    }

    public ProductionCompanyDto getProducer() {
        return producer;
    }

    public void setProducer(ProductionCompanyDto producer) {
        this.producer = producer;
    }

    public UserDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDto createdBy) {
        this.createdBy = createdBy;
    }

    public BasicWriterDto getWriter() {
        return writer;
    }

    public void setWriter(BasicWriterDto writer) {
        this.writer = writer;
    }
    
    
    
}
