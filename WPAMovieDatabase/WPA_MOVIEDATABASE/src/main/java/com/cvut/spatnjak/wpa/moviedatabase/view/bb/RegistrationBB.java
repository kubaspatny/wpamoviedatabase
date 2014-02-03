/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.UserDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IUserService;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@Component("registrationBB")
@Scope("request")
public class RegistrationBB {

    @Autowired
    private IUserService userService;
    
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Date dob;
    private String email;
    private String username;
    private String password;
    private boolean admin;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void validateDOB(FacesContext facesContext, UIComponent uiComponent, Object newValue) throws ValidatorException {
        String dateBirth = (String) newValue;
        
        if(dateBirth.length() > 255) {
            throw new ValidatorException(new FacesMessage("Wrong date format!"));
        }
            
        Date date;
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        s.setLenient(false);

        try {

            ParsePosition position = new ParsePosition(0);
            date = s.parse(dateBirth, position);
            if (position.getIndex() != dateBirth.length()) {
                throw new ValidatorException(new FacesMessage("Wrong date format!"));
            }
        } catch (Exception e) {
            throw new ValidatorException(new FacesMessage("Wrong date format!"));
        }
        
        dob = date;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void validateUsername(FacesContext facesContext, UIComponent uiComponent, Object newValue) throws ValidatorException {
        String username = (String) newValue;
        if(username.length() > 255){
           throw new ValidatorException(new FacesMessage("Username is too long! Please choose shorter (255 character limit).")); 
        }
        
        if(userService.exist(username)){
            throw new ValidatorException(new FacesMessage("Username already exists!"));
        }
        
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_reenter() {
        //return password_reenter;
        return "";
    }

    public void setPassword_reenter(String password_reenter) {
        //this.password_reenter = password_reenter;
    }
    
    public void validatePassEquality(FacesContext facesContext, UIComponent uiComponent, Object newValue) throws ValidatorException {
        String pass = (String) newValue;
        if(pass.length() > 255){
            throw new ValidatorException(new FacesMessage("Password is too long! The limit is 255 characters."));
        }
        
        UIInput confirmComponent = (UIInput) uiComponent.getAttributes().get("pass_confirm");
        String pass_confirm;
        if(confirmComponent.isLocalValueSet()){
            pass_confirm = (String)confirmComponent.getValue();
        } else {
            pass_confirm = (String)confirmComponent.getSubmittedValue();
        }
        
        if(pass_confirm == null || pass_confirm.isEmpty() || pass == null || pass.isEmpty()){
            throw new ValidatorException(new FacesMessage("Please enter passwords!"));
        }
        
        if(!pass.equals(pass_confirm)){
            throw new ValidatorException(new FacesMessage("Passwords do not match!"));
        }
   
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    public String register(){
        userService.addUser(firstName, lastName, username, password, email, dob, admin);
        return "login?faces-redirect=true";
    }
}
