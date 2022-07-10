package mk.ukim.finki.wp.eshop.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Type(type = "text")
    private String description;

    @ManyToOne
    private Genre genre;

    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors;

    public Movie() {
    }

    public Movie(String name, String desc, Genre genre, List<Actor> actors) {
        this.name = name;
        this.description = desc;
        this.genre = genre;
    }
}
