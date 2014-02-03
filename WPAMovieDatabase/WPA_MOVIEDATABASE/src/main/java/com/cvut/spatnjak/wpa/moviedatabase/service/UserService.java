package com.cvut.spatnjak.wpa.moviedatabase.service;

import com.cvut.spatnjak.wpa.moviedatabase.bo.Address;
import com.cvut.spatnjak.wpa.moviedatabase.bo.User;
import com.cvut.spatnjak.wpa.moviedatabase.convert.UserDtoConvertor;
import com.cvut.spatnjak.wpa.moviedatabase.dao.IUserDao;
import com.cvut.spatnjak.wpa.moviedatabase.dto.AddressDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.UserDto;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba
 */
@Component
public class UserService extends AbstractDataAccessService implements IUserService {
    
    @Autowired
    protected IUserDao userDao;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = genericDao.getAll(User.class);
        return UserDtoConvertor.convertUserList(users);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User u = genericDao.getById(userId, User.class);
        return UserDtoConvertor.convertUser(u);
    }
    
    @Override
    public UserDto getUserByUsername(String username) {
        List<User> u = genericDao.getByProperty("username", username, User.class);
        User u1 = u.isEmpty() ? null : u.get(0);
        return UserDtoConvertor.convertUser(u1);
    }
    
    @Override
    public boolean exist(String username) {
        List<User> u = genericDao.getByProperty("username", username, User.class);
        return !(u.isEmpty());
    }

    @Override
    public Long addUser(String first_name, String last_name, String username, String password, String email, int year, int month, int day, String image_url, String biography, boolean isAdmin, String street, Integer street_number, String city, Integer city_post_code, String country) {
        User u = new User();
        u.setFirst_name(first_name);
        u.setLast_name(last_name);
        u.setUsername(username);
        u.setPassword(password);
        u.setEmail(email);
        u.setDate_of_birth(year, month, day);
        u.setImage_url(image_url);
        u.setBiography(biography);
        u.setIsAdmin(isAdmin);
        Address a = new Address(street, street_number, city, city_post_code, country);
        u.setUser_address(a);
        return genericDao.saveOrUpdate(u).getId();
    }
    
    @Override
    public Long addUser(String first_name, String last_name, String username, String password, String email, Date dob, boolean isAdmin) {
        User u = new User();
        u.setFirst_name(first_name);
        u.setLast_name(last_name);
        u.setUsername(username);
        u.setPassword(password);
        u.setEmail(email);
        u.setDate_of_birth(dob);
        u.setIsAdmin(isAdmin);
        return genericDao.saveOrUpdate(u).getId();
    }

    @Override
    public Long addUser(String first_name, String last_name, String username, String password, String email, int year, int month, int day, boolean isAdmin, String street, Integer street_number, String city, Integer city_post_code, String country) {
        return addUser(first_name, last_name, username, password, email, year, month, day, null, null, isAdmin, street, street_number, city, city_post_code,country);
    }

    @Override
    public void updateUser(UserDto user) {
        if(user == null || user.getId() == null) return;
        
        User u = genericDao.getById(user.getId(), User.class);
        u.setFirst_name(user.getFirst_name());
        u.setLast_name(user.getLast_name());
        u.setEmail(user.getEmail());
        u.setDate_of_birth(user.getDate_of_birth());
        u.setImage_url(user.getImage_url());
        u.setBiography(user.getBiography());
        Address a = u.getUser_address() == null ? new Address() : u.getUser_address();
        u.setUser_address(a);
        AddressDto address = user.getAddress();        
        a.setStreet(address.getStreet());
        a.setStreet_number(address.getStreet_number());
        a.setCity(address.getCity());
        a.setCity_post_code(address.getCity_post_code());
        a.setCountry(address.getCountry());
        
    }

    @Override
    public void deleteUser(Long userId) {
        genericDao.removeById(userId, User.class);
    }

    @Override
    public boolean validatePassword(Long userID, String password) {
        if(userID == null || password == null) return false;
        User u = genericDao.getById(userID, User.class);
        return u.getPassword().equals(password);
    }

    @Override
    public void changePassword(Long userID, String old_password, String new_password) {
        if(userID == null || old_password == null || new_password == null) return;
        User u = genericDao.getById(userID, User.class);
        if(u.getPassword().equals(old_password)){
            u.setPassword(new_password);
        }
        
    }

    @Override
    public boolean hasRated(Long userID, Long movieID) {
        User u = genericDao.getById(userID, User.class);
        return u.hasRated(movieID);
    }

    @Override
    public void addRatedMovie(Long userID, Long movieID) {
        User u = genericDao.getById(userID, User.class);
        u.addRatedMovie(movieID);
    }

    @Override
    public List<UserDto> getAllAdmins() {
        List<User> admins = userDao.getAllAdmins();
        return UserDtoConvertor.convertUserList(admins);
    }

}
