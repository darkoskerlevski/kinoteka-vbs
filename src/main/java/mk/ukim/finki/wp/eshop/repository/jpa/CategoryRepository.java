package mk.ukim.finki.wp.eshop.repository.jpa;

import mk.ukim.finki.wp.eshop.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Genre, Long> {
    List<Genre> findAllByNameLike(String text);
    Genre findByName(String name);
    void deleteByName(String name);
}
