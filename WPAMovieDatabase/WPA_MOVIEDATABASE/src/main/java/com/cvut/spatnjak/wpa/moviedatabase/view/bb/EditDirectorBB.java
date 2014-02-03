/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.ActorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.DirectorDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IDirectorService;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
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
@Component("editDirectorBB")
@Scope("request")
public class EditDirectorBB {
    
    @Autowired
    private AutocompleteBB autoCompleteBB;
    
    Map<String, String> params;
    private DirectorDto director;
    String first_name;
    String last_name;
    String biography;
    Date dob;
    String image_url;
    String id_str;

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }
    @Autowired
    private IDirectorService directorService;

    @PostConstruct
    public void init() throws IOException {
        params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id_string = params.get("id");

        Long id = -1l;
        try {
            id = Long.parseLong(id_string);
        } catch (NumberFormatException n) {
        }
        System.out.println("id_long: " + id);

        director = directorService.getDirectorById(id);
        if (director == null) {
            director = new DirectorDto();
            error();
            return;
        }
        
        this.first_name = director.getFirst_name();
        this.last_name = director.getLast_name();
        this.image_url = director.getImage_url();
        this.dob = director.getDate_of_birth();
        this.biography = director.getBiography();

    }
    

    public String save() {
        director.setBiography(biography);
        director.setFirst_name(first_name);
        director.setLast_name(last_name);
        director.setDate_of_birth(dob);
        director.setImage_url(image_url);
        
        directorService.updateDirector(director);
        
        return "director?faces-redirect=true&id=" + (long) director.getId();
    }
    
    public String delete() {
        directorService.deleteDirector(director.getId());
        autoCompleteBB.updateDirectors();
        return "index?faces-redirect=true";
    }

    private static Long parseForID(String s) {
        Long result;
        try {
            String number = s.substring(s.indexOf("(") + 1, s.lastIndexOf(")"));
            if(number == null || number.isEmpty()) throw new Exception("wrong format");
            result = Long.parseLong(number);
        } catch (Exception e) {
            return null;
        }
        return result;
    }
    
    private void error() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().responseSendError(404, "Director with this ID doesn't exist");
        facesContext.responseComplete();
    }

    public DirectorDto getDirector() {
        return director;
    }

    public void setDirector(DirectorDto director) {
        this.director = director;
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    
    public void validateLenght255(FacesContext facesContext, UIComponent uiComponent, Object newValue) throws ValidatorException {
        String number = (String) newValue;
        if (number.length() > 255) {
            throw new ValidatorException(new FacesMessage("Field is limited to 255 characters!"));
        }
    }
    
}
