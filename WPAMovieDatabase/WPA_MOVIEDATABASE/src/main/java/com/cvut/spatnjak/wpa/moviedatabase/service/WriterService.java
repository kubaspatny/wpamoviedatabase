package com.cvut.spatnjak.wpa.moviedatabase.service;

import com.cvut.spatnjak.wpa.moviedatabase.bo.Writer;
import com.cvut.spatnjak.wpa.moviedatabase.convert.WriterDtoConvertor;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicWriterDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.WriterDto;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba
 */
@Component
public class WriterService extends AbstractDataAccessService implements IWriterService{

    @Override
    public List<WriterDto> getAllWriters() {        
        List<Writer> writers = genericDao.getAll(Writer.class);
        return WriterDtoConvertor.convertWriterList(writers);
    }
    
    @Override
    public List<BasicWriterDto> getAllBasicWriters() {        
        List<Writer> writers = genericDao.getAll(Writer.class);
        return WriterDtoConvertor.convertWriterBasicList(writers);
    }
    
    @Override
    public List<BasicWriterDto> searchWritersByFirstName(String name){
        List<Writer> writers = genericDao.searchByProperty("first_name", name, Writer.class);
        return WriterDtoConvertor.convertWriterBasicList(writers);
    }
    
    @Override
    public List<BasicWriterDto> searchWritersByLastName(String name){
        List<Writer> writers = genericDao.searchByProperty("last_name", name, Writer.class);
        return WriterDtoConvertor.convertWriterBasicList(writers);
    }

    @Override
    public WriterDto getWriterById(Long writerId) {
        Writer w = genericDao.getById(writerId, Writer.class);
        return WriterDtoConvertor.convertWriterToFull(w);
    }

    @Override
    public Long addWriter(String first_name, String last_name, int year, int month, int day, String image_url, String biography) {
        Writer w = new Writer();
        w.setFirst_name(first_name);
        w.setLast_name(last_name);
        w.setDate_of_birth(year, month, day);
        w.setImage_url(image_url);
        w.setBiography(biography);
        
        return genericDao.saveOrUpdate(w).getId();
    }
    
    @Override
    public Long addWriter(String first_name, String last_name, Date dob, String image_url, String biography) {
        Writer w = new Writer();
        w.setFirst_name(first_name);
        w.setLast_name(last_name);
        w.setDate_of_birth(dob);
        w.setImage_url(image_url);
        w.setBiography(biography);
        
        return genericDao.saveOrUpdate(w).getId();
    }

    @Override
    public void updateWriter(WriterDto writer) {
        if(writer == null) return;
        
        Writer w = genericDao.getById(writer.getId(), Writer.class);
        w.setFirst_name(writer.getFirst_name());
        w.setLast_name(writer.getLast_name());
        w.setDate_of_birth(writer.getDate_of_birth());
        w.setImage_url(writer.getImage_url());
        w.setBiography(writer.getBiography());
        
    }

    @Override
    public void deleteWriter(Long writerId) {
        genericDao.removeById(writerId, Writer.class);
    }
    
}
