package com.cvut.spatnjak.wpa.moviedatabase.bo;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PreRemove;
import javax.persistence.Table;

/**
 *
 * @author Kuba Spatny
 */


@Entity
@NamedQuery(name = "User.getAdmins", query = "SELECT e FROM User e WHERE e.isAdmin = TRUE")
@Table(name = "Users")
public class User extends Person implements IRegisteredUser {

    @Column(unique = true, updatable = false, nullable = false)
    private String username;
    private String password;
    
    @Column(unique = true)
    private String email;
    
    @Column(nullable = false)
    private boolean isAdmin = false;
    
    @Override
    public boolean isAdmin() {
        return isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @OneToMany(mappedBy = "createdBy")
    @OrderBy(value = "name")
    private Set<Movie> created_movies;

    public Set<Movie> getCreated_movies() {
        return created_movies;
    }

    public void setCreated_movies(Set<Movie> created_movies) {
        this.created_movies = created_movies;
    }
    
    @PreRemove
    public void removeUserFromMovies(){
        if(created_movies == null) return;
        for (Movie m : created_movies) {
            m.setCreatedBy(null);
        }
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    @ElementCollection
    @Column(name="rated_ID")
    private Set<Long> ratedMovies;

    public Set<Long> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(Set<Long> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }
    
    public boolean hasRated(Long movieID){
        return ratedMovies.contains(movieID);
    }
    
    public void addRatedMovie(Long movieID){
        this.ratedMovies.add(movieID);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})// PERSIST - can create new address without saving it first, thus no need for Address Service
    private Address user_address;                                                         // REMOVE - when user instance is removed, the remove action is cascaded to address as well 

    public Address getUser_address() {
        return user_address;
    }

    public void setUser_address(Address user_address) {
        this.user_address = user_address;
    }
    
}
