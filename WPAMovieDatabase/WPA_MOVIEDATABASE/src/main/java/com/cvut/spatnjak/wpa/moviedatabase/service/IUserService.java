package com.cvut.spatnjak.wpa.moviedatabase.service;

import com.cvut.spatnjak.wpa.moviedatabase.dto.UserDto;
import java.util.Date;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kuba Spatny
 */

@Transactional
public interface IUserService {
    
    @Transactional(readOnly=true)
    public List<UserDto> getAllUsers();
    
    @Transactional(readOnly=true)
    public UserDto getUserById(Long userId);
    
    @Transactional(readOnly=true)
    public UserDto getUserByUsername(String username);
    
    @Transactional(readOnly=true)
    public boolean exist(String username);
    
    @Transactional(readOnly=true)
    public List<UserDto> getAllAdmins();
    
    public Long addUser(String first_name, String last_name, String username, String password, String email, int year, int month, int day, String image_url, String biography, boolean isAdmin, String street, Integer street_number, String city, Integer city_post_code, String country);
    
    public Long addUser(String first_name, String last_name, String username, String password, String email, int year, int month, int day, boolean isAdmin, String street, Integer street_number, String city, Integer city_post_code, String country);
    
    public Long addUser(String first_name, String last_name, String username, String password, String email, Date dob, boolean isAdmin);
    
    public void updateUser(UserDto user);
    
    public void deleteUser(Long userId);
    
    @Transactional(readOnly=true)
    public boolean validatePassword(Long userID, String password);
    
    public void changePassword(Long userID, String old_password, String new_password);
    
    @Transactional(readOnly=true)
    public boolean hasRated(Long userID, Long movieID);
    
    public void addRatedMovie(Long userID, Long movieID);
    
}
