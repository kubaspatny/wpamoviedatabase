package com.cvut.spatnjak.wpa.moviedatabase.convert;

import com.cvut.spatnjak.wpa.moviedatabase.dto.AddressDto;
import com.cvut.spatnjak.wpa.moviedatabase.bo.Address;

/**
 *
 * @author Kuba Spatny
 */
public class AddressDtoConvertor {
    
    public static AddressDto convertAddress(Address address){
        if(address == null) return null;
        AddressDto a = new AddressDto();
        a.setId(address.getId());
        a.setStreet(address.getStreet());
        a.setStreet_number(address.getStreet_number());
        a.setCity(address.getCity());
        a.setCity_post_code(address.getCity_post_code());
        a.setCountry(address.getCountry());
        return a;
    }
    
    public static Address convertDtoToBo(AddressDto address){
        if(address == null) return null;
        Address a = new Address();
        a.setId(address.getId());
        a.setStreet(address.getStreet());
        a.setStreet_number(address.getStreet_number());
        a.setCity(address.getCity());
        a.setCity_post_code(address.getCity_post_code());
        a.setCountry(address.getCountry());
        return a;
    }
    
}
