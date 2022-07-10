package mk.ukim.finki.wp.eshop.service.impl;

import mk.ukim.finki.wp.eshop.model.Genre;
import mk.ukim.finki.wp.eshop.model.Movie;
import mk.ukim.finki.wp.eshop.model.dto.MovieDto;
import mk.ukim.finki.wp.eshop.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.wp.eshop.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.wp.eshop.repository.impl.InMemoryCategoryRepository;
import mk.ukim.finki.wp.eshop.repository.impl.InMemoryMovieRepository;
import mk.ukim.finki.wp.eshop.repository.jpa.CategoryRepository;
import mk.ukim.finki.wp.eshop.repository.jpa.MovieRepository;
import mk.ukim.finki.wp.eshop.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final InMemoryMovieRepository movieRepository;
    private final InMemoryCategoryRepository categoryRepository;

    public MovieServiceImpl(InMemoryMovieRepository movieRepository,
                            InMemoryCategoryRepository categoryRepository) {
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
        return this.movieRepository.findByGenre(genre);
    }

    @Override
    @Transactional
    public Optional<Movie> save(String name, Long categoryId) {
        Genre genre = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));

        this.movieRepository.deleteByName(name);
        return this.movieRepository.save(name, genre);
    }

    @Override
    public Optional<Movie> save(MovieDto movieDto) {
        Genre genre = this.categoryRepository.findById(movieDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(movieDto.getCategory()));

        this.movieRepository.deleteByName(movieDto.getName());
        return this.movieRepository.save(movieDto.getName(), genre);
    }

    @Override
    @Transactional
    public Optional<Movie> edit(Long id, String name, Long categoryId) {

        Movie movie = this.movieRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        movie.setName(name);

        Genre genre = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        movie.setGenre(genre);

        return this.movieRepository.save(movie.getName(), movie.getGenre());
    }

    @Override
    public Optional<Movie> edit(Long id, MovieDto movieDto) {
        Movie movie = this.movieRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        movie.setName(movieDto.getName());

        Genre genre = this.categoryRepository.findById(movieDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(movieDto.getCategory()));
        movie.setGenre(genre);

        return this.movieRepository.save(movie.getName(), movie.getGenre());
    }

    @Override
    public void deleteById(Long id) {
        this.movieRepository.deleteById(id);
    }
}
