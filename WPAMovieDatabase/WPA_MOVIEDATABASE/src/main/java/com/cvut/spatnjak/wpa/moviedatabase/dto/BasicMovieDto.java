/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.dto;

import java.util.Date;

/**
 *
 * @author Kuba
 */
public class BasicMovieDto extends AbstractDto{
    
    private String name;
    private String short_info;

    public BasicMovieDto() {
    }

    public BasicMovieDto(Long id, String name, String short_info) {
        this.id = id;
        this.name = name;
        this.short_info = short_info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_info() {
        return short_info;
    }

    public void setShort_info(String short_info) {
        this.short_info = short_info;
    }
    
}
