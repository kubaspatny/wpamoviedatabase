package com.cvut.spatnjak.wpa.moviedatabase.service;

import com.cvut.spatnjak.wpa.moviedatabase.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kuba
 */
public abstract class AbstractDataAccessService {
    
    @Autowired
    protected GenericDao genericDao;
    
    public void setGenericDao(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public GenericDao getGenericDao() {
        return genericDao;
    }    
    
}
