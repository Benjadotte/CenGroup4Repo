package net.javaguides.bookstore.web.dto;

import net.javaguides.bookstore.model.BookDetails;
import net.javaguides.bookstore.model.Wishlist;
import net.javaguides.bookstore.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    
    private final WishlistServices wishlistService;

    public WishlistController(WishlistServices wishlistservice){
        this.wishlistService = wishlistservice;
    }

    
    @PostMapping
    public ResponseEntity addWishlist(@RequestBody Wishlist wishlist){
        wishlistService.addWishlist(wishlist);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    
    @GetMapping
    public ResponseEntity<List<Wishlist>> getAllWishlists(){
        return ResponseEntity.ok(wishlistService.getAllWishlists());
    }

    
    @PutMapping
    public ResponseEntity updateWishlist(@RequestBody Wishlist wishlist) {
        wishlistService.updateWishlist(wishlist);
        return ResponseEntity.ok(wishlistService.getAllWishlists());
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteWishlist(@PathVariable String id) {
        wishlistService.deleteWishlist(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    

    @GetMapping("/{wishlistid}")
    public ResponseEntity<List<Book>> getWishlistContents(@PathVariable String wishlistid){
        return ResponseEntity.ok(wishlistService.getWishlistContents(wishlistid));
    }

    @PutMapping("/{wishlistid}/addBook/{bookid}")
    public ResponseEntity addBookToWishlist(@PathVariable String wishlistid, @PathVariable String bookid) {
        wishlistService.AddBookToWishlist(wishlistid, bookid);
        return ResponseEntity.ok(wishlistService.getWishlistContents(wishlistid));
    }

    @PutMapping("/{wishlistid}/removeBook/{bookid}")
    public ResponseEntity removeBookFromWishlist(@PathVariable String wishlistid, @PathVariable String bookid) {
        wishlistService.RemoveBookFromWishlist(wishlistid, bookid);
        return ResponseEntity.ok(wishlistService.getWishlistContents(wishlistid));
    }

    @PutMapping("/{wishlistid}/pushBook/{bookid}/toCart/{cartid}")
    public ResponseEntity pushBookToCart(@PathVariable String wishlistid, @PathVariable String bookid, @PathVariable String cartid) {
        wishlistService.pushBookToCart(wishlistid, bookid, cartid);
        return ResponseEntity.ok(wishlistService.getWishlistContents(wishlistid));
    }

}