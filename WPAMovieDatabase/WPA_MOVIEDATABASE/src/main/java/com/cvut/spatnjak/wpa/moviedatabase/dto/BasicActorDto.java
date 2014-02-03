package com.cvut.spatnjak.wpa.moviedatabase.dto;

import com.cvut.spatnjak.wpa.moviedatabase.bo.Movie;
import com.cvut.spatnjak.wpa.moviedatabase.convert.BasicMovieDtoConvert;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Kuba
 */
public class BasicActorDto extends AbstractDto {
    
    protected String first_name;
    protected String last_name;
    
    public BasicActorDto() {
    }

    public BasicActorDto(Long id, String first_name, String last_name) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    
    public String getFullName(){
        return first_name + " " + last_name;
    }

    @Override
    public String toString() {
        return getFullName() + " (" + getId() + ")";
    }
    
    
    
}
