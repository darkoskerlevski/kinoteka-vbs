package mk.ukim.finki.wp.eshop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Genre genre;


    public Movie() {
    }

    public Movie(String name, Genre genre) {
        this.name = name;
        this.genre = genre;
    }
}
