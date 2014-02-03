package com.cvut.spatnjak.wpa.moviedatabase.bo;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Kuba Spatny
 */

@Entity
@Table(name = "Movies")
public class Movie extends AbstractBusinessObject {
    
    // ADD NUMBER OF PEOPLE WHO RATED
    
    @Column(nullable = false,unique = true)
    private String name;
    
    @Column(nullable = false, columnDefinition="TEXT")
    private String short_info;
    
    @Column(nullable = false, columnDefinition="TEXT")
    private String long_info;
    
    private String genre;
    
    private int movie_length;
    
    private String age_rating;
    
    private float user_rating;
    
    private int num_user_rated;
    
    private int budget;
    
    @Temporal(TemporalType.DATE)
    private Date release_date;
    
    private String poster_url;
    
    @ManyToMany
    private Map<String, Actor> cast;
    
    public void addCastMemeber(String role, Actor actor){
        cast = cast==null ? new HashMap<String, Actor>() : cast;
        this.cast.put(role, actor);
    }
    
    public void removeCastMember(Actor actor){
        Iterator<Entry<String, Actor>> it = cast.entrySet().iterator();
        while (it.hasNext()) {
           Entry<String, Actor> e = it.next();
           if(e.getValue().id.equals(actor.id)){
                it.remove();
           }
        }
    }
    
    @ManyToOne
    private Director director;
    
    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
    
    public void removeDirector(Director director){
        if(this.director.id.equals(director.id)){
            this.director = null;
        }
    }
    
    @ManyToOne
    private Writer writer;
    
    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }
    
    public void removeWriter(Writer writer){
        if(this.writer.id.equals(writer.id)){
            this.writer = null;
        }
    }
    
    @ManyToOne
    private ProductionCompany producer;
    
    public ProductionCompany getProducer() {
        return producer;
    }

    public void setProducer(ProductionCompany producer) {
        this.producer = producer;
    }
    
    public void removeProducer(ProductionCompany producer){
        if(this.producer.id.equals(producer.getId())){
            this.producer = null;
        }
    }
    
    @ManyToOne
    private User createdBy;

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

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
        return user_rating;
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

    public String getPoster_url() {
        //if(poster_url == null){
        //    return "posterunavailable.jpg";
        //}
        
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public Map<String, Actor> getCast() {
        return cast;
    }

    public void setCast(Map<String, Actor> cast) {
        this.cast = cast;
    }

    public int getNum_user_rated() {
        return num_user_rated;
    }

    public void setNum_user_rated(int num_user_rated) {
        this.num_user_rated = num_user_rated;
    }
    
}
