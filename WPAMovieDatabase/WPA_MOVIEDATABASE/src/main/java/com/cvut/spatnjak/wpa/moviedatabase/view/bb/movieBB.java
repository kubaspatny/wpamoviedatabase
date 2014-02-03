/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicActorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.MovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.RoleDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.UserDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IMovieService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IUserService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba
 */
@Component("movieBB")
@Scope("request")
public class movieBB {

    Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    private MovieDto movie;
    private String rating;
    private UserDto user;
    
    @Autowired
    private IMovieService movieService;
    
    @Autowired
    private IUserService userService;
    
    

    @PostConstruct
    public void validate() throws IOException {
        String id_string = params.get("id");
        if (id_string == null || id_string.isEmpty()) {
            error();
            return;
        }
        Long id = -1l;
        try {
            id = Long.parseLong(id_string);
        } catch (NumberFormatException n) {
            error();
            return;
        }

        movie = movieService.getMovieById(id);
        if (movie == null) {
            error();
            return;
        }
        
        String usernamename = SecurityContextHolder.getContext().getAuthentication().getName();
        user = userService.getUserByUsername(usernamename);


    }
    
    public String rate(){
        int rate = Integer.parseInt(rating);
        movieService.rateMovie(movie.getId(), user.getId(), rate);
        return "movie?faces-redirect=true&id=" + (long) movie.getId();
    }
    
    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    public UserDto getUser() {
        return user;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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
}
