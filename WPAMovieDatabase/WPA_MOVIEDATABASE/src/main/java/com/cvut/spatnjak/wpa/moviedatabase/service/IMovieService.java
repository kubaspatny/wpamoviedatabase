package com.cvut.spatnjak.wpa.moviedatabase.service;

import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicMovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.MovieDto;
import java.util.Date;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kuba
 */

@Transactional
public interface IMovieService {
    
    @Transactional(readOnly=true)
    public List<MovieDto> getAllMovies();
    
    @Transactional(readOnly=true)
    public MovieDto getMovieById(Long movieId);
    
    @Transactional(readOnly=true)
    public List<MovieDto> getAllMoviesOrderByRating(); // descending
    
    @Transactional(readOnly=true)
    public List<MovieDto> getTop10();
    
    @Transactional(readOnly=true)
    public List<MovieDto> getTop10Genre(String genre);
    
    @Transactional(readOnly=true)
    public List<MovieDto> getLatest4();
    
    @Transactional(readOnly=true)
    public List<BasicMovieDto> searchByName(String name);
    
    public Long addMovie(String name,
            String short_info,
            String long_info,
            String genre,
            int movie_length,
            String age_rating,
            int budget,
            int year, int month, int day,
            String poster_url); // + add createdBy
    
    public Long addMovie(String name,
            String short_info,
            String long_info,
            String genre,
            int movie_length,
            String age_rating,
            int budget,
            Date date,
            String poster_url);
    
    public Long addMovie(String name,
            String short_info,
            String long_info,
            String genre,
            int movie_length,
            String age_rating,
            int budget,
            Date date,
            String poster_url,
            long createdBy);
    
    public void updateMovie(MovieDto movie);
    
    public void deleteMovie(Long movieId);
    
    public void addActorRoleToMovie(Long movieId, Long actorID, String role);
    
    public void removeActorFromMovie(Long movieID, Long actorID);
    
    public void addDirectorToMovie(Long movieID, Long directorID);
    
    public void removeDirectorFromMovie(Long movieID, Long directorID);
    
    public void removeDirectorFromMovie(Long movieID);
    
    public void addWriterToMovie(Long movieID, Long writerID);
    
    public void removeWriterFromMovie(Long movieID, Long writerID);
    
    public void removeWriterFromMovie(Long movieID);
    
    public void addProducerToMovie(Long movieID, Long producerID);
    
    public void removeProducerFromMovie(Long movieID, Long producerID);
    
    public void removeProducerFromMovie(Long movieID);
    
    public void addEditorToMovie(Long movieID, Long userID);
    
    public void rateMovie(Long movieID, Long userID, int rating);

    
}
