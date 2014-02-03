/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.convert;

import com.cvut.spatnjak.wpa.moviedatabase.bo.Director;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicDirectorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.DirectorDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kuba
 */
public class DirectorDtoConvertor {
    
    public static BasicDirectorDto convertDirectorToBasic(Director director){
        if(director == null) return null;
        BasicDirectorDto d = new BasicDirectorDto();
        d.setId(director.getId());
        d.setFirst_name(director.getFirst_name());
        d.setLast_name(director.getLast_name());        
        return d;
    }
    
    public static DirectorDto convertDirectorToFull(Director dir){        
        if(dir == null) return null;
        DirectorDto d = new DirectorDto();        
        d.setId(dir.getId());
        d.setFirst_name(dir.getFirst_name());
        d.setLast_name(dir.getLast_name());
        d.setDate_of_birth(dir.getDate_of_birth());
        d.setImage_url(dir.getImage_url());
        d.setBiography(dir.getBiography());
        d.setDirected_movies(dir.getDirected_movies());
        
        return d;
    }
    
    public static List<DirectorDto> convertDirectorList(List<Director> directors){
        List<DirectorDto> directors_result = new ArrayList<DirectorDto>();
        
        if(directors == null) return directors_result;
        
        for (Director d : directors) {
            directors_result.add(DirectorDtoConvertor.convertDirectorToFull(d));
        }
        
        return directors_result;
        
    }
    
    public static List<BasicDirectorDto> convertDirectorBasicList(List<Director> directors){
        List<BasicDirectorDto> directors_result = new ArrayList<BasicDirectorDto>();
        
        if(directors == null) return directors_result;
        
        for (Director d : directors) {
            directors_result.add(DirectorDtoConvertor.convertDirectorToBasic(d));
        }
        
        return directors_result;
        
    }
    
}
