package com.cvut.spatnjak.wpa.moviedatabase.service;

import com.cvut.spatnjak.wpa.moviedatabase.bo.Actor;
import com.cvut.spatnjak.wpa.moviedatabase.convert.ActorDtoConvertor;
import com.cvut.spatnjak.wpa.moviedatabase.dto.ActorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicActorDto;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba Spatny
 */
@Component
public class ActorService extends AbstractDataAccessService implements IActorService {

    @Override
    public List<ActorDto> getAllActors() {
        List<Actor> actors = genericDao.getAll(Actor.class);
        return ActorDtoConvertor.convertActorList(actors);
    }
    
    @Override
    public List<BasicActorDto> getAllBasicActors() {
        List<Actor> actors = genericDao.getAll(Actor.class);
        return ActorDtoConvertor.convertActorBasicList(actors);
    }
    
    @Override
    public List<BasicActorDto> searchActorByFirstName(String name){
        List<Actor> actors = genericDao.searchByProperty("first_name", name, Actor.class);
        return ActorDtoConvertor.convertActorBasicList(actors);
    }
    
    @Override
    public List<BasicActorDto> searchActorByLastName(String name){
        List<Actor> actors = genericDao.searchByProperty("last_name", name, Actor.class);
        return ActorDtoConvertor.convertActorBasicList(actors);
    }

    @Override
    public ActorDto getActorById(Long actorId) {       
        Actor a = genericDao.getById(actorId, Actor.class);
        return ActorDtoConvertor.convertActorToFull(a);
    }

    @Override
    public Long addActor(String first_name, String last_name, int year, int month, int day, String image_url, String biography) {
        Actor a = new Actor();
        a.setFirst_name(first_name);
        a.setLast_name(last_name);
        a.setDate_of_birth(year, month, day);
        a.setImage_url(image_url);
        a.setBiography(biography);
        
        return genericDao.saveOrUpdate(a).getId();
    }
    
    @Override
    public Long addActor(String first_name, String last_name, Date dob, String image_url, String biography) {
        Actor a = new Actor();
        a.setFirst_name(first_name);
        a.setLast_name(last_name);
        a.setDate_of_birth(dob);
        a.setImage_url(image_url);
        a.setBiography(biography);
        
        return genericDao.saveOrUpdate(a).getId();
    }

    @Override
    public void updateActor(ActorDto actor) {
        if(actor == null) return;
        
        Actor a = genericDao.getById(actor.getId(), Actor.class);
        a.setFirst_name(actor.getFirst_name());
        a.setLast_name(actor.getLast_name());
        a.setDate_of_birth(actor.getDate_of_birth());
        a.setImage_url(actor.getImage_url());
        a.setBiography(actor.getBiography());
        
    }

    @Override
    public void deleteActor(Long actorId) {
        genericDao.removeById(actorId, Actor.class);
    }
    
}
