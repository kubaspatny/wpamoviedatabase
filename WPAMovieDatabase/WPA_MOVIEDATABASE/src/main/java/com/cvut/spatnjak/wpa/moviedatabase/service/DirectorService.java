package com.cvut.spatnjak.wpa.moviedatabase.service;

import com.cvut.spatnjak.wpa.moviedatabase.bo.Director;
import com.cvut.spatnjak.wpa.moviedatabase.convert.DirectorDtoConvertor;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicDirectorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.DirectorDto;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba Spatny
 */
@Component
public class DirectorService extends AbstractDataAccessService implements IDirectorService {

    @Override
    public List<DirectorDto> getAllDirectors() {
        List<Director> directors = genericDao.getAll(Director.class);
        return DirectorDtoConvertor.convertDirectorList(directors);
    }
    
    @Override
    public List<BasicDirectorDto> getAllBasicDirectors() {
        List<Director> directors = genericDao.getAll(Director.class);
        return DirectorDtoConvertor.convertDirectorBasicList(directors);
    }
    
    @Override
    public List<BasicDirectorDto> searchDirectorsByFirstName(String name){
        List<Director> result = genericDao.searchByProperty("first_name", name, Director.class);
        return DirectorDtoConvertor.convertDirectorBasicList(result);
    }
    
    @Override
    public List<BasicDirectorDto> searchDirectorsByLastName(String name){
        List<Director> result = genericDao.searchByProperty("last_name", name, Director.class);
        return DirectorDtoConvertor.convertDirectorBasicList(result);
    }

    @Override
    public DirectorDto getDirectorById(Long directorId) {
        Director d = genericDao.getById(directorId, Director.class);
        return DirectorDtoConvertor.convertDirectorToFull(d);
    }

    @Override
    public Long addDirector(String first_name, String last_name, int year, int month, int day, String image_url, String biography) {
        Director d = new Director();
        d.setFirst_name(first_name);
        d.setLast_name(last_name);
        d.setDate_of_birth(year, month, day);
        d.setImage_url(image_url);
        d.setBiography(biography);
        
        return genericDao.saveOrUpdate(d).getId();
    }
    
    @Override
    public Long addDirector(String first_name, String last_name, Date dob, String image_url, String biography) {
        Director d = new Director();
        d.setFirst_name(first_name);
        d.setLast_name(last_name);
        d.setDate_of_birth(dob);
        d.setImage_url(image_url);
        d.setBiography(biography);
        
        return genericDao.saveOrUpdate(d).getId();
    }

    @Override
    public void updateDirector(DirectorDto director) {
        if(director == null) return;
        
        Director d = genericDao.getById(director.getId(), Director.class);
        d.setFirst_name(director.getFirst_name());
        d.setLast_name(director.getLast_name());
        d.setDate_of_birth(director.getDate_of_birth());
        d.setImage_url(director.getImage_url());
        d.setBiography(director.getBiography());
        
    }

    @Override
    public void deleteDirector(Long directorId) {
        genericDao.removeById(directorId, Director.class);
    }
    
}
