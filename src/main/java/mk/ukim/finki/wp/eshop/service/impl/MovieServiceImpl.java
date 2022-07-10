package mk.ukim.finki.wp.eshop.service.impl;

import mk.ukim.finki.wp.eshop.model.Actor;
import mk.ukim.finki.wp.eshop.model.Genre;
import mk.ukim.finki.wp.eshop.model.Movie;
import mk.ukim.finki.wp.eshop.model.dto.MovieDto;
import mk.ukim.finki.wp.eshop.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.wp.eshop.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.wp.eshop.repository.jpa.CategoryRepository;
import mk.ukim.finki.wp.eshop.repository.jpa.MovieRepository;
import mk.ukim.finki.wp.eshop.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    public MovieServiceImpl(MovieRepository movieRepository,
                            CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Movie> findAll() {
        return this.movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return this.movieRepository.findById(id);
    }

    @Override
    public Optional<Movie> findByName(String name) {
        return this.movieRepository.findByName(name);
    }

    @Override
    public List<Movie> findByGenre(String genre) {
        Genre g = this.categoryRepository.findByName(genre);
        return this.movieRepository.findByGenre(g);
    }

    @Override
    @Transactional
    public Movie save(String name, String desc, Long categoryId) {
        Genre genre = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        this.movieRepository.deleteByName(name);
        Movie m = new Movie(name, desc, genre);
        return this.movieRepository.save(m);
    }

    @Override
    public Movie save(MovieDto movieDto) {
        Genre genre = this.categoryRepository.findById(movieDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(movieDto.getCategory()));

        this.movieRepository.deleteByName(movieDto.getName());
        Movie m = new Movie(movieDto.getName(), movieDto.getDescription(), genre);
        m.setActors(movieDto.getActors());
        return this.movieRepository.save(m);
    }

    @Override
    @Transactional
    public Movie edit(Long id, String name, Long categoryId) {

        Movie movie = this.movieRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        movie.setName(name);

        Genre genre = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        movie.setGenre(genre);

        Movie m = new Movie(movie.getName(), movie.getDescription(), movie.getGenre());
        m.setActors(movie.getActors());
        return this.movieRepository.save(m);
    }

    @Override
    public Movie edit(Long id, MovieDto movieDto) {
        Movie movie = this.movieRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        movie.setName(movieDto.getName());

        Genre genre = this.categoryRepository.findById(movieDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(movieDto.getCategory()));
        movie.setGenre(genre);

        Movie m = new Movie(movie.getName(), movie.getDescription(), movie.getGenre());
        m.setActors(movie.getActors());
        return this.movieRepository.save(m);
    }

    @Override
    public void deleteById(Long id) {
        this.movieRepository.deleteById(id);
    }
}
