/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.test;

import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicMovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.DirectorDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IDirectorService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IMovieService;
import java.util.Set;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kuba
 */
public class DirectorServiceTest extends AbstractServiceTest {
    
    @Autowired
    private IDirectorService directorService;
    
    @Autowired
    private IMovieService movieService;
    
    public DirectorServiceTest() {
        super();
    }
    
    @Test
    public void empty(){}
    
    @Test
    public void testAdd(){
        
        int size = directorService.getAllDirectors().size();
        String first_name = "Director";
        String last_name = "Sample";
        int year = 1967;
        int month = 11;
        int day = 2;
        String url = "http://ia.media-imdb.com/images/M/MV5BMTkwOTM0MDA4OV5BMl5BanBnXkFtZTcwNzIzNTEzMg@@._V1_SY317_CR7,0,214,317_.jpg";
        String bio = "Born in Keighley, West Yorkshire. He attended Malsis School in Cross Hills Ermysted's Grammar School at Skipton, later he attended Sedbergh School in Cumbria.";

        Long id = directorService.addDirector(first_name, last_name, year, month, day, url, bio);
        
        Assert.assertEquals(size+1, directorService.getAllDirectors().size());
        
        DirectorDto d = directorService.getDirectorById(id);
        Assert.assertEquals(first_name, d.getFirst_name());
        Assert.assertEquals(last_name, d.getLast_name());
        Assert.assertEquals(year - 1900, d.getDate_of_birth().getYear());
        Assert.assertEquals(month - 1, d.getDate_of_birth().getMonth());
        Assert.assertEquals(day, d.getDate_of_birth().getDate());
        Assert.assertEquals(url, d.getImage_url());
        Assert.assertEquals(bio, d.getBiography());
        
    }
    
    @Test
    public void testRemove(){
        int size = directorService.getAllDirectors().size();
        
        directorService.deleteDirector(142l);
        
        Assert.assertEquals(size-1, directorService.getAllDirectors().size());
    }
    
    @Test
    public void getDirectorByID(){
        
        DirectorDto d = directorService.getDirectorById(142l);
        String exp_firstname = "Simon";
        String exp_lastname = "Beaufoy";
        Long exp_id = 142l;
        
        Assert.assertEquals(exp_id, d.getId());
        Assert.assertEquals(exp_firstname, d.getFirst_name());
        Assert.assertEquals(exp_lastname, d.getLast_name());
        
    }
    
    @Test
    public void testUpdateDirector(){
        String new_name = "sdkbfdn213nk";
        DirectorDto d = directorService.getDirectorById(142l);
        Assert.assertFalse(d.getFirst_name().equals(new_name));
        
        d.setFirst_name(new_name);
        directorService.updateDirector(d);
        
        DirectorDto d2 = directorService.getDirectorById(142l);
        Assert.assertEquals(new_name, d2.getFirst_name());
        
    }
    
    
    //@Test
    public void test(){
        DirectorDto d = directorService.getDirectorById(142l);
        System.out.println("Name: " + d.getFirst_name() + " " + d.getLast_name());
        System.out.println("Biography: " + d.getBiography());
        System.out.println("URL: " + d.getImage_url());
        System.out.println("Movies:");
        Set<BasicMovieDto> movies = d.getDirected_movies();
        for (BasicMovieDto m : movies) {
            System.out.println(m.getName() + "(" + m.getId() + ")");
        }
    }
    
}
