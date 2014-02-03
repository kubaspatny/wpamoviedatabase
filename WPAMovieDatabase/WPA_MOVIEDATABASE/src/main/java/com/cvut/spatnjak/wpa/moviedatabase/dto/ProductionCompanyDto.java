/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.dto;

import com.cvut.spatnjak.wpa.moviedatabase.bo.Movie;
import com.cvut.spatnjak.wpa.moviedatabase.convert.BasicMovieDtoConvert;
import java.util.Set;

/**
 *
 * @author Kuba
 */
public class ProductionCompanyDto extends AbstractDto {
    
    private String name;
    private Set<BasicMovieDto> produced_movies;
    
    public ProductionCompanyDto(){
    }

    public ProductionCompanyDto(Long id, String name, Set<BasicMovieDto> produced_movies) {
        this.id = id;
        this.name = name;
        this.produced_movies = produced_movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BasicMovieDto> getProduced_movies() {
        return produced_movies;
    }

    public void setProduced_movies(Set<Movie> produced_movies) {
        this.produced_movies = BasicMovieDtoConvert.convertMovies(produced_movies);
    }

    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
    
    
    
}
