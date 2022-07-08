package mk.ukim.finki.wp.eshop.bootstrap;

import lombok.Getter;
import mk.ukim.finki.wp.eshop.model.*;
import mk.ukim.finki.wp.eshop.repository.impl.InMemoryMovieRepository;
import mk.ukim.finki.wp.eshop.repository.jpa.MovieRepository;
import org.apache.jena.base.Sys;
import org.apache.jena.query.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Genre> genres = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Movie> movies = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();

    @PostConstruct
    public void init() {


        genres.add(new Genre("Test", "Testtest"));

        String SPARQLEndpoint = "http://dbpedia.org/sparql";

        String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "SELECT DISTINCT ?genre_name ?film_title " +
                "WHERE { " +
                "?film rdf:type <http://dbpedia.org/ontology/Film> . " +
                "?film rdfs:label ?film_title ." +
                "?film <http://dbpedia.org/ontology/genre> ?film_genre ." +
                "?film_genre rdfs:label ?genre_name " +
                "FILTER(LANGMATCHES(LANG(?film_title), 'en') && LANGMATCHES(LANG(?genre_name), 'en')) . " +
                "} ORDER BY RAND() LIMIT 10";

        System.out.println("Query: " + queryString);

        Query query = QueryFactory.create(queryString);
        try (QueryExecution qexec = QueryExecutionFactory.sparqlService(SPARQLEndpoint, query)) {
            ResultSet results = qexec.execSelect();
            while(results.hasNext()){
                QuerySolution soln = results.nextSolution();
                String genreString = soln.get("genre_name").toString();
                Genre genre = new Genre(genreString.substring(0, genreString.length()-3), "default desc");
                String movieString = soln.get("film_title").toString();
                Movie movie = new Movie(movieString.substring(0, movieString.length()-3), genre);
                movies.add(movie);
                genres.add(genre);
//                System.out.println("Film: " + soln.get("film_title").toString());
//                System.out.println("Abstract: " + soln.get("film_abstract").toString());
            }
        }
    }
}
