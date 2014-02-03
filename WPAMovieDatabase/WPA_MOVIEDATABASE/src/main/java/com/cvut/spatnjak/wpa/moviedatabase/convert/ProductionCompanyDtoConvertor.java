/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.convert;

import com.cvut.spatnjak.wpa.moviedatabase.bo.ProductionCompany;
import com.cvut.spatnjak.wpa.moviedatabase.dto.ProductionCompanyDto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kuba
 */
public class ProductionCompanyDtoConvertor {
    
    public static ProductionCompanyDto convertProdComp(ProductionCompany prodComp){
        if(prodComp == null) return null;
        ProductionCompanyDto p = new ProductionCompanyDto();
        p.setId(prodComp.getId());
        p.setName(prodComp.getName());
        p.setProduced_movies(prodComp.getProduced_movies());
        
        return p;
    }
    
    public static List<ProductionCompanyDto> convertProductionCompanyList(List<ProductionCompany> producers){
        List<ProductionCompanyDto> producers_result = new ArrayList<ProductionCompanyDto>();
        
        if(producers == null) return producers_result;
        
        for (ProductionCompany p : producers) {
            producers_result.add(ProductionCompanyDtoConvertor.convertProdComp(p));
        }
        
        return producers_result;
        
    }
    
}
