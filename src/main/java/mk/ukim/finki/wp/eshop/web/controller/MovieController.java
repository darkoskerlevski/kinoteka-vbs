package mk.ukim.finki.wp.eshop.web.controller;

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
@RequestMapping("/Movies")
public class MovieController {

    private final MovieService movieService;
    private final CategoryService categoryService;

    public MovieController(MovieService movieService,
                           CategoryService categoryService) {
        this.movieService = movieService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getMoviePage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Movie> movies = this.movieService.findAll();
        model.addAttribute("movies", movies);
        model.addAttribute("bodyContent", "Movies");
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

    @PostMapping("/add")
    public String saveMovie(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Long category) {
        if (id != null) {
            this.movieService.edit(id, name, category);
        } else {
            this.movieService.save(name, category);
        }
        return "redirect:/movies";
    }
}
