/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicActorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicDirectorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicWriterDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.MovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.ProductionCompanyDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.RoleDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.UserDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IMovieService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
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
@Component("editMovieBB")
@Scope("request")
public class editMovieBB {
    
    
    Map<String, String> params;
    private MovieDto movie;
    
    @Autowired
    private IMovieService movieService;
    
    private String newDirector;
    private String newWriter;
    private String newProducer;
    private String movie_length;
    private String budget;

    @PostConstruct
    public void validate() {
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        String id_string = params.get("id");
        
        Long id = -1l;
        try {
            id = Long.parseLong(id_string);
        } catch (NumberFormatException n) {
        }

        movie = movieService.getMovieById(id);
        if (movie == null) {
            movie = new MovieDto();
            return;
        }
        
        String saving = params.get("s");        
        if("true".equals(saving)){
            
            newDirector = params.get("dir");
            newWriter = params.get("wr");
            newProducer = params.get("prod");
            
            String poster_url = params.get("poster_url");
            movie.setPoster_url(poster_url);
            
            String short_info = params.get("short_info");
            movie.setShort_info(short_info);
            String long_info = params.get("long_info");
            movie.setLong_info(long_info);
            String age_rating = params.get("age_rating");
            movie.setAge_rating(age_rating);
            String genre = params.get("genre");
            movie.setGenre(genre);
            budget = params.get("budget");
            int bud = movie.getBudget();
            try {
                bud = Integer.parseInt(budget);
            } catch (Exception e){
            }
            
            movie.setBudget(bud);
            
            movie_length = params.get("movie_length");
            int len = movie.getMovie_length();
            try {
                len = Integer.parseInt(movie_length);
            } catch (Exception e){
            }
            movie.setMovie_length(len);
            
            
            
            
        } else {
            
            newDirector = movie.getDirector()==null ? "" : movie.getDirector().toString();
            newWriter = movie.getWriter()==null ? "" : movie.getWriter().toString();
            newProducer = movie.getProducer()==null ? "" : movie.getProducer().toString();
            movie_length = movie.getMovie_length() + "";
            budget = movie.getBudget() + "";
            
        }
        
    }

    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    private void error() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().responseSendError(404, "Movie with this ID doesn't exist");
        facesContext.responseComplete();
    }

    public List<BasicActorDto> getStars() throws IOException {
        List<BasicActorDto> result = new ArrayList<BasicActorDto>();
        if (movie != null) {
            Iterator<RoleDto> it = movie.getCast().iterator();
            while(it.hasNext()) {
                result.add(it.next().getActor());
            }
        }

        return result;
    }
    
    public String save(){
        movieService.updateMovie(movie);
        
        if(newDirector == null || newDirector.isEmpty()){
            movieService.removeDirectorFromMovie(movie.getId());
        } else {
            Long dir_id = parseForID(newDirector);
            movieService.addDirectorToMovie(movie.getId(), dir_id);
        }
        
        if(newWriter == null || newWriter.isEmpty()){
            movieService.removeWriterFromMovie(movie.getId());
        } else {
            Long wr_id = parseForID(newWriter);
            movieService.addWriterToMovie(movie.getId(), wr_id);
        }
        
        if(newProducer == null || newProducer.isEmpty()){
            movieService.removeProducerFromMovie(movie.getId());
        } else {
            Long prod_id = parseForID(newProducer);
            movieService.addProducerToMovie(movie.getId(), prod_id);
        }
        
        return "cast?faces-redirect=true&id=" + (long)movie.getId();
    }
    
    public String delete(){
        movieService.deleteMovie(movie.getId());
        return "index?faces-redirect=true";      
    }
    
    public String getName() {
        return movie.getName();
    }

    public void setName(String name) {
        movie.setName(name);
    }

    public String getShort_info() {
        System.out.println("getShortInfo");
        return movie.getShort_info();
    }

    public void setShort_info(String short_info) {
        movie.setShort_info(short_info);
    }

    public String getLong_info() {
        return movie.getLong_info();
    }

    public void setLong_info(String long_info) {
        movie.setLong_info(long_info);
    }

    public String getGenre() {
        return movie.getGenre();
    }

    public void setGenre(String genre) {
        movie.setGenre(genre);
    }

    public int getMovie_length() {
        return movie.getMovie_length();
    }

    public void setMovie_length(int movie_length) {
        movie.setMovie_length(movie_length);
    }

    public String getAge_rating() {
        return movie.getAge_rating();
    }

    public void setAge_rating(String age_rating) {
        movie.setAge_rating(age_rating);
    }

    public int getBudget() {
        return movie.getBudget(); 
    }

    public void setBudget(int budget) {
       movie.setBudget(budget);
    }

    public Date getRelease_date() {
        return movie.getRelease_date();
    }

    public void setRelease_date(int year, int month, int day) {
        movie.setRelease_date(year, month, day);
    }

    public String getPoster_url() {
        return movie.getPoster_url();
    }

    public void setPoster_url(String poster_url) {
        movie.setPoster_url(poster_url);
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
    
    
    
    private static Long parseForID(String s){
       Long result;
       try {
           result = Long.parseLong(s.substring(s.indexOf("(")+1, s.lastIndexOf(")")));
       } catch (Exception e) {
           return null;
       }
       return result; 
    }
    
    public void validateLenght255(FacesContext facesContext, UIComponent uiComponent, Object newValue) throws ValidatorException {
        String number = (String) newValue;
        if (number.length() > 255) {
            throw new ValidatorException(new FacesMessage("Field is limited to 255 characters!"));
        }
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
    
}
