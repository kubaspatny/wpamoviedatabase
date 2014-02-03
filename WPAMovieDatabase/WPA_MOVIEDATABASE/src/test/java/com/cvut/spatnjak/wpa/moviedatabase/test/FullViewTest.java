///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.cvut.spatnjak.wpa.moviedatabase.test;
//
//import com.cvut.spatnjak.wpa.moviedatabase.bo.User;
//import com.cvut.spatnjak.wpa.moviedatabase.dto.ActorDto;
//import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicActorDto;
//import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicDirectorDto;
//import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicMovieDto;
//import com.cvut.spatnjak.wpa.moviedatabase.dto.BasicWriterDto;
//import com.cvut.spatnjak.wpa.moviedatabase.dto.DirectorDto;
//import com.cvut.spatnjak.wpa.moviedatabase.dto.MovieDto;
//import com.cvut.spatnjak.wpa.moviedatabase.dto.ProductionCompanyDto;
//import com.cvut.spatnjak.wpa.moviedatabase.dto.UserDto;
//import com.cvut.spatnjak.wpa.moviedatabase.dto.WriterDto;
//import com.cvut.spatnjak.wpa.moviedatabase.service.IActorService;
//import com.cvut.spatnjak.wpa.moviedatabase.service.IDirectorService;
//import com.cvut.spatnjak.wpa.moviedatabase.service.IMovieService;
//import com.cvut.spatnjak.wpa.moviedatabase.service.IWriterService;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// *
// * @author Kuba
// */
//public class FullViewTest extends AbstractServiceTest {
//    
//    @Autowired
//    private IMovieService movieService;
//    
//    @Autowired
//    private IActorService actorService;
//    
//    @Autowired
//    private IDirectorService directorService;
//    
//    @Autowired
//    private IWriterService writerService;
//    
//    @Test
//    public void testGetAllMovies(){
//        
//        List<MovieDto> movies = movieService.getAllMovies();
//        System.out.println("\n\n--- TEST GET ALL MOVIES ---");
//        for (MovieDto m : movies) {
//            System.out.println("============================");
//            System.out.println(m.getName());
//            System.out.println(m.getGenre());
//            System.out.println(m.getAge_rating());
//            System.out.println("$" + m.getBudget());
//            System.out.println("Short Info: " + m.getShort_info());
//            System.out.println("Long Info: " + m.getLong_info());
//            System.out.println("CAST:");
////            Map<String, BasicActorDto> cast = m.getCast();
////            for (Map.Entry<String, BasicActorDto> e : cast.entrySet()) {
////                BasicActorDto b = e.getValue();
////                System.out.println(b.getFirst_name() + " " + b.getLast_name() + " as " + e.getKey());
////            }
//            BasicDirectorDto d = m.getDirector();
//            if(d != null){
//                System.out.println("DIRECTOR: " + d.getFirst_name() + " " + d.getLast_name());
//            } else {
//                System.out.println("DIRECTOR: Not added yet.");
//            }
//            
//            BasicWriterDto w = m.getWriter();
//            if(w != null){
//                System.out.println("WRITER: " + w.getFirst_name() + " " + w.getLast_name());
//            } else {
//                System.out.println("WRITER: Not added yet.");
//            }
//            
//            ProductionCompanyDto p = m.getProducer();
//            if(p != null){
//                System.out.println("PRODUCTION COMPANY: " + p.getName());
//            } else {
//                System.out.println("PRODUCTION COMPANY: Not added yet.");
//            }
//            
//            UserDto u = m.getCreatedBy();
//            if(u != null){
//                System.out.println("CREATED BY: " + u.getUsername());
//            } else {
//                System.out.println("CREATED BY: Not added yet.");
//            }
//        }
//        System.out.println("---------------------------\n\n");
//        
//    }
//
//    @Test
//    public void testGetAllActors(){
//        
//        List<ActorDto> actors = actorService.getAllActors();
//        System.out.println("\n\n--- TEST GET ALL ACTORS ---");
//        for (ActorDto a : actors) {
//            System.out.println("============================");
//            System.out.println(a.getFirst_name() + " " + a.getLast_name() + "(" + a.getId() + ")");
//            System.out.println("Biography: " + a.getBiography());
//            System.out.println("Date of Birth: " + a.getDate_of_birth());
//            System.out.println("URL: " + a.getImage_url());
//            Set<BasicMovieDto> movies = a.getMovies();
//            System.out.println("MOVIES:");
//            for (BasicMovieDto m : movies) {
//                System.out.println(m.getName());
//            }
//        }
//        System.out.println("-----------------------------\n\n");
//    }
//    
//    @Test
//    public void testGetAllDirectors(){
//        List<DirectorDto> directors = directorService.getAllDirectors();
//        System.out.println("\n\n--- TEST GET ALL DIRECTORS ---");
//        for (DirectorDto d : directors) {
//            System.out.println("============================");
//            System.out.println(d.getFirst_name() + " " + d.getLast_name() + "(" + d.getId() + ")");
//            System.out.println("Biography: " + d.getBiography());
//            System.out.println("Date of Birth: " + d.getDate_of_birth());
//            System.out.println("URL: " + d.getImage_url());
//            Set<BasicMovieDto> movies = d.getDirected_movies();
//            System.out.println("MOVIES:");
//            for (BasicMovieDto m : movies) {
//                System.out.println(m.getName());
//            }
//        }
//        System.out.println("--------------------------------\n\n");
//    }
//    
//    @Test
//    public void testGetAllWriters(){
//       List<WriterDto> writers = writerService.getAllWriters();
//        System.out.println("\n\n--- TEST GET ALL WRITERS ---");
//        for (WriterDto w : writers) {
//            System.out.println("============================");
//            System.out.println(w.getFirst_name() + " " + w.getLast_name() + "(" + w.getId() + ")");
//            System.out.println("Biography: " + w.getBiography());
//            System.out.println("Date of Birth: " + w.getDate_of_birth());
//            System.out.println("URL: " + w.getImage_url());
//            Set<BasicMovieDto> movies = w.getWritten_movies();
//            System.out.println("MOVIES:");
//            for (BasicMovieDto m : movies) {
//                System.out.println(m.getName());
//            }
//        }
//        System.out.println("------------------------------\n\n");
//    }
//    
//    
//    
//} // class
