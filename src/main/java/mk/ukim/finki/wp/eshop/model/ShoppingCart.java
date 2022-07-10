package mk.ukim.finki.wp.eshop.model;

import lombok.Data;
import mk.ukim.finki.wp.eshop.model.enumerations.ShoppingCartStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="my_seq_gen")
    @SequenceGenerator(name="my_seq_gen", sequenceName="ENTITY_SEQ")
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Movie> movies;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.movies = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }
}
