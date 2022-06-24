package mk.ukim.finki.wp.eshop.model.dto;

import lombok.Data;

@Data
public class MovieDto {

    private String name;
    private Long category;

    public MovieDto() {
    }

    public MovieDto(String name, Double price, Integer quantity, Long category, Long manufacturer) {
        this.name = name;
        this.category = category;
    }
}
