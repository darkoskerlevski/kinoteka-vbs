//package mk.ukim.finki.wp.eshop.repository.impl;
//
//import mk.ukim.finki.wp.eshop.bootstrap.DataHolder;
//import mk.ukim.finki.wp.eshop.model.Genre;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Repository
//public class InMemoryCategoryRepository {
//
//    public List<Genre> findAll() {
//        return DataHolder.genres;
//    }
//
//    public Genre save(Genre c) {
//        if (c==null || c.getName().isEmpty()) {
//            return null;
//        }
//        DataHolder.genres.removeIf(r->r.getName().equals(c.getName()));
//        DataHolder.genres.add(c);
//        return c;
//    }
//
//    public Optional<Genre> findByName(String name) {
//        return DataHolder.genres.stream().filter(r->r.getName().equals(name)).findFirst();
//    }
//
//    public Optional<Genre> findById(Long id) {
//        return DataHolder.genres.stream().filter(r->r.getId().equals(id)).findFirst();
//    }
//
//    public List<Genre> search(String text) {
//        return DataHolder.genres.stream().filter(r->r.getName().contains(text) || r.getDescription().contains(text)).collect(Collectors.toList());
//    }
//
//    public void delete(String name) {
//        DataHolder.genres.removeIf(r->r.getName().equals(name));
//    }
//}
