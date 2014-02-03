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
@Table(name = "Directors")
public class Director extends Person{
    
    @OneToMany(mappedBy = "director")
    private Set<Movie> directed_movies;

    public Set<Movie> getDirected_movies() {
        return directed_movies;
    }

    public void setDirected_movies(Set<Movie> written_movies) {
        this.directed_movies = written_movies;
    }
    
    @PreRemove
    public void removeDirectorFromMovies(){
        for (Movie m : directed_movies) {
            m.setDirector(null);
        }
    }
    
}
