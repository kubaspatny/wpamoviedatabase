package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.MovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IMovieService;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba Spatny
 */
@Component("allMoviesListBB")
@Scope("request")
public class AllMoviesListBB implements Serializable {
    
    @Autowired
    private IMovieService movieService;
    
    private List<MovieDto> allMovies;
    
    public List<MovieDto> getAllMovies(){
        return movieService.getAllMovies();
    }
    
}
