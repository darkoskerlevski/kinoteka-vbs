package mk.ukim.finki.wp.eshop.repository.jpa;

import mk.ukim.finki.wp.eshop.model.Actor;
import mk.ukim.finki.wp.eshop.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findAllByNameLike(String text);
    Actor findByName(String name);
    void deleteByName(String name);
}