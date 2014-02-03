/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.view.bb;

import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicActorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicDirectorDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicMovieDto;
import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicWriterDto;
import com.cvut.spatnjak.wpa.moviedatabase.service.IActorService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IDirectorService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IMovieService;
import com.cvut.spatnjak.wpa.moviedatabase.service.IWriterService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kuba
 */
@Component("searchBB")
@Scope("request")
public class SearchBB {

    @Autowired
    private IActorService actorService;
    @Autowired
    private IDirectorService directorService;
    @Autowired
    private IWriterService writerService;
    @Autowired
    private IMovieService movieService;
    private String search_type;
    private String search_query;
    private List<BasicMovieDto> movieResults;
    private List<BasicActorDto> actorResults;
    private List<BasicDirectorDto> directorResults;
    private List<BasicWriterDto> writerResults;

    public String getSearch_type() {
        return search_type;
    }

    public void setSearch_type(String search_type) {
        this.search_type = search_type;
    }

    public String getSearch_query() {
        return search_query;
    }

    public void setSearch_query(String search_query) {
        this.search_query = search_query;
    }

    public List<BasicMovieDto> getMovieResults() {
        return movieResults;
    }

    public void setMovieResults(List<BasicMovieDto> movieResults) {
        this.movieResults = movieResults;
    }

    public List<BasicActorDto> getActorResults() {
        return actorResults;
    }

    public void setActorResults(List<BasicActorDto> actorResults) {
        this.actorResults = actorResults;
    }

    public List<BasicDirectorDto> getDirectorResults() {
        return directorResults;
    }

    public void setDirectorResults(List<BasicDirectorDto> directorResults) {
        this.directorResults = directorResults;
    }

    public List<BasicWriterDto> getWriterResults() {
        return writerResults;
    }

    public void setWriterResults(List<BasicWriterDto> writerResults) {
        this.writerResults = writerResults;
    }
    
    public String search() {

        String[] parsedQuery = search_query.split(" ", -1);

        if (getShowMovies()) {
            Set<BasicMovieDto> result = new HashSet<BasicMovieDto>();
            for (int i = 0; i < parsedQuery.length; i++) {
                List<BasicMovieDto> sub_result = movieService.searchByName(parsedQuery[i]);
                result.addAll(sub_result);
            }
            movieResults = new ArrayList<BasicMovieDto>();
            movieResults.addAll(result);
        } else if(getShowActors()){
            
            Set<BasicActorDto> result = new HashSet<BasicActorDto>();
            for (int i = 0; i < parsedQuery.length; i++) {
                List<BasicActorDto> sub_result = actorService.searchActorByFirstName(parsedQuery[i]);
                result.addAll(sub_result);
                sub_result = actorService.searchActorByLastName(parsedQuery[i]);
                result.addAll(sub_result);
            }
            actorResults = new ArrayList<BasicActorDto>();
            actorResults.addAll(result);
            
        } else if(getShowDirectors()){
            
            Set<BasicDirectorDto> result = new HashSet<BasicDirectorDto>();
            for (int i = 0; i < parsedQuery.length; i++) {
                List<BasicDirectorDto> sub_result = directorService.searchDirectorsByFirstName(parsedQuery[i]);
                result.addAll(sub_result);
                sub_result = directorService.searchDirectorsByLastName(parsedQuery[i]);
                result.addAll(sub_result);
            }
            directorResults = new ArrayList<BasicDirectorDto>();
            directorResults.addAll(result);
            
        } else if(getShowWriters()){
            
            Set<BasicWriterDto> result = new HashSet<BasicWriterDto>();
            for (int i = 0; i < parsedQuery.length; i++) {
                List<BasicWriterDto> sub_result = writerService.searchWritersByFirstName(parsedQuery[i]);
                result.addAll(sub_result);
                sub_result = writerService.searchWritersByLastName(parsedQuery[i]);
                result.addAll(sub_result);
            }
            writerResults = new ArrayList<BasicWriterDto>();
            writerResults.addAll(result);
            
        }

        return "";
    }

    public boolean getShowMovies() {
        return "Movie".equals(search_type);
    }

    public boolean getShowActors() {
        return "Actor".equals(search_type);
    }

    public boolean getShowDirectors() {
        return "Director".equals(search_type);
    }

    public boolean getShowWriters() {
        return "Writer".equals(search_type);
    }
}
