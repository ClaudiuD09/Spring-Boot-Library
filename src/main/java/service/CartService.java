package service;


import model.Book;
import model.CartItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CartService {

	private final List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void addToCart(Book book, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getBook().getId().equals(book.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(book, quantity));
    }

    public void removeFromCart(Long bookId) {
        cartItems.removeIf(item -> item.getBook().getId().equals(bookId));
    }

    public void clearCart() {
        cartItems.clear();
    }
}
