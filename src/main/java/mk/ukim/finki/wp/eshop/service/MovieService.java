package mk.ukim.finki.wp.eshop.service;

import mk.ukim.finki.wp.eshop.model.Actor;
import mk.ukim.finki.wp.eshop.model.Movie;
import mk.ukim.finki.wp.eshop.model.dto.MovieDto;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> findAll();

    Optional<Movie> findById(Long id);

    Optional<Movie> findByName(String name);

    List<Movie> findByGenre(String genre);

    Movie save(String name, String desc, Long category, List<Actor> actors);

    Movie save(MovieDto movieDto);

    Movie edit(Long id, String name, Long category);

    Movie edit(Long id, MovieDto movieDto);

    void deleteById(Long id);
}
