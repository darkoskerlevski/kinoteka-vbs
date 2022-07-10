package mk.ukim.finki.wp.eshop.web.controller;

import mk.ukim.finki.wp.eshop.model.Actor;
import mk.ukim.finki.wp.eshop.model.Genre;
import mk.ukim.finki.wp.eshop.model.Movie;
import mk.ukim.finki.wp.eshop.service.CategoryService;
import mk.ukim.finki.wp.eshop.service.MovieService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final CategoryService categoryService;

    public MovieController(MovieService movieService,
                           CategoryService categoryService) {
        this.movieService = movieService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getMoviePage(@RequestParam(required = false) String genre, @RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Movie> movies = this.movieService.findAll();
        List<Genre> genres = this.categoryService.listCategories();
        model.addAttribute("genres", genres);
        if(genre != null && !genre.equalsIgnoreCase("All genres")){
            model.addAttribute("movies", movieService.findByGenre(genre));
            model.addAttribute("selectedGenre", genre);
        }
        else model.addAttribute("movies", movies);
        model.addAttribute("bodyContent", "movies");
        return "master-template";
    }

    @RequestMapping(value = "/{genre}", method = RequestMethod.GET)
    public String getMoviesByGenrePage(@PathVariable String genre, Model model){
        model.addAttribute("bodyContent", "movies");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        this.movieService.deleteById(id);
        return "redirect:/movies";
    }

    @GetMapping("/edit-form/{id}")
    public String editMoviePage(@PathVariable Long id, Model model) {
        if (this.movieService.findById(id).isPresent()) {
            Movie movie = this.movieService.findById(id).get();
            List<Genre> categories = this.categoryService.listCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("movie", movie);
            model.addAttribute("bodyContent", "add-Movie");
            return "master-template";
        }
        return "redirect:/movies?error=MovieNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addMoviePage(Model model) {
        List<Genre> categories = this.categoryService.listCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-movie");
        return "master-template";
    }

    @GetMapping("/details/{id}")
    public String getMovieDetailsPage(@PathVariable Long id, Model model){
        Movie movie = this.movieService.findById(id).get();
        model.addAttribute("movie", movie);
        model.addAttribute("bodyContent", "movieDetails");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveMovie(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam(required = false) String desc,
            @RequestParam Long category) {
        if (id != null) {
            this.movieService.edit(id, name, category);
        } else {
            this.movieService.save(name, desc, category);
        }
        return "redirect:/movies";
    }
}
