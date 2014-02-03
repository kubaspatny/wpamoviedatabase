package com.cvut.spatnjak.wpa.moviedatabase.dao;

import com.cvut.spatnjak.wpa.moviedatabase.bo.AbstractBusinessObject;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba Spatny
 */
@Component("genericDao")
public class Dao implements GenericDao {
    
    @Autowired
    protected EntityManagerFactory entityManagerfactory;

    protected EntityManager getEntityManager() {
        return EntityManagerFactoryUtils.getTransactionalEntityManager(entityManagerfactory); //entity manager with @Transactional support
    }
    
    @Override
    public <ENTITY> ENTITY getById(Long id, Class<ENTITY> entity_class) {
        return getEntityManager().find(entity_class, id);
    }

    @Override
    public <ENTITY> List<ENTITY> getAll(Class<ENTITY> entity_class) {
        return getEntityManager().createQuery("SELECT e FROM " + entity_class.getSimpleName() + " e order by e.id").getResultList();
    }
    
    @Override
    public <ENTITY> List<ENTITY> getAllOrderedDesc(String property, Class<ENTITY> entity_class) {
        return getEntityManager().createQuery("SELECT e FROM " + entity_class.getSimpleName() + " e order by e." + property + " desc").getResultList();
    }

    @Override
    public <ENTITY> List<ENTITY> getAllOrderedAsc(String property, Class<ENTITY> entity_class) {
        return getEntityManager().createQuery("SELECT e FROM " + entity_class.getSimpleName() + " e order by e." + property).getResultList();
    }
    
    @Override
    public <ENTITY> List<ENTITY> getByProperty(String property, Object value, Class<ENTITY> entity_class) {
        String queryString = "SELECT e FROM " + entity_class.getSimpleName() + " e WHERE e." + property + " = :value";
        return getEntityManager().createQuery(queryString).setParameter("value", value).getResultList();
    }
    
    @Override
    public <ENTITY> List<ENTITY> searchByProperty(String property, Object value, Class<ENTITY> entity_class) {
        String queryString = "SELECT e FROM " + entity_class.getSimpleName() + " e WHERE e." + property + " LIKE :value";
        return getEntityManager().createQuery(queryString).setParameter("value", "%"+value+"%").getResultList();
    }
    
    @Override
    public <ENTITY> ENTITY getByPropertyUnique(String property, Object value, Class<ENTITY> entity_class) {
        ENTITY e;
        if (value == null) {
            e = entity_class.cast(getEntityManager().createQuery("FROM " + entity_class.getSimpleName() + " WHERE " + property + " IS NULL" ).getSingleResult());
        } else {
            e = entity_class.cast(getEntityManager().createQuery("FROM " + entity_class.getSimpleName() + " WHERE " + property + " = :value" ).setParameter("value", value).getSingleResult());
        }
        return e;
    }
    
    @Override
    public <ENTITY> List<ENTITY> getByPropertyTopN(String property, Object value, int number, String orderProperty, Class<ENTITY> entity_class) {
        String queryString = "SELECT e FROM " + entity_class.getSimpleName() + " e WHERE e." + property + " = :value order by e." + orderProperty + " desc";
        return getEntityManager().createQuery(queryString).setParameter("value", value).setMaxResults(number).getResultList();
    }
    
    @Override
    public <ENTITY> List<ENTITY> getTopN(String property, int number, Class<ENTITY> entity_class) {
        return getEntityManager().createQuery("SELECT e FROM " + entity_class.getSimpleName() + " e order by e." + property + " desc").setMaxResults(number).getResultList();
    }
    
    @Override
    public <ENTITY extends AbstractBusinessObject> void removeById(long id, Class<ENTITY> entity_class) {
        ENTITY e = getEntityManager().find(entity_class, id);
        if (e != null) {
            getEntityManager().remove(e);
        }
    }
    
    @Override
    public <ENTITY extends AbstractBusinessObject> void remove(ENTITY o) {
        getEntityManager().remove(o);
    }
    
    @Override
    public <ENTITY extends AbstractBusinessObject> ENTITY saveOrUpdate(ENTITY o) {
        if (o.getId() == null) {
            getEntityManager().persist(o);
        } else {
            getEntityManager().merge(o);
        }
        return o;
    }

    // NOT IMPLEMENTED YET
    
    @Override
    public <ENTITY> List<ENTITY> getPage(int from, int maxResults, Class<ENTITY> entity_class) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long getCount(Class entity_class) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public <ENTITY> List<ENTITY> getPage(int first, int rows, String sortBy, boolean ascending, Class<ENTITY> entity_class) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
}
