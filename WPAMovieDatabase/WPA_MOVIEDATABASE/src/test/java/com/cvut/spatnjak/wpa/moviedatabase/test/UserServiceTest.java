/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.test;

import com.cvut.spatnjak.wpa.moviedatabase.dto.AddressDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicMovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.UserDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IUserService;
import java.util.Date;
import java.util.List;
import java.util.Set;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kuba
 */
public class UserServiceTest extends AbstractServiceTest {
    
    @Autowired
    private IUserService userService;
    
    @Test
    public void empty(){}
    
    @Test
    public void testAddUserWithoutAddress(){
        Date date = new Date();
        date.setYear(2013-1900);
        date.setMonth(12-1);
        date.setDate(24);
        Long id = userService.addUser("First", "Last", "Username", "pass", "mail@mail.com", date, true);
        
        UserDto u = userService.getUserById(id);
        Assert.assertNull(u.getAddress());
    }
    
    @Test
    public void testAdd(){
        String first_name = "first";
        String last_name = "last";
        String username = "username";
        String password = "password";
        String email = "user@email.com";
        int year = 2013;
        int month = 11;
        int day = 22;
        boolean admin = true;
        String street = "Technicka";
        Integer street_num = 2;
        String city = "Prague";
        Integer city_p_n = 11100;
        String country = "CZ";
        
        int size = userService.getAllUsers().size();
        
        Long id = userService.addUser(first_name, last_name, username, password, email, year, month, day, admin, street, street_num, city, city_p_n, country);
        
        Assert.assertEquals(size + 1, userService.getAllUsers().size());
        
        UserDto u = userService.getUserById(id);
        Assert.assertEquals(id, u.getId());
        Assert.assertEquals(first_name, u.getFirst_name());
        Assert.assertEquals(last_name, u.getLast_name());
        Assert.assertEquals(username, u.getUsername());
        Assert.assertTrue(userService.validatePassword(id, password));
        Assert.assertEquals(email, u.getEmail());
        Assert.assertEquals(admin, u.isAdmin());
        AddressDto a = u.getAddress();
        Assert.assertEquals(street, a.getStreet());
        Assert.assertEquals(street_num, a.getStreet_number());
        Assert.assertEquals(city, a.getCity());
        Assert.assertEquals(city_p_n, a.getCity_post_code());
        Assert.assertEquals(country, a.getCountry());
        
    }
    
    @Test
    public void testDelete(){
        int size = userService.getAllUsers().size();
        userService.deleteUser(168l);
        Assert.assertEquals(size - 1, userService.getAllUsers().size());
    }
    
    @Test
    public void testUpdate(){
        String bio = "difsvbhoihbxnchfbhcund366vfv14c1f45416xc41c1cf" + System.nanoTime();
        String url = "sdkjcbhffc.jpg" + System.nanoTime();
        UserDto u = userService.getUserById(168l);
        u.setBiography(bio);
        u.setImage_url(url);
        userService.updateUser(u);
        
        u = userService.getUserById(168l);
        Assert.assertEquals(bio, u.getBiography());
        Assert.assertEquals(url, u.getImage_url());
        
    }
    
    @Test
    public void testValidatePassword(){
        
        String correct = "profXpassword";
        String wrong = System.nanoTime() + "dhiufshbfs";
        
        Assert.assertFalse(userService.validatePassword(166l, wrong));
        Assert.assertTrue(userService.validatePassword(166l, correct));
       
    }
    
    @Test
    public void testChangePassword(){
        System.out.println("--- TEST CHANGE PASSWORD ---");
        String new_pass = "" + System.nanoTime();
        UserDto u = userService.getUserById(166l);
        userService.changePassword(u.getId(), "profXpassword", new_pass);
        
        Assert.assertTrue(userService.validatePassword(166l, new_pass));
        
    }
    
    @Test
    public void testRating(){
       boolean hasRated = userService.hasRated(166l, 133l);
       Assert.assertFalse(hasRated);
       
       userService.addRatedMovie(166l, 133l);
       
       hasRated = userService.hasRated(166l, 133l);
       Assert.assertTrue(hasRated);
    }
    
    @Test
    public void testGetByID(){
        
        UserDto u = userService.getUserById(166l);
        Assert.assertEquals("Charles", u.getFirst_name());
        Assert.assertEquals("Xavier", u.getLast_name());
        Assert.assertEquals("profX", u.getUsername());
        
        
        System.out.println("--- TEST GET USER BY ID ---");
        System.out.println(u.getFirst_name() + " " + u.getLast_name());
        System.out.println(u.getUsername());
        System.out.println(u.getEmail());
        System.out.println("Created movies: ");
        Set<BasicMovieDto> movies = u.getCreated_movies();
        for (BasicMovieDto m : movies) {
            System.out.println(m.getName());
        }
        System.out.println("---------------------------");
        
        u = userService.getUserById(164l);
        System.out.println(u.getFirst_name() + " " + u.getLast_name());
        System.out.println(u.getUsername());
        System.out.println(u.getEmail());
        System.out.println("Created movies: ");
        movies = u.getCreated_movies();
        for (BasicMovieDto m : movies) {
            System.out.println(m.getName());
        }
        System.out.println("---------------------------");
        
    }
    
    @Test
    public void updateUserAndAddress(){
        
        String city = "fhbsddcscd6d6c4s7vsudh";
        String bio = "dbhvsdjmxd54sc867s";
        UserDto u = userService.getUserById(166l);
        AddressDto a = u.getAddress();
        
        a.setCity(city);
        u.setBiography(bio);
        
        userService.updateUser(u);
        
        u = userService.getUserById(166l);
        a = u.getAddress();
        
        Assert.assertEquals(city, a.getCity());
        Assert.assertEquals(bio, u.getBiography());
        
        
    }
    
    @Test
    public void testGetAllAdmins(){
        
        System.out.println("--- TEST GET ALL ADMINS ---");
        List<UserDto> admins = userService.getAllAdmins();
        for (UserDto u : admins) {
            Assert.assertTrue(u.isAdmin());
            System.out.println(u.getUsername());
        }
        System.out.println("---------------------------");
        
    }
    
}