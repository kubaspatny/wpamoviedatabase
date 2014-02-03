/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.MovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IMovieService;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba
 */
@Component("top10BB")
@Scope("request")
public class Top10BB {
    
    @Autowired
    private IMovieService movieService;
    
    Map<String, String> params;
    
    private List<MovieDto> top10;
    private String genre;
    
    @PostConstruct
    private void init(){
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        genre = params.get("genre");
        
        if(genre == null || genre.isEmpty() ||
                !(genre.equals("Action") || genre.equals("Comedy") || genre.equals("Fantasy") || genre.equals("Sci-fi"))
           ){
            top10 = movieService.getTop10();
        } else {
            top10 = movieService.getTop10Genre(genre);
        }
        
    }

    public List<MovieDto> getTop10() {
        return top10;
    }

    public void setTop10(List<MovieDto> top10) {
        this.top10 = top10;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    
    
}
