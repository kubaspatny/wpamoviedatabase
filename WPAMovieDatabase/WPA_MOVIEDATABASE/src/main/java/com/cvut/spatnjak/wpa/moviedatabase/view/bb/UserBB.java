package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.UserDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IUserService;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba Spatny
 */
@Component("userBB")
@Scope(value = "session")
public class UserBB {

    String username;
    private UserDto user;
    
    @Autowired
    private IUserService userService;
    
    @PostConstruct
    private void init(){
        update();
    }

    public String getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/j_spring_security_logout";
    }
    
    public void update(){
        user = userService.getUserByUsername(getUsername());
    }
}
