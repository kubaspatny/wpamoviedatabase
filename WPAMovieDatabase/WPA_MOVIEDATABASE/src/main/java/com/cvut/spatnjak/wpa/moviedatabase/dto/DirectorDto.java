package com.cvut.spatnjak.wpa.moviedatabase.dto;

import com.cvut.spatnjak.wpa.moviedatabase.bo.Movie;
import com.cvut.spatnjak.wpa.moviedatabase.convert.BasicMovieDtoConvert;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Kuba Spatny
 */
public class DirectorDto extends PersonDto {
    
    private Set<BasicMovieDto> directed_movies;
    
    public DirectorDto() {
    }

    public DirectorDto(Long id, String first_name, String last_name, Date date_of_birth, String image_url, String biography, Set<BasicMovieDto> directed_movies) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date_of_birth = date_of_birth;
        this.image_url = image_url;
        this.biography = biography;
        this.directed_movies = directed_movies;
    }

    public Set<BasicMovieDto> getDirected_movies() {
        return directed_movies;
    }

    public void setDirected_movies(Set<Movie> directed_movies) {
        this.directed_movies = BasicMovieDtoConvert.convertMovies(directed_movies);
    }
    
}
