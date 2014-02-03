/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.test;

import com.cvut.spatnjak.wpa.moviedatabase.dto.ActorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicMovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IActorService;
import java.util.List;
import java.util.Set;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kuba
 */
public class ActorServiceTest extends AbstractServiceTest {
    
    @Autowired
    private IActorService actorService;
    
    public ActorServiceTest() {
        super();
    }
    
    @Test
    public void empty(){}
    
    @Test
    public void testAddActor(){
        
        String first_name = "Emma";
        String last_name = "Watson";
        int year = 1990;
        int month = 5;
        int day = 15;
        String url = "http://ia.media-imdb.com/images/M/MV5BMTQ4ODI5NTY5MF5BMl5BanBnXkFtZTcwMzAyNTQ1OA@@._V1_SX214_CR0,0,214,317_.jpg";
        String bio = "Emma Charlotte Duerre Watson was born in Paris, France to parents, Jacqueline Luesby and Chris Watson.";
        
        int size = actorService.getAllActors().size();
        long actor_id = actorService.addActor(first_name, last_name, year, month, day, url, bio);
        
        Assert.assertEquals(size+1, actorService.getAllActors().size());
        
        ActorDto a = actorService.getActorById(actor_id);
        Assert.assertEquals(first_name, a.getFirst_name());
        Assert.assertEquals(last_name, a.getLast_name());
        Assert.assertEquals(year-1900, a.getDate_of_birth().getYear());
        Assert.assertEquals(month-1, a.getDate_of_birth().getMonth());
        Assert.assertEquals(day, a.getDate_of_birth().getDate());
        Assert.assertEquals(url, a.getImage_url());
        Assert.assertEquals(bio, a.getBiography());
        
    }
    
    @Test
    public void getActorByID(){
        
        ActorDto a = actorService.getActorById(172l);
        String exp_firstname = "Jennifer";
        String exp_lastname = "Lawrence";
        Long exp_id = 172l;
        
        Assert.assertEquals(exp_id, a.getId());
        Assert.assertEquals(exp_firstname, a.getFirst_name());
        Assert.assertEquals(exp_lastname, a.getLast_name());
        
    }
    
    @Test
    public void testUpdateActor(){
        String new_name = "sdkbfdn213nk";
        ActorDto a = actorService.getActorById(172l);
        Assert.assertFalse(a.getFirst_name().equals(new_name));
        
        a.setFirst_name(new_name);
        actorService.updateActor(a);
        
        ActorDto b = actorService.getActorById(172l);
        Assert.assertEquals(new_name, a.getFirst_name());
        
    }
    
    @Test
    public void testRemoveActor(){
 
        ActorDto a = actorService.getActorById(172l);
        Assert.assertNotNull(a);
        
        actorService.deleteActor(172l);
        
        ActorDto b = actorService.getActorById(172l);
        Assert.assertNull(b);
        
    }   
        
}
