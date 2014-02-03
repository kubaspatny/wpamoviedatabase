package com.cvut.spatnjak.wpa.moviedatabase.bo;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

/**
 *
 * @author Kuba Spatny
 */
@Entity
@Table(name = "Actors")
public class Actor extends Person{
    
    @ManyToMany(mappedBy = "cast")
    private Set<Movie> movies;

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
    
    @PreRemove
    private void removeActorFromMovies() {
        if(movies == null) return;
        for (Movie m : movies) {
            m.removeCastMember(this);
        }
    }
    
}
