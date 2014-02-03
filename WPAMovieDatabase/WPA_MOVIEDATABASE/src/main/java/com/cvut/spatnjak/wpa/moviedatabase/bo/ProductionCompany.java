package com.cvut.spatnjak.wpa.moviedatabase.bo;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

/**
 *
 * @author Kuba Spatny
 */

@Entity
@Table(name = "Producers")
public class ProductionCompany extends AbstractBusinessObject{
    
    @Column(nullable = false, unique = true)
    private String name;
    
    @OneToMany(mappedBy = "producer")
    private Set<Movie> produced_movies;
    
    @PreRemove
    public void removeProducerFromMovies(){
        for (Movie m : produced_movies) {
            m.setProducer(null);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movie> getProduced_movies() {
        return produced_movies;
    }

    public void setProduced_movies(Set<Movie> produced_movies) {
        this.produced_movies = produced_movies;
    }
    
    
    
}
