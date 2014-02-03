package com.cvut.spatnjak.wpa.moviedatabase.dao;

import com.cvut.spatnjak.wpa.moviedatabase.bo.AbstractBusinessObject;
import java.util.List;

/**
 *
 * @author Kuba Spatny
 */
public interface GenericDao {
    
    public <ENTITY> List<ENTITY> getAll(Class<ENTITY> entity_class);
   
    public <ENTITY> List<ENTITY> getPage(int from, int maxResults, Class<ENTITY> entity_class);
   
    public <ENTITY extends AbstractBusinessObject> void removeById(long id, Class<ENTITY> entity_class);

    public Long getCount(Class entity_class);

    public <ENTITY extends AbstractBusinessObject> void remove(ENTITY o);

    public <ENTITY extends AbstractBusinessObject> ENTITY saveOrUpdate(ENTITY o);

    public <ENTITY> ENTITY getById(Long id, Class<ENTITY> entity_class);
   
    public <ENTITY> List<ENTITY> getAllOrderedDesc(String property, Class<ENTITY> entity_class);
    
    public <ENTITY> List<ENTITY> getAllOrderedAsc(String property, Class<ENTITY> entity_class);

    public <ENTITY> List<ENTITY> getByProperty(String property, Object value, Class<ENTITY> entity_class);
    
    public <ENTITY> List<ENTITY> searchByProperty(String property, Object value, Class<ENTITY> entity_class);
    
    public <ENTITY> List<ENTITY> getByPropertyTopN(String property, Object value, int number, String orderProperty, Class<ENTITY> entity_class);
    
    public <ENTITY> ENTITY getByPropertyUnique(String property, Object value, Class<ENTITY> entity_class);

    public <ENTITY> List<ENTITY> getPage(int first, int rows, String sortBy, boolean ascending, Class<ENTITY> entity_class);
    
    public <ENTITY> List<ENTITY> getTopN(String property, int number, Class<ENTITY> entity_class);
    
}
