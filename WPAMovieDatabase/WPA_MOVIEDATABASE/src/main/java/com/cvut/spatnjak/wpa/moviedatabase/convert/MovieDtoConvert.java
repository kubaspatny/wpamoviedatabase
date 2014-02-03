/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.convert;

import com.cvut.spatnjak.wpa.moviedatabase.bo.Movie;
import com.cvut.spatnjak.wpa.moviedatabase.dto.MovieDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kuba
 */
public class MovieDtoConvert {
    
    public static MovieDto convertMovie(Movie movie){
        if(movie == null) return null;
        
        MovieDto m = new MovieDto();
        m.setId(movie.getId());
        m.setName(movie.getName());
        m.setShort_info(movie.getShort_info());
        m.setLong_info(movie.getLong_info());
        m.setGenre(movie.getGenre());
        m.setMovie_length(movie.getMovie_length());
        m.setAge_rating(movie.getAge_rating());
        m.setUser_rating(movie.getUser_rating());
        m.setBudget(movie.getBudget());
        m.setRelease_date(movie.getRelease_date());
        m.setPoster_url(movie.getPoster_url());
        m.setCast(movie.getCast());
        m.setDirector(DirectorDtoConvertor.convertDirectorToBasic(movie.getDirector()));
        m.setWriter(WriterDtoConvertor.convertWriterToBasic(movie.getWriter()));
        m.setProducer(ProductionCompanyDtoConvertor.convertProdComp(movie.getProducer()));
        
        m.setCreatedBy(UserDtoConvertor.convertUser(movie.getCreatedBy()));
        
        return m;
    }
    
    public static List<MovieDto> convertMovieList(List<Movie> movies){
        List<MovieDto> movies_result = new ArrayList<MovieDto>();
        
        if(movies == null) return movies_result;
        
        for (Movie m : movies) {
            movies_result.add(MovieDtoConvert.convertMovie(m));
        }
        
        return movies_result;
        
    }
    
}
