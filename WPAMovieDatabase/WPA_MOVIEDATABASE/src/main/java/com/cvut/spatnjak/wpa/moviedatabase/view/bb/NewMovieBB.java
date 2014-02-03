/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicMovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IMovieService;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba
 */

@Component("newMovieBB")
@Scope("request")
public class NewMovieBB {
    
    @Autowired
    private IMovieService movieService;
    
    @Autowired
    private UserBB userBB;
    
    String movie_name;
    String short_info;
    String long_info;
    String genre;
    int movie_length;
    String age_rating;
    int budget;
    Date date;
    String poster_url;
    
    private String newDirector;
    private String newWriter;
    private String newProducer;

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
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

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public String getNewDirector() {
        return newDirector;
    }

    public void setNewDirector(String newDirector) {
        this.newDirector = newDirector;
    }

    public String getNewWriter() {
        return newWriter;
    }

    public void setNewWriter(String newWriter) {
        this.newWriter = newWriter;
    }

    public String getNewProducer() {
        return newProducer;
    }

    public void setNewProducer(String newProducer) {
        this.newProducer = newProducer;
    }
    
    public String addMovie(){
        
        long id = movieService.addMovie(movie_name, short_info, long_info, genre, movie_length, age_rating, budget, date, poster_url, userBB.getUser().getId());
        if(newDirector == null || newDirector.isEmpty()){
            movieService.removeDirectorFromMovie(id);
        } else {
            Long dir_id = parseForID(newDirector);
            movieService.addDirectorToMovie(id, dir_id);
        }
        
        if(newWriter == null || newWriter.isEmpty()){
            movieService.removeWriterFromMovie(id);
        } else {
            Long wr_id = parseForID(newWriter);
            movieService.addWriterToMovie(id, wr_id);
        }
        
        if(newProducer == null || newProducer.isEmpty()){
            movieService.removeProducerFromMovie(id);
        } else {
            Long prod_id = parseForID(newProducer);
            movieService.addProducerToMovie(id, prod_id);
        }
        
        return "cast?faces-redirect=true&id=" + id;
        
    }
    
    private static Long parseForID(String s){
       Long result;
       try {
           result = Long.parseLong(s.substring(s.indexOf("(")+1, s.lastIndexOf(")")));
       } catch (Exception e) {
           return null;
       }
       return result; 
    }
    
    public void validateNumber(FacesContext facesContext, UIComponent uiComponent, Object newValue) throws ValidatorException {
        if(newValue instanceof String){
            throw new ValidatorException(new FacesMessage("Field can only contain numbers!"));
        }
        
        int number = (Integer) newValue;
        if(number > Integer.MAX_VALUE || number < 0){
            throw new ValidatorException(new FacesMessage("Please put a number in range: 0 ... "+Integer.MAX_VALUE));
        }
        
    }
    
    public void validateLenght255(FacesContext facesContext, UIComponent uiComponent, Object newValue) throws ValidatorException {
        String number = (String) newValue;
        if (number.length() > 255) {
            throw new ValidatorException(new FacesMessage("Field is limited to 255 characters!"));
        }
    }
    
    public void validateMovieName(FacesContext facesContext, UIComponent uiComponent, Object newValue) throws ValidatorException {
        String name = (String) newValue;
        if (name.length() > 255) {
            throw new ValidatorException(new FacesMessage("Field is limited to 255 characters!"));
        }
        List<BasicMovieDto> result = movieService.searchByName(name);
        for (BasicMovieDto m : result) {
            System.out.println("["+m.getName() + ", "+name+"]");
            if(m.getName().toLowerCase().equals(name.toLowerCase())){
                throw new ValidatorException(new FacesMessage("Movie with the same name already exists!"));
            }
            
        }
    }
    
}
