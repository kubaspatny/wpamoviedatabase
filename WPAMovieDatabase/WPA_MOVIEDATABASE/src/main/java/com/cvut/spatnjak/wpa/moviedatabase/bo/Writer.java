package com.cvut.spatnjak.wpa.moviedatabase.bo;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

/**
 *
 * @author Kuba Spatny
 */
@Entity
@Table(name = "Writers")
public class Writer extends Person {
    
    @OneToMany(mappedBy = "writer")
    private Set<Movie> written_movies;

    public Set<Movie> getWritten_movies() {
        return written_movies;
    }

    public void setWritten_movies(Set<Movie> written_movies) {
        this.written_movies = written_movies;
    }
    
    @PreRemove
    public void removeWriterFromMovies(){
        for (Movie m : written_movies) {
            m.setWriter(null);
        }
    }
    
}
