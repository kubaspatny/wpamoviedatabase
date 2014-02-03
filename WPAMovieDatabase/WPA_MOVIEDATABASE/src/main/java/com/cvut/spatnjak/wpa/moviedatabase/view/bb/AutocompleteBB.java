/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicActorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicDirectorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicWriterDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.ProductionCompanyDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IActorService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IDirectorService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IProducerService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IWriterService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba
 */

@Component
@Scope("application")
public class AutocompleteBB {
    
    List<BasicDirectorDto> directors;
    List<BasicWriterDto> writers;
    List<ProductionCompanyDto> producers;
    List<BasicActorDto> actors;
    
    @Autowired
    private IDirectorService directorService;
    
    @Autowired
    private IWriterService writerService;
    
    @Autowired
    private IProducerService producerService;
    
    @Autowired
    private IActorService actorService;
    
    @PostConstruct
    public void init(){
        updateActors();
        updateDirectors();
        updateProducers();
        updateWriters();
    }
    
    public List<String> completeDir(Object o){
        String query = (String) o;
        List<String> result = new ArrayList<String>();
        for (BasicDirectorDto d : directors) {
            if(d.getFullName().toLowerCase().contains(query.toLowerCase())){
            result.add(d.toString()); 
            }
        }
        return result;
    }
    
    public List<String> completeWr(Object o){
        String query = (String) o;
        List<String> result = new ArrayList<String>();
        for (BasicWriterDto w : writers) {
            if(w.getFullName().toLowerCase().contains(query.toLowerCase())){
                result.add(w.toString()); 
            }
        }
        return result;
    }
    
    public List<String> completeProd(Object o){
        String query = (String) o;
        List<String> result = new ArrayList<String>();
        for (ProductionCompanyDto p : producers) {
            if(p.getName().toLowerCase().contains(query.toLowerCase())){
                result.add(p.toString()); 
            }
        }
        return result;
    }
    
    public List<String> completeAct(Object o){
        String query = (String) o;
        List<String> result = new ArrayList<String>();
        for (BasicActorDto a : actors) {
            if(a.getFullName().toLowerCase().contains(query.toLowerCase())){
                result.add(a.toString()); 
            }
        }
        return result;
    }
    
    public void updateActors(){
        actors = actorService.getAllBasicActors();
    }
    public void updateDirectors(){
        directors = directorService.getAllBasicDirectors();
    }
    public void updateWriters(){
        writers = writerService.getAllBasicWriters();
    }
    public void updateProducers(){
        producers = producerService.getAllProducers();
    }

}
