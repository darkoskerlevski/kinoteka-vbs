package mk.ukim.finki.wp.eshop.service;

import mk.ukim.finki.wp.eshop.model.Movie;
import mk.ukim.finki.wp.eshop.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Movie> listAllProductsInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username, Long productId);
}
