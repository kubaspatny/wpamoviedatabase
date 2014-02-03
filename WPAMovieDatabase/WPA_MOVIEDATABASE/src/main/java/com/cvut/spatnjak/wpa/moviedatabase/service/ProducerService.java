package com.cvut.spatnjak.wpa.moviedatabase.service;

import com.cvut.spatnjak.wpa.moviedatabase.bo.ProductionCompany;
import com.cvut.spatnjak.wpa.moviedatabase.convert.ProductionCompanyDtoConvertor;
import com.cvut.spatnjak.wpa.moviedatabase.dto.ProductionCompanyDto;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba
 */
@Component
public class ProducerService extends AbstractDataAccessService implements IProducerService {

    @Override
    public List<ProductionCompanyDto> getAllProducers() {
        List<ProductionCompany> producers = genericDao.getAll(ProductionCompany.class);
        return ProductionCompanyDtoConvertor.convertProductionCompanyList(producers);
    }

    @Override
    public ProductionCompanyDto getProducerById(Long producerId) {
        ProductionCompany p = genericDao.getById(producerId, ProductionCompany.class);
        return ProductionCompanyDtoConvertor.convertProdComp(p);
    }
    
    @Override
    public ProductionCompanyDto getProducerByName(String name){
        List<ProductionCompany> u = genericDao.getByProperty("name", name, ProductionCompany.class);
        ProductionCompany u1 = u.isEmpty() ? null : u.get(0);
        return ProductionCompanyDtoConvertor.convertProdComp(u1);
    }

    @Override
    public Long addProducer(String name) {
        ProductionCompany p = new ProductionCompany();
        p.setName(name);
        return genericDao.saveOrUpdate(p).getId();
    }

    @Override
    public void updateProducer(ProductionCompanyDto producer) {
        if(producer == null || producer.getName() == null) return;
        ProductionCompany p = genericDao.getById(producer.getId(), ProductionCompany.class);
        p.setName(producer.getName());
    }

    @Override
    public void deleteProducer(Long producerId) {
        genericDao.removeById(producerId, ProductionCompany.class);
    }
    
}
