package mk.ukim.finki.wp.eshop.service;

import mk.ukim.finki.wp.eshop.model.Movie;
import mk.ukim.finki.wp.eshop.model.dto.MovieDto;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    List<Movie> findAll();

    Optional<Movie> findById(Long id);

    Optional<Movie> findByName(String name);

    Optional<Movie> save(String name, Long category);

    Optional<Movie> save(MovieDto movieDto);

    Optional<Movie> edit(Long id, String name, Long category);

    Optional<Movie> edit(Long id, MovieDto movieDto);

    void deleteById(Long id);
}
