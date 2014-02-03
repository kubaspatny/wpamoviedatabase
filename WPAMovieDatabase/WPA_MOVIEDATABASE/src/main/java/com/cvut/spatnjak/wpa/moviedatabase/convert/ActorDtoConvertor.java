package com.cvut.spatnjak.wpa.moviedatabase.convert;

import com.cvut.spatnjak.wpa.moviedatabase.bo.Actor;
import com.cvut.spatnjak.wpa.moviedatabase.dto.ActorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicActorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.RoleDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Kuba Spatny
 */
public class ActorDtoConvertor {
    
    public static BasicActorDto convertActorToBasic(Actor actor){
        if(actor == null) return null;
        BasicActorDto a = new BasicActorDto();
        a.setId(actor.getId());
        a.setFirst_name(actor.getFirst_name());
        a.setLast_name(actor.getLast_name());        
        return a;
    }
    
    public static List<RoleDto> convertActorRoleMapToBasic(Map<String, Actor> cast){
//       Map<String, BasicActorDto> c = new HashMap<String, BasicActorDto>();
//       if(cast == null){
//           return c;
//       }
//       for(Entry<String, Actor> e : cast.entrySet()){
//           c.put(e.getKey(), convertActorToBasic(e.getValue()));
//       }
//        
//       return c;
        
        List<RoleDto> list = new ArrayList<RoleDto>();
        if(cast == null){return list;}
        for (Entry<String, Actor> e : cast.entrySet()) {
            list.add(new RoleDto(e.getKey(), convertActorToBasic(e.getValue())));
        }
        return list;
    }
    
    public static ActorDto convertActorToFull(Actor actor){
        if(actor == null) return null;
        ActorDto a = new ActorDto();
        a.setId(actor.getId());
        a.setFirst_name(actor.getFirst_name());
        a.setLast_name(actor.getLast_name());
        a.setBiography(actor.getBiography());
        a.setDate_of_birth(actor.getDate_of_birth());
        a.setImage_url(actor.getImage_url());
        a.setMovies(actor.getMovies());
        return a;
    }
    
    public static List<ActorDto> convertActorList(List<Actor> actors){
        List<ActorDto> actors_result = new ArrayList<ActorDto>();
        
        if(actors == null) return actors_result;
        
        for (Actor a : actors) {
            actors_result.add(ActorDtoConvertor.convertActorToFull(a));
        }
        
        return actors_result;
    }
    
    public static List<BasicActorDto> convertActorBasicList(List<Actor> actors){
        List<BasicActorDto> actors_result = new ArrayList<BasicActorDto>();
        
        if(actors == null) return actors_result;
        
        for (Actor a : actors) {
            actors_result.add(ActorDtoConvertor.convertActorToBasic(a));
        }
        
        return actors_result;
    }
    
}
