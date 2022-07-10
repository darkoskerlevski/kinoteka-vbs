package mk.ukim.finki.wp.eshop.model.dto;

import lombok.Data;
import mk.ukim.finki.wp.eshop.model.Actor;

import java.util.List;

@Data
public class MovieDto {

    private String name;
    private String description;
    private Long category;
    private List<Actor> actors;

    public MovieDto() {
    }

    public MovieDto(String name, String description, List<Actor> actors, Double price, Integer quantity, Long category, Long manufacturer) {
        this.name = name;
        this.description = description;
        this.actors = actors;
        this.category = category;
    }
}
