/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.WriterDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IWriterService;
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
@Component("editWriterBB")
@Scope("request")
public class EditWriterBB {
    
    @Autowired
    private AutocompleteBB autoCompleteBB;

    Map<String, String> params;
    private WriterDto writer;
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
    private IWriterService writerService;

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

        writer = writerService.getWriterById(id);
        if (writer == null) {
            writer = new WriterDto();
            error();
            return;
        }
        
        this.first_name = writer.getFirst_name();
        this.last_name = writer.getLast_name();
        this.image_url = writer.getImage_url();
        this.dob = writer.getDate_of_birth();
        this.biography = writer.getBiography();

    }
    

    public String save() {
        writer.setBiography(biography);
        writer.setFirst_name(first_name);
        writer.setLast_name(last_name);
        writer.setDate_of_birth(dob);
        writer.setImage_url(image_url);
        writerService.updateWriter(writer);
        
        return "writer?faces-redirect=true&id=" + (long) writer.getId();
    }
    
    public String delete() {
        writerService.deleteWriter(writer.getId());
        autoCompleteBB.updateWriters();
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
        facesContext.getExternalContext().responseSendError(404, "Actor with this ID doesn't exist");
        facesContext.responseComplete();
    }

    public WriterDto getWriter() {
        return writer;
    }

    public void setWriter(WriterDto writer) {
        this.writer = writer;
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
