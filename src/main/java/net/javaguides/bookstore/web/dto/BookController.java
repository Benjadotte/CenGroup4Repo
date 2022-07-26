package net.javaguides.bookstore.web.dto;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.bookstore.model.BookDetails;
//import com.bookstore.backend.repo.BookStoreRepo;
import net.javaguides.bookstore.service.BookStoreService;

@RestController
@RequestMapping("/bookstore")
@CrossOrigin
public class BookController 
{
    
    private final BookStoreService bookStoreService;

    public BookController(BookStoreService bookStoreService)
    {
        this.bookStoreService = bookStoreService;
    }


    /*
    Basic Mapping functions

    Add book
    find all books
    update book details
    find book by id
    delete book

     */

    @GetMapping("/all")
    public ResponseEntity<List<BookDetails>> getAllBookDetils()
    {
        List<BookDetails> bookDetails = bookStoreService.findALLBooks();
        return new ResponseEntity<>(bookDetails, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<BookDetails> getBookDetilsById(@PathVariable("id") String id)
    {
        BookDetails bookDetails = bookStoreService.findBookDetails(id);
        return new ResponseEntity<>(bookDetails, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<BookDetails> addBookDetails(@RequestBody BookDetails bookDetails)
    {
        BookDetails newBookDetails = bookStoreService.addBookDetails(bookDetails);
        return new ResponseEntity<>(newBookDetails, HttpStatus.CREATED);
    }
    
    @PutMapping("/update")
    public ResponseEntity<BookDetails> updateBookDetails(@RequestBody BookDetails bookDetails)
    {
        BookDetails updateBookDetails = bookStoreService.addBookDetails(bookDetails);
        return new ResponseEntity<>(updateBookDetails, HttpStatus.OK);
    }


    /*
    Retrieve List of Books by Genre 

    Retrieve List of Top Sellers (Top 10 books that have sold the most copied) 

    Retrieve List of Books for a particular rating and higher 

    Retrieve List of X Books at a time where X is an integer from a given position in the overall recordset.
    */ 

    //Retrieve List of Books by Genre 
    @GetMapping("/byGenre/{genre}")
    public ResponseEntity<List<BookDetails>> getBookByGenre(@PathVariable String genre) 
    {
        return ResponseEntity.ok(bookStoreService.getBookByGenre(genre));
    }


    //Retrieve List of Top Sellers (Top 10 books that have sold the most copied) 
    @GetMapping("/tenMostSold")
    public ResponseEntity<List<BookDetails>> tenMostSold() 
    {
        return ResponseEntity.ok(bookStoreService.tenMostSold());
    }


    //Retrieve List of Books for a particular rating and higher 
    @GetMapping("/ratinghigherthan/{value}")
    public ResponseEntity<List<BookDetails>> getBooksWithRatingAvgHigherThan(@PathVariable float value) 
    {
        return ResponseEntity.ok(bookStoreService.getBooksWithAvgRatingHigherThan(value));
    }


    //Retrieve List of X Books at a time where X is an integer from a given position in the overall recordset.
    @GetMapping("/by/{quantity}/from/{position}")
    public ResponseEntity<List<BookDetails>> getSubset(@PathVariable int quantity, @PathVariable int position) 
    {
        return ResponseEntity.ok(bookStoreService.getSubset(quantity, position));
    }






}
