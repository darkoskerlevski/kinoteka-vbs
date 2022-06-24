package mk.ukim.finki.wp.eshop.service;

import mk.ukim.finki.wp.eshop.model.Genre;

import java.util.List;

public interface CategoryService {

    Genre create(String name, String description);

    Genre update(String name, String description);

    void delete(String name);

    List<Genre> listCategories();

    List<Genre> searchCategories(String searchText);

}
