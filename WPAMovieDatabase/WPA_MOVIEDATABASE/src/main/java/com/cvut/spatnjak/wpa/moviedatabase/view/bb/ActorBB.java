package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.ActorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicMovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IActorService;
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

@Component("actorBB")
@Scope("request")
public class ActorBB {

    Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    private ActorDto actor;
    @Autowired
    private IActorService actorService;

    @PostConstruct
    public void validate() throws IOException {
        
        String id_string = params.get("id");
        System.out.println("ID STRING: "  + id_string);
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

        actor = actorService.getActorById(id);
        System.out.println("ACTOR: " + actor);
        if (actor == null) {
            error();
            return;
        }


    }

    public ActorDto getActor() {
        return actor;
    }

    public void setActor(ActorDto actor) {
        this.actor = actor;
    }
    
    public List<BasicMovieDto> getMovies(){
        ArrayList<BasicMovieDto> result = new ArrayList<BasicMovieDto>();
        Iterator<BasicMovieDto> it = this.actor.getMovies().iterator();
        while(it.hasNext()){
           result.add(it.next());
        }
       
        return result;
    }
    
    private void error() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().responseSendError(404, "Actor with this ID doesn't exist");
        facesContext.responseComplete();
    }

    
}
