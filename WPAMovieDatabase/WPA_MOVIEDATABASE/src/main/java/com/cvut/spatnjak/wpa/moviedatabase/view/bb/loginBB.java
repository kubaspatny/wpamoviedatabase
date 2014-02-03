/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.UserDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IUserService;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba
 */
@Component("loginBB")
@Scope("request")
public class loginBB {
    
    @Autowired
    UserBB userBB;
    
    private String username;
    
    @Autowired
    private IUserService userService;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return "";
    }

    public void setPassword(String password) {
    }
    
    public void validateUsername(FacesContext facesContext, UIComponent uiComponent, Object newValue) throws ValidatorException {
        
        if(!userService.exist((String)newValue)){
            throw new ValidatorException(new FacesMessage("Wrong username!"));
        }
        
    }
    
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object newValue) throws ValidatorException {
        String local_pass = (String) newValue;
        UIInput confirmComponent = (UIInput) uiComponent.getAttributes().get("login");
        String local_user;
        if(confirmComponent.isLocalValueSet()){
            local_user = (String)confirmComponent.getValue();
        } else {
            local_user = (String)confirmComponent.getSubmittedValue();
        }
        
        if(local_user == null || local_user.isEmpty() || local_pass == null || local_pass.isEmpty()){
            throw new ValidatorException(new FacesMessage("Please enter your username and password!"));
        }
        
        UserDto u = userService.getUserByUsername(local_user);
        if(u == null){
            return;
        }
        
        boolean login = userService.validatePassword(u.getId(), local_pass);
        
        if(!login){
            throw new ValidatorException(new FacesMessage("Wrong password!")); 
        }

        if (userBB != null) {
            userBB.setUser(u);
        } else {
            throw new ValidatorException(new FacesMessage("UserBB session bean is null!")); 
        }
    }
    
}
