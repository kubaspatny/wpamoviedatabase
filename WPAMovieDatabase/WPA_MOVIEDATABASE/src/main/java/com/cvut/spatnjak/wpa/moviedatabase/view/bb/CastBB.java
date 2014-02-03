/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.MovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IMovieService;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba
 */
@Component("castBB")
@Scope("request")
public class CastBB {

    Map<String, String> params;
    private MovieDto movie;
    String actor;
    String role;
    String id_str;

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }
    @Autowired
    private IMovieService movieService;

    @PostConstruct
    public void init() {
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id_string = params.get("id");

        Long id = -1l;
        try {
            id = Long.parseLong(id_string);
        } catch (NumberFormatException n) {
        }
        System.out.println("id_long: " + id);

        movie = movieService.getMovieById(id);
        if (movie == null) {
            movie = new MovieDto();
            return;
        }

        String saving = params.get("s");        
        if("true".equals(saving)){
            actor = params.get("act");
            role = params.get("role");
        }

    }

    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String save() {
        return "movie?faces-redirect=true&id=" + (long) movie.getId();
    }

    public String addRole() {
        movieService.addActorRoleToMovie(movie.getId(), parseForID(actor), role);
        return "cast?faces-redirect=true&id=" + (long)movie.getId();
    }
    
    public String delete(long id) {
        movieService.removeActorFromMovie(movie.getId(), id);
        return "cast?faces-redirect=true&id=" + (long)movie.getId();
    }

    private static Long parseForID(String s) {
        Long result;
        try {
            String number = s.substring(s.indexOf("(") + 1, s.lastIndexOf(")"));
            if(number == null || number.isEmpty()) throw new Exception("wrong format");
            result = Long.parseLong(number);
        } catch (Exception e) {
            return null;
        }
        return result;
    }
}
