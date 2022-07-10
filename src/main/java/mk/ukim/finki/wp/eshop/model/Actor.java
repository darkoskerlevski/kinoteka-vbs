package mk.ukim.finki.wp.eshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String url;

    @ManyToMany
    private List<Movie> movies;

    public Actor() {
    }

    public Actor(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
