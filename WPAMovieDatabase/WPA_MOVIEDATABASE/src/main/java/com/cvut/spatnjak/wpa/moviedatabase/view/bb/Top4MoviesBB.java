package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicActorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.MovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.RoleDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IMovieService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba Spatny
 */
@Component("top4MoviesBB")
@Scope("request")
public class Top4MoviesBB {
    
    @Autowired
    private IMovieService movieService;
    
    private List<MovieDto> top4;
    
    private MovieDto getM(int movie){
        if(movie == 1) top4 = movieService.getLatest4();
        
        if(top4.size() < movie) return null;
        return top4.get(movie-1);
    }
    
    public MovieDto getM1() {
        return getM(1);
    }

    public MovieDto getM2() {
        return getM(2);
    }

    public MovieDto getM3() {
        return getM(3);
    }

    public MovieDto getM4() {
        return getM(4);
    }
    
    public List<BasicActorDto> getS1(){
        return getStars(1);
    }
    public List<BasicActorDto> getS2(){
        return getStars(2);
    }
    public List<BasicActorDto> getS3(){
        return getStars(3);
    }
    public List<BasicActorDto> getS4(){
        return getStars(4);
    }
    
    private List<BasicActorDto> getStars(int movie){
        MovieDto m = getM(movie);
        if(m==null) return null;
        List<BasicActorDto> result = new ArrayList<BasicActorDto>();
        int max = (m.getCast().size() < 3 ? m.getCast().size() : 3);
        Iterator<RoleDto> it = m.getCast().iterator();
        for(int i = 0; i < max; i++){
            result.add(it.next().getActor());
        }
        return result;
    }
    
}
