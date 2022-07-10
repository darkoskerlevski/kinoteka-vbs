package mk.ukim.finki.wp.eshop.bootstrap;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.eshop.model.*;
import mk.ukim.finki.wp.eshop.repository.jpa.CategoryRepository;
import mk.ukim.finki.wp.eshop.repository.jpa.MovieRepository;
import mk.ukim.finki.wp.eshop.service.UserService;
import org.apache.jena.base.Sys;
import org.apache.jena.query.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@RequiredArgsConstructor
public class DataHolder {

    public final MovieRepository movieRepository;
    public final CategoryRepository categoryRepository;
    public final UserService userService;

    @PostConstruct
    public void init() {

        String SPARQLEndpoint = "http://dbpedia.org/sparql";
        userService.register("darko", "darko", "darko", "darko", "darko", Role.ROLE_USER);

        String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "SELECT DISTINCT ?genre_name ?film_title " +
                "WHERE { " +
                "?film rdf:type <http://dbpedia.org/ontology/Film> . " +
                "?film rdfs:label ?film_title ." +
                "?film <http://dbpedia.org/ontology/genre> ?film_genre ." +
                "?film_genre rdfs:label ?genre_name " +
                "FILTER(LANGMATCHES(LANG(?film_title), 'en') && LANGMATCHES(LANG(?genre_name), 'en')) . " +
                "} ORDER BY RAND() LIMIT 50";

        System.out.println("Query: " + queryString);

        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.sparqlService(SPARQLEndpoint, query)) {
            ResultSet results = qexec.execSelect();
            while(results.hasNext()){
                Boolean exists = false;
                List<Genre> genreList = categoryRepository.findAll();
                QuerySolution soln = results.nextSolution();
                String genreString = soln.get("genre_name").toString();
                Genre genre = new Genre(genreString.substring(0, genreString.length()-3), "default desc");
                for (Genre g : genreList){
                    if (g.getName().equals(genreString.substring(0, genreString.length()-3))) {
                        genre = g;
                        break;
                    }
                }
                String movieString = soln.get("film_title").toString();
                Movie movie = new Movie(movieString.substring(0, movieString.length()-3), genre);

                categoryRepository.save(genre);
                movieRepository.save(movie);
            }
        }
    }
}
