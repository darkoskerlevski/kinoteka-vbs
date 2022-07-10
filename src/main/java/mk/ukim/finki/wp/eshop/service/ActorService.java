package mk.ukim.finki.wp.eshop.service;

import mk.ukim.finki.wp.eshop.model.Actor;
import mk.ukim.finki.wp.eshop.model.Genre;

import java.util.List;

public interface ActorService {

    Actor create(String name, String url);

    Actor update(String name, String url);

    void delete(String name);

    List<Actor> listActors();

}
