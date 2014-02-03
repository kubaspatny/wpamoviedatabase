/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.service.IActorService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IProducerService;
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
@Component("newProducerBB")
@Scope("request")
public class NewProducerBB {
    
    @Autowired
    private IProducerService producerService;
    
    @Autowired
    private AutocompleteBB autoCompleteBB;
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String addProducer(){
        
            Long id = producerService.addProducer(name);
            autoCompleteBB.updateProducers();
            return "producer?faces-redirect=true&id=" + id;
        
    }
    
    public void validateName(FacesContext facesContext, UIComponent uiComponent, Object newValue) throws ValidatorException {
        
        if(producerService.getProducerByName((String)newValue) != null){
            throw new ValidatorException(new FacesMessage("Production Company with this name already exists!"));
        }
        String name = (String) newValue;
        if(name.length() > 255){
            throw new ValidatorException(new FacesMessage("Name exceeds the 255 character limit!"));
        }
        
    }
    
}
