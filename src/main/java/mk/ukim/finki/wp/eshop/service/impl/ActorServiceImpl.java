package mk.ukim.finki.wp.eshop.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.eshop.model.Actor;
import mk.ukim.finki.wp.eshop.model.Genre;
import mk.ukim.finki.wp.eshop.repository.jpa.ActorRepository;
import mk.ukim.finki.wp.eshop.service.ActorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    @Override
    public Actor create(String name, String url) {
        Actor actor = new Actor(name, url);
        return this.actorRepository.save(actor);
    }

    @Override
    public Actor update(String name, String url) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Actor a= new Actor(name,url);
        actorRepository.save(a);
        return a;
    }

    @Override
    public void delete(String name) {
        actorRepository.delete(actorRepository.findByName(name));
    }

    @Override
    public List<Actor> listActors() {
        return this.actorRepository.findAll();
    }
}
