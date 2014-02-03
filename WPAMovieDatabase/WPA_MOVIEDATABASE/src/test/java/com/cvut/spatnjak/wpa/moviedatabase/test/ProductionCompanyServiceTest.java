/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.test;

import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicMovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.ProductionCompanyDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IProducerService;
import java.util.List;
import java.util.Set;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kuba
 */
public class ProductionCompanyServiceTest extends AbstractServiceTest {
    
    @Autowired
    private IProducerService producerService;
    
    @Test
    public void empty(){
    }
    
    @Test
    public void testAddProducer(){
        
        int size = producerService.getAllProducers().size();
        String name = "hndoigudi4684df";
        Long id = producerService.addProducer(name);
        
        Assert.assertEquals(size + 1, producerService.getAllProducers().size());
        
        ProductionCompanyDto p = producerService.getProducerById(id);
        Assert.assertEquals(id, p.getId());
        Assert.assertEquals(name, p.getName());
        
    }
    
    @Test
    public void testDeleteProducer(){
        int size = producerService.getAllProducers().size();
        producerService.deleteProducer(2057l);
        Assert.assertEquals(size - 1, producerService.getAllProducers().size());
        Assert.assertNull(producerService.getProducerById(2057l));
    }

    @Test
    public void testGetByID(){
        
        ProductionCompanyDto p = producerService.getProducerById(2057l);
        Assert.assertNotNull(p);
        Assert.assertEquals((Long)2057l, p.getId());
        Assert.assertEquals("Universal", p.getName());
        
    }
    
    @Test
    public void testUpdate(){
        String name = "heofhsbsas4656cf";
        ProductionCompanyDto p = producerService.getProducerById(2057l);
        p.setName(name);
        producerService.updateProducer(p);
        
        p = producerService.getProducerById(2057l);
        Assert.assertEquals(name, p.getName());
    }
    
}
