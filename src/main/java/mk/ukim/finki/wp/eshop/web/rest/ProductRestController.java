package mk.ukim.finki.wp.eshop.web.rest;

import mk.ukim.finki.wp.eshop.model.Movie;
import mk.ukim.finki.wp.eshop.model.dto.MovieDto;
import mk.ukim.finki.wp.eshop.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/products")
public class ProductRestController {

    private final MovieService movieService;

    public ProductRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    private List<Movie> findAll() {
        return this.movieService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findById(@PathVariable Long id) {
        return this.movieService.findById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Movie> save(@RequestBody MovieDto movieDto) {
        return this.movieService.save(movieDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Movie> save(@PathVariable Long id, @RequestBody MovieDto movieDto) {
        return this.movieService.edit(id, movieDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.movieService.deleteById(id);
        if(this.movieService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
