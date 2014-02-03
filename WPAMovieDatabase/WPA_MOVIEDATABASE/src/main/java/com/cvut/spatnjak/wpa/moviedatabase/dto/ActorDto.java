/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.dto;

import com.cvut.spatnjak.wpa.moviedatabase.bo.Movie;
import com.cvut.spatnjak.wpa.moviedatabase.convert.BasicMovieDtoConvert;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Kuba
 */
public class ActorDto extends PersonDto {
    
    private Set<BasicMovieDto> movies;

    public ActorDto() {
    }

    public ActorDto(Long id, String first_name, String last_name, Date date_of_birth, String image_url, String biography, Set<BasicMovieDto> movies) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.image_url = image_url;
        this.biography = biography;
        this.movies = movies;
    }

    public Set<BasicMovieDto> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = BasicMovieDtoConvert.convertMovies(movies);
    }
    
}
