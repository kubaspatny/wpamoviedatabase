/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.AddressDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.UserDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IUserService;
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
@Component("editUserBB")
@Scope("request")
public class EditUserBB {
    
    private UserDto user;
    private String street_number;
    private String postal_number;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private UserBB userBB;
    
    @PostConstruct
    private void init(){
        user = userService.getUserByUsername(userBB.getUsername());
        if(user.getAddress() == null){
            user.setAddress(new AddressDto());
        }
        street_number = user.getAddress().getStreet_number() == null ? "" : user.getAddress().getStreet_number()+"";
        postal_number = user.getAddress().getCity_post_code() == null ? "" : user.getAddress().getCity_post_code()+"";
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }

    public String getPostal_number() {
        return postal_number;
    }

    public void setPostal_number(String postal_number) {
        this.postal_number = postal_number;
    }
    
    public void validateStreetNumber(FacesContext facesContext, UIComponent uiComponent, Object newValue) throws ValidatorException {
        String number = (String) newValue;
        if (!number.matches("[0-9]+")) {
            throw new ValidatorException(new FacesMessage("Field can only contain numbers!"));
        }
        
        if(number.length() > 9){
            throw new ValidatorException(new FacesMessage("Field is limited to 9 digits!"));
        }
    }
    
    public void validateLenght255(FacesContext facesContext, UIComponent uiComponent, Object newValue) throws ValidatorException {
        String number = (String) newValue;
        if (number.length() > 255) {
            throw new ValidatorException(new FacesMessage("Field is limited to 255 characters!"));
        }
    }
    
    public void validateName(FacesContext facesContext, UIComponent uiComponent, Object newValue) throws ValidatorException {
        String name = (String) newValue;
        if (!name.matches("[a-zA-Z]+")) {
            throw new ValidatorException(new FacesMessage("Name can only contain letters!"));
        }
        
        if(name.length() > 255){
            throw new ValidatorException(new FacesMessage("Name must be within 255 characters!"));
        }
    }
    
    public void validateEmail(FacesContext facesContext, UIComponent uiComponent, Object newValue) throws ValidatorException {
        String email = (String) newValue;
        if(email.length() > 255){
            throw new ValidatorException(new FacesMessage("Email address too long! It can be 255 characters at max!"));
        }
        if (!(email.contains("@") && (email.contains(".")))) {
            throw new ValidatorException(new FacesMessage("Wrong email format!"));
        }
    }
    
    public String save(){
        user.getAddress().setStreet_number(Integer.parseInt(street_number));
        user.getAddress().setCity_post_code(Integer.parseInt(postal_number));
        userService.updateUser(user);
        userBB.update();
        return "userdetails?faces-redirect=true";
    }
    
}
