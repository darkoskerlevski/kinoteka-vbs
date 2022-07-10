package mk.ukim.finki.wp.eshop.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Actor> actors;

    public Movie() {
        this.actors = new ArrayList<>();
    }

    public Movie(String name, String desc, Genre genre) {
        this.name = name;
        this.description = desc;
        this.genre = genre;
        this.actors = new ArrayList<>();
    }


}
