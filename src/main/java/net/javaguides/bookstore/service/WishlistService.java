package net.javaguides.bookstore.service;

import  net.javaguides.bookstore.model.BookDetails;
import  net.javaguides.bookstore.model.Wishlist;
import  net.javaguides.bookstore.repository.WishlistRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {
    
    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    

    public void addWishlist(Wishlist wishlist){

        Optional<Wishlist> repositoryResults = wishlistRepository.findById(wishlist.getId());

        if (repositoryResults.isPresent()) {
            throw new RuntimeException(String.format("Found Existing Wishlist with ID %s", wishlist.getId()));
        }
        else {
            wishlistRepository.insert(wishlist);
        }

    }

    public List<Wishlist> getAllWishlists(){
        return wishlistRepository.findAll();
    }

    public void updateWishlist(Wishlist wishlist) {

        
        Optional<Wishlist> repositoryResults = wishlistRepository.findById(wishlist.getId());

        
        if (repositoryResults.isEmpty()) {
            throw new RuntimeException(String.format("Cannot find Wishlist with ID %s", wishlist.getId()));
        }

       
        else {
            Wishlist savedWishlist = repositoryResults.get();

            savedWishlist.setUserid(wishlist.getUserid());
            savedWishlist.setBooks(wishlist.getBooks());
            savedWishlist.setWishlistName(wishlist.getWishlistName());

            wishlistRepository.save(savedWishlist);
        }
    }

    public void deleteWishlist(String id) {
        wishlistRepository.deleteById(id);
    }

   

    public void AddBookToWishlist(String wishlistid, String bookid) {

       
        Optional<Wishlist> repositoryResults = wishlistRepository.findById(wishlistid);

        
        if (repositoryResults.isEmpty()) {
            throw new RuntimeException(String.format("Cannot find Wishlist with ID %s", wishlistid));
        }

        

        Wishlist savedWishlist = repositoryResults.get();
        ArrayList<String> booksInWishlist = savedWishlist.getBooks();

        if (booksInWishlist == null) {
            booksInWishlist = new ArrayList<String>();
        }

        
        if (booksInWishlist.isEmpty()) {
            booksInWishlist.add(bookid);
            savedWishlist.setBooks(booksInWishlist);
            wishlistRepository.save(savedWishlist);
        }

       
        else {
            if (!booksInWishlist.contains(bookid)) {
                booksInWishlist.add(bookid);
                savedWishlist.setBooks(booksInWishlist);
                wishlistRepository.save(savedWishlist);
            }
        }
    }

    public void RemoveBookFromWishlist(String wishlistid, String bookid) {

        
        Optional<Wishlist> repositoryResults = wishlistRepository.findById(wishlistid);

        
        if (repositoryResults.isEmpty()) {
            throw new RuntimeException(String.format("Cannot find Wishlist with ID %s", wishlistid));
        }

       

        Wishlist savedWishlist = repositoryResults.get();
        ArrayList<String> booksInWishlist = savedWishlist.getBooks();

        if (booksInWishlist == null) {
            booksInWishlist = new ArrayList<String>();
            savedWishlist.setBooks(booksInWishlist);
            wishlistRepository.save(savedWishlist);
        }

       
        if (!booksInWishlist.isEmpty()) {
            booksInWishlist.remove(bookid);
            savedWishlist.setBooks(booksInWishlist);
            wishlistRepository.save(savedWishlist);
        }
    }

    public List<BookDetails> getWishlistContents(String wishlistid) {

        
        Optional<Wishlist> repositoryResults = wishlistRepository.findById(wishlistid);

       
        if (repositoryResults.isEmpty()) {
            throw new RuntimeException(String.format("Cannot find Wishlist with ID %s", wishlistid));
        }

        else {
            Wishlist wishlist = repositoryResults.get();
            ArrayList<String> booksInWishlist = wishlist.getBooks();

            if (booksInWishlist == null) {
                booksInWishlist = new ArrayList<String>();
                wishlist.setBooks(booksInWishlist);
                wishlistRepository.save(wishlist);
            }

            ArrayList<BookDetails> books = new ArrayList<>();

            if (!booksInWishlist.isEmpty()) {
                for (String c : booksInWishlist) {
                    books.add(getBookInfo(c));
                }
            }

            return books;
        }
    }

    private BookDetails getBookInfo(String bookID) {

        
        String uri = "http://localhost:8080/api/book/byID/";
        uri += bookID;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, BookDetails.class);
    }

    public void pushBookToCart(String wishlistID, String bookID, String cartID) {

        String uri = "http://localhost:8080/api/cart/" + cartID + "/addBook/" + bookID;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(uri, String.class);

        RemoveBookFromWishlist(wishlistID, bookID);
    }

}