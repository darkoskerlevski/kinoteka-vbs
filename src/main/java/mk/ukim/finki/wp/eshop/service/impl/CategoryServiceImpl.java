package mk.ukim.finki.wp.eshop.service.impl;

import mk.ukim.finki.wp.eshop.model.Genre;
import mk.ukim.finki.wp.eshop.repository.impl.InMemoryCategoryRepository;
import mk.ukim.finki.wp.eshop.repository.jpa.CategoryRepository;
import mk.ukim.finki.wp.eshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final InMemoryCategoryRepository categoryRepository;

    public CategoryServiceImpl(InMemoryCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Genre create(String name, String description) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Genre c = new Genre(name,description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public Genre update(String name, String description) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Genre c= new Genre(name,description);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public void delete(String name) {
        if (name==null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        //categoryRepository.deleteByName(name);
    }

    @Override
    public List<Genre> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Genre> searchCategories(String searchText) {
        return null;
        //return categoryRepository.findAllByNameLike(searchText);
    }
}
