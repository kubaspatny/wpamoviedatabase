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
public class WriterDto extends PersonDto {
    
    private Set<BasicMovieDto> written_movies;
    
    public WriterDto() {
    }

    public WriterDto(Long id, String first_name, String last_name, Date date_of_birth, String image_url, String biography, Set<BasicMovieDto> directed_movies) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.image_url = image_url;
        this.biography = biography;
        this.written_movies = directed_movies;
    }

    public Set<BasicMovieDto> getWritten_movies() {
        return written_movies;
    }

    public void setWritten_movies(Set<Movie> written_movies) {
        this.written_movies = BasicMovieDtoConvert.convertMovies(written_movies);;
    }
    
}
