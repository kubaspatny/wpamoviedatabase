/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.dao;

import com.cvut.spatnjak.wpa.moviedatabase.bo.User;
import com.cvut.spatnjak.wpa.moviedatabase.dto.UserDto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba
 */
@Component("userDao")
public class userDao implements IUserDao {
    
    @Autowired
    protected EntityManagerFactory entityManagerfactory;

    protected EntityManager getEntityManager() {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerfactory); //entity manager with @Transactional support
    }

    @Override
    public List<User> getAllAdmins() {
        List<User> users = getEntityManager().createNamedQuery("User.getAdmins").getResultList();
        return users;
    }
    
}
