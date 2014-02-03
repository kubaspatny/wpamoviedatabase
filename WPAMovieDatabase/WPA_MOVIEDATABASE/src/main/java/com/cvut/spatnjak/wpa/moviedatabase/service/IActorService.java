package com.cvut.spatnjak.wpa.moviedatabase.service;

import com.cvut.spatnjak.wpa.moviedatabase.dto.ActorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicActorDto;
import java.util.Date;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kuba Spatny
 */
@Transactional
public interface IActorService {
    
    @Transactional(readOnly=true)
    public List<ActorDto> getAllActors();
    
    @Transactional(readOnly=true)
    public List<BasicActorDto> getAllBasicActors();
    
    @Transactional(readOnly=true)
    public ActorDto getActorById(Long movieId);
    
    @Transactional(readOnly=true)
    public List<BasicActorDto> searchActorByFirstName(String name);
    
    @Transactional(readOnly=true)
    public List<BasicActorDto> searchActorByLastName(String name);
    
    public Long addActor(String first_name, String last_name, int year, int month, int day, String image_url, String biography);
    
    public Long addActor(String first_name, String last_name, Date dob, String image_url, String biography);
    
    public void updateActor(ActorDto actor);
    
    public void deleteActor(Long actorId);
    
}
