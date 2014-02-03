/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.convert;

import com.cvut.spatnjak.wpa.moviedatabase.bo.User;
import com.cvut.spatnjak.wpa.moviedatabase.dto.UserDto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Kuba
 */
public class UserDtoConvertor {

    public static UserDto convertUser(User user) {
        if (user == null) {
            return null;
        }
        UserDto u = new UserDto();
        u.setId(user.getId());
        u.setFirst_name(user.getFirst_name());
        u.setLast_name(user.getLast_name());
        u.setBiography(user.getBiography());
        u.setDate_of_birth(user.getDate_of_birth());
        u.setImage_url(user.getImage_url());
        u.setUsername(user.getUsername());
        u.setIsAdmin(user.isAdmin());
        u.setEmail(user.getEmail());
        u.setCreated_movies(BasicMovieDtoConvert.convertMovies(user.getCreated_movies()));
        Set<Long> rated_movies = new HashSet<Long>();
        if (user.getRatedMovies() != null) {
            for (Long l : user.getRatedMovies()) {
                rated_movies.add(l);
            }
        }
        u.setRated_movies(rated_movies);
        u.setAddress(AddressDtoConvertor.convertAddress(user.getUser_address()));
        return u;
    }

    public static List<UserDto> convertUserList(List<User> users) {
        List<UserDto> users_result = new ArrayList<UserDto>();

        if (users == null) {
            return users_result;
        }

        for (User u : users) {
            users_result.add(UserDtoConvertor.convertUser(u));
        }

        return users_result;

    }
}
