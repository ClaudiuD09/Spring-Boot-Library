package controller;



import model.Book;
import model.CartItem;
import service.BookService;
import service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {

	private final CartService cartService;
    private final BookService bookService;

    public CartController(CartService cartService, BookService bookService) {
        this.cartService = cartService;
        this.bookService = bookService;
    }

    @GetMapping
    public List<CartItem> getCartItems() {
        return cartService.getCartItems();
    }

    @PostMapping("/{bookId}")
    public String addToCart(@PathVariable Long bookId, @RequestParam int quantity) {
        Book book = bookService.getAllBooks().stream()
                .filter(b -> b.getId().equals(bookId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found"));
        cartService.addToCart(book, quantity);
        return "Book added to cart!";
    }

    @DeleteMapping("/{bookId}")
    public String removeFromCart(@PathVariable Long bookId) {
        cartService.removeFromCart(bookId);
        return "Book removed from cart!";
    }

    @DeleteMapping
    public String clearCart() {
        cartService.clearCart();
        return "Cart cleared!";
    }
    
}
