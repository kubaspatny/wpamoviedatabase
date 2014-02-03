package com.cvut.spatnjak.wpa.moviedatabase.service;

import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicWriterDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.WriterDto;
import java.util.Date;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kuba Spatny
 */
@Transactional
public interface IWriterService {
    
    @Transactional(readOnly=true)
    public List<WriterDto> getAllWriters();
    
    @Transactional(readOnly=true)
    public List<BasicWriterDto> getAllBasicWriters();
    
    @Transactional(readOnly=true)
    public List<BasicWriterDto> searchWritersByFirstName(String name);
    
    @Transactional(readOnly=true)
    public List<BasicWriterDto> searchWritersByLastName(String name);
    
    @Transactional(readOnly=true)
    public WriterDto getWriterById(Long writerId);
    
    public Long addWriter(String first_name, String last_name, int year, int month, int day, String image_url, String biography);
    
    public Long addWriter(String first_name, String last_name, Date dob, String image_url, String biography);
    
    public void updateWriter(WriterDto writer);
    
    public void deleteWriter(Long writerId);
    
}
