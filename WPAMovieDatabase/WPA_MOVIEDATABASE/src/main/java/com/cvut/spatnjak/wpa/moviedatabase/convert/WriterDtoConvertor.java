/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.convert;

import com.cvut.spatnjak.wpa.moviedatabase.bo.Writer;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicWriterDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.WriterDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kuba
 */
public class WriterDtoConvertor {
    
    public static BasicWriterDto convertWriterToBasic(Writer wr){
        if(wr == null) return null;
        BasicWriterDto d = new BasicWriterDto();
        d.setId(wr.getId());
        d.setFirst_name(wr.getFirst_name());
        d.setLast_name(wr.getLast_name());        
        return d;
    }
    
    public static WriterDto convertWriterToFull(Writer wr){
        if(wr == null) return null;
        WriterDto w = new WriterDto();
        w.setId(wr.getId());
        w.setFirst_name(wr.getFirst_name());
        w.setLast_name(wr.getLast_name());
        w.setDate_of_birth(wr.getDate_of_birth());
        w.setImage_url(wr.getImage_url());
        w.setBiography(wr.getBiography());
        w.setWritten_movies(wr.getWritten_movies());
        
        return w;
    }
    
    public static List<WriterDto> convertWriterList(List<Writer> writers){
        List<WriterDto> writers_result = new ArrayList<WriterDto>();
        
        if(writers == null) return writers_result;
        
        for (Writer w : writers) {
            writers_result.add(WriterDtoConvertor.convertWriterToFull(w));
        }
        
        return writers_result;
        
    }
    
    public static List<BasicWriterDto> convertWriterBasicList(List<Writer> writers){
        List<BasicWriterDto> writers_result = new ArrayList<BasicWriterDto>();
        
        if(writers == null) return writers_result;
        
        for (Writer w : writers) {
            writers_result.add(WriterDtoConvertor.convertWriterToBasic(w));
        }
        
        return writers_result;
        
    }
    
}
