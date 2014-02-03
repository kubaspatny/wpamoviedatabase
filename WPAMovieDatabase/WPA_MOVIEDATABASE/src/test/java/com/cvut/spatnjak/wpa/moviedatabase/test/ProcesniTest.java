/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.test;

import com.cvut.spatnjak.wpa.moviedatabase.dto.ActorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.AddressDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.MovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.RoleDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.UserDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IActorService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IDirectorService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IMovieService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IProducerService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IUserService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IWriterService;
import java.util.Date;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kuba
 */
public class ProcesniTest extends AbstractServiceTest {

    @Autowired
    private IActorService actorService;
    @Autowired
    private IMovieService movieService;
    @Autowired
    private IDirectorService directorService;
    @Autowired
    private IWriterService writerService;
    @Autowired
    private IProducerService producerService;
    @Autowired
    private IUserService userService;

    @Test
    public void addUserSetAddressDeleteUserTest() {

        Date date = new Date();
        date.setYear(2013 - 1900);
        date.setMonth(12 - 1);
        date.setDate(24);
        Long id = userService.addUser("First1", "Last1", "Username1", "pass1", "mail@mail.com1", date, false);

        UserDto u = userService.getUserById(id);
        Assert.assertNull(u.getAddress());

        String street = "Technicka";
        Integer street_num = 2;
        String city = "Prague";
        Integer city_p_n = 11100;
        String country = "CZ";

        Assert.assertNull(u.getAddress());

        AddressDto a = new AddressDto(street, street_num, city, city_p_n, country);
        u.setAddress(a);
        userService.updateUser(u);
        u = userService.getUserById(id);
        Assert.assertNotNull(u.getAddress());

        userService.deleteUser(id);
        Assert.assertNull(userService.getUserById(id));

    }

    @Test
    public void movieAddEditDelete() {

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

        org.junit.Assert.assertEquals(size + 1, movieService.getAllMovies().size());
        MovieDto m = movieService.getMovieById(id);
        org.junit.Assert.assertEquals(id, m.getId());
        org.junit.Assert.assertEquals(name, m.getName());
        org.junit.Assert.assertEquals(short_info, m.getShort_info());
        org.junit.Assert.assertEquals(long_info, m.getLong_info());
        org.junit.Assert.assertEquals(genre, m.getGenre());
        org.junit.Assert.assertEquals(lenght, m.getMovie_length());
        org.junit.Assert.assertEquals(age_rating, m.getAge_rating());
        org.junit.Assert.assertEquals(budget, m.getBudget());
        org.junit.Assert.assertEquals(year - 1900, m.getRelease_date().getYear());
        org.junit.Assert.assertEquals(month - 1, m.getRelease_date().getMonth());
        org.junit.Assert.assertEquals(day, m.getRelease_date().getDate());
        org.junit.Assert.assertEquals(url, m.getPoster_url());

        String new_name = (System.nanoTime() + "");
        String new_info = (System.nanoTime() + "");

        m.setName(new_name);
        m.setShort_info(new_info);

        movieService.updateMovie(m);

        m = movieService.getMovieById(id);

        Assert.assertEquals(new_name, m.getName());
        Assert.assertEquals(new_info, m.getShort_info());

        movieService.deleteMovie(id);
        Assert.assertNull(movieService.getMovieById(id));

    }

    @Test
    public void ActorAddEditDelete() {

        String first_name = "Emma";
        String last_name = "Watson";
        int year = 1990;
        int month = 5;
        int day = 15;
        String url = "http://ia.media-imdb.com/images/M/MV5BMTQ4ODI5NTY5MF5BMl5BanBnXkFtZTcwMzAyNTQ1OA@@._V1_SX214_CR0,0,214,317_.jpg";
        String bio = "Emma Charlotte Duerre Watson was born in Paris, France to parents, Jacqueline Luesby and Chris Watson.";

        int size = actorService.getAllActors().size();
        long actor_id = actorService.addActor(first_name, last_name, year, month, day, url, bio);

        Assert.assertEquals(size + 1, actorService.getAllActors().size());

        ActorDto a = actorService.getActorById(actor_id);
        Assert.assertEquals(first_name, a.getFirst_name());
        Assert.assertEquals(last_name, a.getLast_name());
        Assert.assertEquals(year - 1900, a.getDate_of_birth().getYear());
        Assert.assertEquals(month - 1, a.getDate_of_birth().getMonth());
        Assert.assertEquals(day, a.getDate_of_birth().getDate());
        Assert.assertEquals(url, a.getImage_url());
        Assert.assertEquals(bio, a.getBiography());

        String new_first_name = "ajsdfhbvpnfciwnsvfbsncoms";
        String new_last_name = "rfbvsfnw0cfnnbsnv[mfcvrbncofmc";
        String new_url = "ewivubfncsmovrnbncmw,ax";

        a.setFirst_name(new_first_name);
        a.setLast_name(new_last_name);
        a.setImage_url(new_url);

        actorService.updateActor(a);

        a = actorService.getActorById(actor_id);

        Assert.assertEquals(new_first_name, a.getFirst_name());
        Assert.assertEquals(new_last_name, a.getLast_name());
        Assert.assertEquals(new_url, a.getImage_url());

        actorService.deleteActor(actor_id);
        Assert.assertNull(actorService.getActorById(actor_id));

    }

    @Test
    public void AddActorAddMovieAddActorToMovieDeleteMovieCheckActorMovies() {

        String first_name = "Emma";
        String last_name = "Watson";
        int year = 1990;
        int month = 5;
        int day = 15;
        String url = "http://ia.media-imdb.com/images/M/MV5BMTQ4ODI5NTY5MF5BMl5BanBnXkFtZTcwMzAyNTQ1OA@@._V1_SX214_CR0,0,214,317_.jpg";
        String bio = "Emma Charlotte Duerre Watson was born in Paris, France to parents, Jacqueline Luesby and Chris Watson.";

        long actor_id = actorService.addActor(first_name, last_name, year, month, day, url, bio);

        String name = "The Hunger Games: Catching Fire 2";
        String short_info = "Katniss Everdeen voluntarily takes her younger sister's place in the Hunger Games, a televised fight to the death in which two teenagers from each of the twelve Districts of Panem are chosen at random to compete. ";
        String long_info = "In a dystopian future, the totalitarian nation of Panem is divided between 12 districts and the Capitol. Each year two young representatives from each district are selected by lottery to participate in The Hunger Games. Part entertainment, part brutal retribution for a past rebellion, the televised games are broadcast throughout Panem. The 24 participants are forced to eliminate their competitors while the citizens of Panem are required to watch. When 16-year-old Katniss's young sister, Prim, is selected as District 12's female representative, Katniss volunteers to take her place. She and her male counterpart Peeta, are pitted against bigger, stronger representatives, some of whom have trained for this their whole lives.";
        String genre = "Adventure";
        int lenght = 142;
        String age_rating = "PG-13";
        float rating = 0f;
        int budget = 78000000;

        long movie_id = movieService.addMovie(name, short_info, long_info, genre, lenght, age_rating, budget, year, month, day, url);

        String role_name = "idabfsdln";
        movieService.addActorRoleToMovie(movie_id, actor_id, role_name);

        MovieDto m = movieService.getMovieById(movie_id);
        Assert.assertEquals(1, m.getCast().size());
        RoleDto r = m.getCast().get(0);

        Assert.assertEquals(actor_id, (long) r.actor.getId());
        Assert.assertEquals(role_name, r.role_name);

        movieService.deleteMovie(movie_id);
        Assert.assertNull(movieService.getMovieById(movie_id));

        ActorDto a = actorService.getActorById(actor_id);
        Assert.assertEquals(0, a.getMovies().size());

    }

    @Test
    public void AddUserCreateMovieDeleteUser() {
        
        Date date = new Date();
        date.setYear(2013 - 1900);
        date.setMonth(12 - 1);
        date.setDate(24);
        Long id = userService.addUser("First1", "Last1", "Username1", "pass1", "mail@mail.com1", date, false);

        UserDto u = userService.getUserById(id);
        Assert.assertNull(u.getAddress());

        String name = "The Hunger Games: Catching Fire 2";
        String short_info = "Katniss Everdeen voluntarily takes her younger sister's place in the Hunger Games, a televised fight to the death in which two teenagers from each of the twelve Districts of Panem are chosen at random to compete. ";
        String long_info = "In a dystopian future, the totalitarian nation of Panem is divided between 12 districts and the Capitol. Each year two young representatives from each district are selected by lottery to participate in The Hunger Games. Part entertainment, part brutal retribution for a past rebellion, the televised games are broadcast throughout Panem. The 24 participants are forced to eliminate their competitors while the citizens of Panem are required to watch. When 16-year-old Katniss's young sister, Prim, is selected as District 12's female representative, Katniss volunteers to take her place. She and her male counterpart Peeta, are pitted against bigger, stronger representatives, some of whom have trained for this their whole lives.";
        String genre = "Adventure";
        int lenght = 142;
        String age_rating = "PG-13";
        float rating = 0f;
        int budget = 78000000;
        int year = 1990;
        int month = 5;
        int day = 15;
        String url = "fdhosnfcksmd.com";

        long movie_id = movieService.addMovie(name, short_info, long_info, genre, lenght, age_rating, budget, year, month, day, url);

        MovieDto m = movieService.getMovieById(movie_id);
        m.setCreatedBy(u);
        movieService.updateMovie(m);
        
        m = movieService.getMovieById(movie_id);
        
        userService.deleteUser(id);
        Assert.assertNull(userService.getUserById(id));
        
        m = movieService.getMovieById(movie_id);
        Assert.assertNull(m.getCreatedBy());

    }
}