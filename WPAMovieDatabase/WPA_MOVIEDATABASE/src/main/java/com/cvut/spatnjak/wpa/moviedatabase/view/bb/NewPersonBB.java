/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.service.IActorService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IDirectorService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IWriterService;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba
 */
@Component("newPersonBB")
@Scope("request")
public class NewPersonBB {
    
    @Autowired
    private IActorService actorService;
    
    @Autowired
    private IDirectorService directorService;
    
    @Autowired
    private IWriterService writerService;
    
    @Autowired
    private AutocompleteBB autoCompleteBB;
    
    private String first_name;
    private String last_name;
    private Date date_of_birth;    
    private String image_url;    
    private String biography;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
    
    public String addPerson(){
        
        if(type==null || type.equals("")) return null;
        
        if(type.equals("Actor")){
            Long id = actorService.addActor(first_name, last_name, date_of_birth, image_url, biography);
            autoCompleteBB.updateActors();
            return "actor?faces-redirect=true&id=" + id;
        } else if(type.equals("Director")){
            Long id = directorService.addDirector(first_name, last_name, date_of_birth, image_url, biography);
            autoCompleteBB.updateDirectors();
            return "director?faces-redirect=true&id=" + id;
        } else {
            Long id = writerService.addWriter(first_name, last_name, date_of_birth, image_url, biography);
            autoCompleteBB.updateWriters();
            return "writer?faces-redirect=true&id=" + id;
        }
        
    }
    
    public void validateLenght255(FacesContext facesContext, UIComponent uiComponent, Object newValue) throws ValidatorException {
        String number = (String) newValue;
        if (number.length() > 255) {
            throw new ValidatorException(new FacesMessage("Field is limited to 255 characters!"));
        }
    }
    
    
    
}
