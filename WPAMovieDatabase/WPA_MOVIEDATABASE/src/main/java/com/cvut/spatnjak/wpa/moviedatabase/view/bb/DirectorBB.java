/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.ActorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicMovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.DirectorDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IDirectorService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("directorBB")
@Scope("request")
public class DirectorBB {

    Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    private DirectorDto director;
    @Autowired
    private IDirectorService directorService;

    @PostConstruct
    public void validate() throws IOException {
        String id_string = params.get("id");
        if (id_string == null || id_string.isEmpty()) {
            return;
        }
        Long id = -1l;
        try {
            id = Long.parseLong(id_string);
        } catch (NumberFormatException n) {
            error();
            return;
        }

        director = directorService.getDirectorById(id);
        if (director == null) {
            error();
            return;
        }
    }

    public DirectorDto getDirector() {
        return director;
    }

    public void setDirector(DirectorDto director) {
        this.director = director;
    }

    public List<BasicMovieDto> getMovies(){
        ArrayList<BasicMovieDto> result = new ArrayList<BasicMovieDto>();
        Iterator<BasicMovieDto> it = this.director.getDirected_movies().iterator();
        while(it.hasNext()){
           result.add(it.next());
        }
       
        return result;
    }
    
    private void error() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().responseSendError(404, "Director with this ID doesn't exist");
        facesContext.responseComplete();
    }

    
}
