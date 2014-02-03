package com.cvut.spatnjak.wpa.moviedatabase.service;

import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicDirectorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.DirectorDto;
import java.util.Date;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kuba Spatny
 */
@Transactional
public interface IDirectorService {
    
    @Transactional(readOnly=true)
    public List<DirectorDto> getAllDirectors();
    
    @Transactional(readOnly=true)
    public List<BasicDirectorDto> getAllBasicDirectors();
    
    @Transactional(readOnly=true)
    public List<BasicDirectorDto> searchDirectorsByFirstName(String name);
    
    @Transactional(readOnly=true)
    public List<BasicDirectorDto> searchDirectorsByLastName(String name);
    
    @Transactional(readOnly=true)
    public DirectorDto getDirectorById(Long directorId);
    
    public Long addDirector(String first_name, String last_name, int year, int month, int day, String image_url, String biography);
    
    public Long addDirector(String first_name, String last_name, Date dob, String image_url, String biography);
    
    public void updateDirector(DirectorDto director);
    
    public void deleteDirector(Long directorId);
    
}
