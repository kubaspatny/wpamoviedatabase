/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicMovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.WriterDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IWriterService;
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

@Component("writerBB")
@Scope("request")
public class WriterBB {

    Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
    private WriterDto writer;
    @Autowired
    private IWriterService writerService;

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

        writer = writerService.getWriterById(id);
        if (writer == null) {
            error();
            return;
        }


    }

    public WriterDto getWriter() {
        return writer;
    }

    public void setWriter(WriterDto writer) {
        this.writer = writer;
    }
    
    public List<BasicMovieDto> getMovies(){
        ArrayList<BasicMovieDto> result = new ArrayList<BasicMovieDto>();
        Iterator<BasicMovieDto> it = this.writer.getWritten_movies().iterator();
        while(it.hasNext()){
           result.add(it.next());
        }
       
        return result;
    }
    
    private void error() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().responseSendError(404, "Writer with this ID doesn't exist");
        facesContext.responseComplete();
    }

    
}
