package mk.ukim.finki.wp.eshop.web.controller;

import mk.ukim.finki.wp.eshop.model.Movie;
import mk.ukim.finki.wp.eshop.model.ShoppingCart;
import mk.ukim.finki.wp.eshop.model.User;
import mk.ukim.finki.wp.eshop.service.MovieService;
import mk.ukim.finki.wp.eshop.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final MovieService movieService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, MovieService movieService) {
        this.shoppingCartService = shoppingCartService;
        this.movieService = movieService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("movies", this.shoppingCartService.listAllProductsInShoppingCart(shoppingCart.getId()));
        model.addAttribute("cart", shoppingCart);
        model.addAttribute("bodyContent", "shopping-cart");
        return "master-template";
    }

    @PostMapping("/add-movie/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            this.shoppingCartService.addProductToShoppingCart(user.getUsername(), id);
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }
    @DeleteMapping("/deleteitem/{id}/{itemid}")
    public String deleteItem(@PathVariable Long id, @PathVariable Long itemid, Authentication authentication){
        try {
            User user = (User) authentication.getPrincipal();
            Movie movie = this.movieService.findById(itemid).orElseThrow();
            this.shoppingCartService.removeFromShoppingCart(movie, user.getUsername());
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception){
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }
}
