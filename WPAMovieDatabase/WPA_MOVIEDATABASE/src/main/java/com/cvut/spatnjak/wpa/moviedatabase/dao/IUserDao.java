/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.dao;

import com.cvut.spatnjak.wpa.moviedatabase.bo.User;
import java.util.List;

/**
 *
 * @author Kuba
 */
public interface IUserDao {
    
    public List<User> getAllAdmins();
    
}
