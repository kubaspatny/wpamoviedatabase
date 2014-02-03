package com.cvut.spatnjak.wpa.moviedatabase.service;

import com.cvut.spatnjak.wpa.moviedatabase.dto.ProductionCompanyDto;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kuba
 */
@Transactional
public interface IProducerService {
    
    @Transactional(readOnly=true)
    public List<ProductionCompanyDto> getAllProducers();
    
    @Transactional(readOnly=true)
    public ProductionCompanyDto getProducerById(Long producerId);
    
    @Transactional(readOnly=true)
    public ProductionCompanyDto getProducerByName(String username);
    
    public Long addProducer(String name);
    
    public void updateProducer(ProductionCompanyDto producer);
    
    public void deleteProducer(Long producerId);
    
}
