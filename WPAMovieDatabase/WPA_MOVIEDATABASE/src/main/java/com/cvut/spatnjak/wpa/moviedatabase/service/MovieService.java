/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.service;

import com.cvut.spatnjak.wpa.moviedatabase.bo.Actor;
import com.cvut.spatnjak.wpa.moviedatabase.bo.Director;
import com.cvut.spatnjak.wpa.moviedatabase.bo.Movie;
import com.cvut.spatnjak.wpa.moviedatabase.bo.ProductionCompany;
import com.cvut.spatnjak.wpa.moviedatabase.bo.User;
import com.cvut.spatnjak.wpa.moviedatabase.bo.Writer;
import com.cvut.spatnjak.wpa.moviedatabase.convert.BasicMovieDtoConvert;
import com.cvut.spatnjak.wpa.moviedatabase.convert.MovieDtoConvert;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicMovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.MovieDto;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba
 */
@Component
public class MovieService extends AbstractDataAccessService implements IMovieService {

    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = genericDao.getAll(Movie.class);
        return MovieDtoConvert.convertMovieList(movies);
    }

    @Override
    public MovieDto getMovieById(Long movieId) {
        Movie m = genericDao.getById(movieId, Movie.class);
        return MovieDtoConvert.convertMovie(m);
    }

    @Override
    public List<MovieDto> getAllMoviesOrderByRating() {
        List<Movie> movies = genericDao.getAllOrderedDesc("user_rating", Movie.class);
        return MovieDtoConvert.convertMovieList(movies);
    }

    @Override
    public List<MovieDto> getTop10() {
        List<Movie> movies = genericDao.getTopN("user_rating", 10, Movie.class);
        return MovieDtoConvert.convertMovieList(movies);
    }
    
    @Override
    public List<MovieDto> getTop10Genre(String genre) {
        List<Movie> movies = genericDao.getByPropertyTopN("genre", genre, 10, "user_rating", Movie.class);
        return MovieDtoConvert.convertMovieList(movies);
    }

    @Override
    public List<MovieDto> getLatest4() {
        List<Movie> movies = genericDao.getTopN("id", 4, Movie.class);
        return MovieDtoConvert.convertMovieList(movies);
    }
    
    @Override
    public List<BasicMovieDto> searchByName(String name) {
        List<Movie> movies = genericDao.searchByProperty("name", name, Movie.class);
        return BasicMovieDtoConvert.convertMovies(movies);
    }

    @Override
    public Long addMovie(String name, String short_info, String long_info, String genre, int movie_length, String age_rating, int budget, int year, int month, int day, String poster_url) {
        Movie m = new Movie();
        m.setName(name);
        m.setShort_info(short_info);
        m.setLong_info(long_info);
        m.setGenre(genre);
        m.setMovie_length(movie_length);
        m.setAge_rating(age_rating);
        m.setUser_rating(0);
        m.setBudget(budget);
        m.setRelease_date(new Date(year - 1900, month - 1, day));
        m.setPoster_url(poster_url);
        m.setNum_user_rated(0);

        return genericDao.saveOrUpdate(m).getId();
    }
    
    @Override
    public Long addMovie(String name, String short_info, String long_info, String genre, int movie_length, String age_rating, int budget, Date date, String poster_url) {
        Movie m = new Movie();
        m.setName(name);
        m.setShort_info(short_info);
        m.setLong_info(long_info);
        m.setGenre(genre);
        m.setMovie_length(movie_length);
        m.setAge_rating(age_rating);
        m.setUser_rating(0);
        m.setBudget(budget);
        m.setRelease_date(date);
        m.setPoster_url(poster_url);
        m.setNum_user_rated(0);

        return genericDao.saveOrUpdate(m).getId();
    }
    
    @Override
    public Long addMovie(String name, String short_info, String long_info, String genre, int movie_length, String age_rating, int budget, Date date, String poster_url, long ceatedBy) {
        Movie m = new Movie();
        m.setName(name);
        m.setShort_info(short_info);
        m.setLong_info(long_info);
        m.setGenre(genre);
        m.setMovie_length(movie_length);
        m.setAge_rating(age_rating);
        m.setUser_rating(0);
        m.setBudget(budget);
        m.setRelease_date(date);
        m.setPoster_url(poster_url);
        m.setNum_user_rated(0);
        User u = new User();
        u.setId(ceatedBy);
        m.setCreatedBy(u);

        return genericDao.saveOrUpdate(m).getId();
    }

    @Override
    public void updateMovie(MovieDto movie) {
        if (movie == null) {
            return;
        }
        Movie m = genericDao.getById(movie.getId(), Movie.class);

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

        if (movie.getDirector() != null) {
            this.addDirectorToMovie(m.getId(), movie.getDirector().getId());
        }

    }

    @Override
    public void deleteMovie(Long movieId) {
        genericDao.removeById(movieId, Movie.class);
    }

    @Override
    public void addActorRoleToMovie(Long movieID, Long actorID, String role) {
        
        if(movieID == null || actorID == null || role == null || role.isEmpty()) return;
        Movie m = genericDao.getById(movieID, Movie.class);

        Actor a = new Actor();
        a.setId(actorID);
        m.addCastMemeber(role, a);

    }

    @Override
    public void removeActorFromMovie(Long movieID, Long actorID) {

        Movie m = genericDao.getById(movieID, Movie.class);

        Actor a = new Actor();
        a.setId(actorID);

        m.removeCastMember(a);

    }

    @Override
    public void addDirectorToMovie(Long movieID, Long directorID) {
        if (movieID == null || directorID == null) {
            return;
        }
        Movie m = genericDao.getById(movieID, Movie.class);
        Director d = new Director();
        d.setId(directorID);
        m.setDirector(d);
    }

    @Override
    public void removeDirectorFromMovie(Long movieID, Long directorID) {
        if (movieID == null || directorID == null) {
            return;
        }
        Movie m = genericDao.getById(movieID, Movie.class);
        Director d = new Director();
        d.setId(directorID);
        m.removeDirector(d);
    }

    @Override
    public void removeDirectorFromMovie(Long movieID) {
        if (movieID == null) {
            return;
        }
        Movie m = genericDao.getById(movieID, Movie.class);
        if (m == null) {
            return;
        }
        m.setDirector(null);
    }

    @Override
    public void addWriterToMovie(Long movieID, Long writerID) {
        if (movieID == null || writerID == null) {
            return;
        }
        Movie m = genericDao.getById(movieID, Movie.class);
        Writer w = new Writer();
        w.setId(writerID);
        m.setWriter(w);
    }

    @Override
    public void removeWriterFromMovie(Long movieID, Long writerID) {
        if (movieID == null || writerID == null) {
            return;
        }
        Movie m = genericDao.getById(movieID, Movie.class);
        Writer w = new Writer();
        w.setId(writerID);
        m.removeWriter(w);
    }

    @Override
    public void removeWriterFromMovie(Long movieID) {
        if (movieID == null) {
            return;
        }
        Movie m = genericDao.getById(movieID, Movie.class);
        if (m == null) {
            return;
        }
        m.setWriter(null);
    }

    @Override
    public void addProducerToMovie(Long movieID, Long producerID) {
        if (movieID == null || producerID == null) {
            return;
        }
        Movie m = genericDao.getById(movieID, Movie.class);
        ProductionCompany p = new ProductionCompany();
        p.setId(producerID);
        m.setProducer(p);
    }

    @Override
    public void removeProducerFromMovie(Long movieID, Long producerID) {
        if (movieID == null || producerID == null) {
            return;
        }
        Movie m = genericDao.getById(movieID, Movie.class);
        ProductionCompany p = new ProductionCompany();
        p.setId(producerID);
        m.removeProducer(p);
    }

    @Override
    public void removeProducerFromMovie(Long movieID) {
        if (movieID == null) {
            return;
        }
        Movie m = genericDao.getById(movieID, Movie.class);
        if (m == null) {
            return;
        }
        m.setProducer(null);
    }

    @Override
    public void addEditorToMovie(Long movieID, Long userID) {
        if (movieID == null || userID == null) {
            return;
        }
        Movie m = genericDao.getById(movieID, Movie.class);
        User u = new User();
        u.setId(userID);
        m.setCreatedBy(u);
    }

    @Override
    public void rateMovie(Long movieID, Long userID, int rating) {
        if (movieID == null || userID == null || rating < 1 || rating > 10) {
            return;
        }
        Movie m = genericDao.getById(movieID, Movie.class);
        User u = genericDao.getById(userID, User.class);
        
        if(!u.hasRated(movieID)){
            
            float current_rating = m.getUser_rating();
            int num_raters = m.getNum_user_rated();
            
            float sum = current_rating * num_raters;
            
            float new_rating = (sum+rating) / (float)(++num_raters);
            m.setUser_rating(new_rating);
            m.setNum_user_rated(num_raters);
            
            u.addRatedMovie(m.getId());
            
        }

    }
;
}
