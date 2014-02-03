/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.test;

import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicMovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.WriterDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IMovieService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IWriterService;
import java.util.List;
import java.util.Set;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kuba
 */
public class WriterServiceTest extends AbstractServiceTest {
    
    @Autowired
    private IWriterService writerService;
    
    @Autowired
    private IMovieService movieService;
    
    public WriterServiceTest() {
        super();
    }
    
    @Test
    public void empty(){}
    
    
    @Test
    public void testAdd(){
       
        int size = writerService.getAllWriters().size();
        String first_name = "Writer";
        String last_name = "Sample";
        int year = 1967;
        int month = 11;
        int day = 2;
        String url = "http://ia.media-imdb.com/images/M/MV5BMTkwOTM0MDA4OV5BMl5BanBnXkFtZTcwNzIzNTEzMg@@._V1_SY317_CR7,0,214,317_.jpg";
        String bio = "Born in Keighley, West Yorkshire. He attended Malsis School in Cross Hills Ermysted's Grammar School at Skipton, later he attended Sedbergh School in Cumbria.";

        Long id = writerService.addWriter(first_name, last_name, year, month, day, url, bio);
        
        Assert.assertEquals(size+1, writerService.getAllWriters().size());
        
        WriterDto d = writerService.getWriterById(id);
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
       int size = writerService.getAllWriters().size();
       writerService.deleteWriter(141l);
       Assert.assertEquals(size-1, writerService.getAllWriters().size());
    }
    
        
    //@Test
    public void testGetAll(){
        List<WriterDto> writers = writerService.getAllWriters();
        System.out.println("--- ALL WRITERS ---");
        for (WriterDto w : writers) {
            System.out.println("Name: " + w.getFirst_name() + " " + w.getLast_name());
            System.out.println("Date of birth: " + w.getDate_of_birth());
            Set<BasicMovieDto> m = w.getWritten_movies();
            System.out.println("Movies:");
            for (BasicMovieDto b : m) {
                System.out.println(b.getName());
            }
            System.out.println("------");
        }
    }
    
    @Test
    public void getWriterByID(){
        
        WriterDto d = writerService.getWriterById(141l);
        String exp_firstname = "Matthew";
        String exp_lastname = "Vaughn";
        Long exp_id = 141l;
        
        Assert.assertEquals(exp_id, d.getId());
        Assert.assertEquals(exp_firstname, d.getFirst_name());
        Assert.assertEquals(exp_lastname, d.getLast_name());
        
    }
    
    @Test
    public void testUpdateActor(){
        String new_name = "sdkbfdn213nk";
        WriterDto d = writerService.getWriterById(141l);
        Assert.assertFalse(d.getFirst_name().equals(new_name));
        
        d.setFirst_name(new_name);
        writerService.updateWriter(d);
        
        WriterDto d2 = writerService.getWriterById(141l);
        Assert.assertEquals(new_name, d2.getFirst_name());
        
    }
    
}
