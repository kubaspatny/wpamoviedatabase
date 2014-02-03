/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.test;

import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicDirectorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicWriterDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.MovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.ProductionCompanyDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.RoleDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.UserDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IMovieService;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kuba
 */
public class MovieServiceTest extends AbstractServiceTest {
    
    @Autowired
    private IMovieService movieService;
    
    public MovieServiceTest() {
        super();
    }
    
    @Test
    public void testGetNonExistent(){        
        MovieDto m = movieService.getMovieById(5484351121l);
        System.out.println("m == null: " + (m==null));        
    }
    
    @Test
    public void testAddMovie(){
        
        int size = movieService.getAllMovies().size();
        String name = "The Hunger Games: Catching Fire 2";
        String short_info = "Katniss Everdeen voluntarily takes her younger sister's place in the Hunger Games, a televised fight to the death in which two teenagers from each of the twelve Districts of Panem are chosen at random to compete. ";
        String long_info = "In a dystopian future, the totalitarian nation of Panem is divided between 12 districts and the Capitol. Each year two young representatives from each district are selected by lottery to participate in The Hunger Games. Part entertainment, part brutal retribution for a past rebellion, the televised games are broadcast throughout Panem. The 24 participants are forced to eliminate their competitors while the citizens of Panem are required to watch. When 16-year-old Katniss's young sister, Prim, is selected as District 12's female representative, Katniss volunteers to take her place. She and her male counterpart Peeta, are pitted against bigger, stronger representatives, some of whom have trained for this their whole lives.";
        String genre = "Adventure";
        int lenght = 142;
        String age_rating = "PG-13";
        float rating = 0f;
        int budget = 78000000;
        int year = 2013;
        int month = 11;
        int day = 22;
        String url = "http://ia.media-imdb.com/images/M/MV5BMjA4NDg3NzYxMF5BMl5BanBnXkFtZTcwNTgyNzkyNw@@._V1_SX214_.jpg";
        
        Long id = movieService.addMovie(name, short_info, long_info, genre, lenght, age_rating, budget, year, month, day, url);
        
        Assert.assertEquals(size+1, movieService.getAllMovies().size());
        MovieDto m = movieService.getMovieById(id);
        Assert.assertEquals(id, m.getId());
        Assert.assertEquals(name, m.getName());
        Assert.assertEquals(short_info, m.getShort_info());
        Assert.assertEquals(long_info, m.getLong_info());
        Assert.assertEquals(genre, m.getGenre());
        Assert.assertEquals(lenght, m.getMovie_length());
        Assert.assertEquals(age_rating, m.getAge_rating());
        Assert.assertEquals(budget, m.getBudget());
        Assert.assertEquals(year - 1900, m.getRelease_date().getYear());
        Assert.assertEquals(month - 1, m.getRelease_date().getMonth());
        Assert.assertEquals(day, m.getRelease_date().getDate());
        Assert.assertEquals(url, m.getPoster_url());
        
    }
    
    @Test
    public void testGetMovieByID(){
        
       String name = "The Hunger Games";
       MovieDto m = movieService.getMovieById(1315l);
       
       Assert.assertEquals((Long)1315l, m.getId());
       Assert.assertEquals(name, m.getName());
        
    }
    
    @Test
    public void testGetByRating(){
        List<MovieDto> m = movieService.getAllMoviesOrderByRating();
        System.out.println("\n\n--- SORTED BY RATING ---");
        for (MovieDto movie : m) {
            System.out.println(movie.getUser_rating() + " " + movie.getName());
        }
        System.out.println("------------------------\n\n");
        for (int i = 0; i < m.size()-1; i++) {
           Assert.assertTrue(m.get(i).getUser_rating() >= m.get(i+1).getUser_rating());
        }
        
    }
    
    @Test
    public void testGetLatest4(){
       List<MovieDto> m = movieService.getLatest4();
       Assert.assertTrue(m.size() >= 0 && m.size() <= 4);
    }
    
    @Test
    public void testDeleteMovie(){
        int size = movieService.getAllMovies().size();
        movieService.deleteMovie(1503l);
        Assert.assertEquals(size-1, movieService.getAllMovies().size());
        
        Assert.assertNull(movieService.getMovieById(1503l));
    }
    
    @Test
    public void testUpdate(){
        
        MovieDto m = movieService.getMovieById(1503l);
        String name = "jhfosnfn161681s";
        Assert.assertFalse(m.getName().equals(name));
        
        m.setName(name);
        movieService.updateMovie(m);
        
        m = movieService.getMovieById(1503l);
        Assert.assertEquals(name, m.getName());
        
    }
    
    @Test
    public void testAddActorToRoleInMovie(){
        
        MovieDto m = movieService.getMovieById(1503l);
        Long actorID = 1789l;
        boolean containsActor = false;
        
        for(RoleDto d : m.getCast()){
            if(d.getActor().getId().equals(actorID)){
                containsActor = true;
            }
        }
        
        Assert.assertFalse(containsActor);
        
        movieService.addActorRoleToMovie(1503l, 1789l, "Sample role");
        
        m = movieService.getMovieById(1503l);
        
        containsActor = false;
        for(RoleDto d : m.getCast()){
            if(d.getActor().getId().equals(actorID)){
                containsActor = true;
            }
        }
        Assert.assertTrue(containsActor);
        
    }
    
       
    
    
    //@Test
    public void testSetWriterDirectorProducer(){
        
        movieService.addDirectorToMovie(1503l, 142l);
        MovieDto m = movieService.getMovieById(1503l);
        Assert.assertEquals((Long)142l, m.getDirector().getId());
        
        movieService.addWriterToMovie(1503l, 141l);
        m = movieService.getMovieById(1503l);
        Assert.assertEquals((Long)141l, m.getWriter().getId());
        
        movieService.addProducerToMovie(1503l, 2057l);
        m = movieService.getMovieById(1503l);
        Assert.assertEquals((Long)2057l, m.getProducer().getId());
        
    }
    
    @Test
    public void testAddCreatedBy(){
        movieService.addEditorToMovie(1503l, 168l);
        MovieDto m = movieService.getMovieById(1503l);
        Assert.assertEquals((Long)168l, m.getCreatedBy().getId());
    }
    
    
}
