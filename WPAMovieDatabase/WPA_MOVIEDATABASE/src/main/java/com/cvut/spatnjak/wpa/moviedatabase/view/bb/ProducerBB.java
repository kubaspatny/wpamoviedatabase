package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.ActorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicMovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.ProductionCompanyDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IActorService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IProducerService;
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

@Component("producerBB")
@Scope("request")
public class ProducerBB {

    Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    private ProductionCompanyDto producer;
    
    @Autowired
    private IProducerService producerService;
    
    @Autowired
    private AutocompleteBB autoCompleteBB;

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

        producer = producerService.getProducerById(id);
        if (producer == null) {
            error();
            return;
        }        

    }

    public ProductionCompanyDto getProducer() {
        return producer;
    }

    public void setProducer(ProductionCompanyDto producer) {
        this.producer = producer;
    }
    
    public List<BasicMovieDto> getMovies(){
        ArrayList<BasicMovieDto> result = new ArrayList<BasicMovieDto>();
        Iterator<BasicMovieDto> it = this.producer.getProduced_movies().iterator();
        while(it.hasNext()){
           result.add(it.next());
        }
       
        return result;
    }
    
    public String delete(){
        producerService.deleteProducer(producer.getId());
        autoCompleteBB.updateProducers();
        return "index";
    }
    
    private void error() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().responseSendError(404, "Producer with this ID doesn't exist");
        facesContext.responseComplete();
    }

    
}
