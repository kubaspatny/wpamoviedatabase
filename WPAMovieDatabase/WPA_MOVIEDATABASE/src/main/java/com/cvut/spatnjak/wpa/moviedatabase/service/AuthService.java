/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.service;

import com.cvut.spatnjak.wpa.moviedatabase.dao.GenericDao;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;


//Configuration in applicationContext-security.xml
public class AuthService extends AbstractUserDetailsAuthenticationProvider {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractUserDetailsAuthenticationProvider.class);
    private GenericDao genericDAO;
    private TransactionTemplate transactionTemplate;

    public AuthService() {
        this.setUserCache(new NullUserCache());
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails ud, UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        // do nothing
    }

    /**
     * @param username
     * @param upat
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        //only public methods can be marked as transactional
        return (UserDetails) transactionTemplate.execute(new TransactionCallback() {

            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    UserDetails ud = null;

                    com.cvut.spatnjak.wpa.moviedatabase.bo.User u;
                    try {
                        u = genericDAO.getByPropertyUnique("username", username, com.cvut.spatnjak.wpa.moviedatabase.bo.User.class);
                    } catch (EmptyResultDataAccessException erdaex) {
                        throw new BadCredentialsException("Uzivatel neexistuje");
                    }
                    String password = (String) upat.getCredentials();
                    if (u == null || !u.getPassword().equals(password)) {
                        AuthenticationException e = new BadCredentialsException("Neplatne uzivatelske udaje");
                        throw e;
                    } else {
                        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
                        auths.add(new SimpleGrantedAuthority("ROLE_USER"));
                        if(u.isAdmin()){
                            auths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                        }
                        ud = new User(u.getUsername(), u.getPassword(), auths);
                    }
                    return ud;
                } catch(AuthenticationException e){
                    status.setRollbackOnly();
                    throw e;
                }catch (Exception e) {
                    LOG.error("Error occured during retrieveUser call", e);
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void setGenericDAO(GenericDao genericDAO) {
        this.genericDAO = genericDAO;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
    
    
}

