package com.cvut.spatnjak.wpa.moviedatabase.convert;

import com.cvut.spatnjak.wpa.moviedatabase.bo.Movie;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicMovieDto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Kuba Spatny
 */
public final class BasicMovieDtoConvert {
    
    public static BasicMovieDto convertMovie(Movie movie){
        if(movie == null) return null;
        BasicMovieDto m = new BasicMovieDto();
        m.setId(movie.getId());
        m.setName(movie.getName());
        m.setShort_info(movie.getShort_info());
        return m;
    }
    
    public static Set<BasicMovieDto> convertMovies(Set<Movie> movies){
        Set<BasicMovieDto> m = new HashSet<BasicMovieDto>();
        if(movies == null) return m;
        for (Movie movie : movies) {
            m.add(convertMovie(movie));
        }
        return m;
    }
    
    public static List<BasicMovieDto> convertMovies(List<Movie> movies){
        List<BasicMovieDto> m = new ArrayList<BasicMovieDto>();
        if(movies == null) return m;
        for (Movie movie : movies) {
            m.add(convertMovie(movie));
        }
        return m;
    }
    
}
