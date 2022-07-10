//package mk.ukim.finki.wp.eshop.repository.impl;
//
//import mk.ukim.finki.wp.eshop.bootstrap.DataHolder;
//import mk.ukim.finki.wp.eshop.model.Genre;
//import mk.ukim.finki.wp.eshop.model.Movie;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class InMemoryMovieRepository {
//
//    public List<Movie> findAll() {
//        return DataHolder.movies;
//    }
//
//    public Optional<Movie> findById(Long id) {
//        return DataHolder.movies.stream().filter(i -> i.getId().equals(id)).findFirst();
//    }
//
//    public Optional<Movie> findByName(String name) {
//        return DataHolder.movies.stream().filter(i -> i.getName().equals(name)).findFirst();
//    }
//
//    public List<Movie> findByGenre(String genre){
//        return DataHolder.movies.stream().filter(i -> i.getGenre().getName().equals(genre)).collect(Collectors.toList());
//    }
//
//    public void deleteById(Long id) {
//        DataHolder.movies.removeIf(i -> i.getId().equals(id));
//    }
//
//    public void deleteByName(String name) { DataHolder.movies.removeIf(i -> i.getName().equals(name));}
//
//    public Optional<Movie> save(String name,
//                                Genre genre) {
//        DataHolder.movies.removeIf(i -> i.getName().equals(name));
//        Movie movie = new Movie(name, genre);
//        DataHolder.movies.add(movie);
//        return Optional.of(movie);
//    }
//}
