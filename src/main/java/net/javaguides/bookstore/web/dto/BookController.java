package net.javaguides.bookstore.web.dto;

import net.javaguides.bookstore.model.BookDetails;
import net.javaguides.bookstore.service.BookStoreService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;



//@CrossOrigin

@RestController
@RequestMapping("/bookstore")
public class BookController
{

    private final BookStoreService bookStoreService;

    public BookController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }


    /*

    Basic Mapping functions

    getAllBookDetils
    getBookDetilsById
    addBookDetails
    updateBookDetails
    deleteBookDetails

     */

    //getAllBookDetils
    @GetMapping("/all")
    public ResponseEntity<List<BookDetails>> getAllBookDetils()
    {
        List<BookDetails> bookDetails = bookStoreService.findALLBooks();
        return new ResponseEntity<>(bookDetails, HttpStatus.OK);
    }
/*
    //getBookDetilsById
    @GetMapping("/find/{id}")
    public ResponseEntity<BookDetails> getBookDetilsById(@PathVariable("id") Long id)
    {
        BookDetails bookDetails = bookStoreService.findBookDetails(id);
        return new ResponseEntity<>(bookDetails, HttpStatus.OK);
    }
*/
    //addBookDetails
    @PostMapping("/add")
    public ResponseEntity<BookDetails> addBookDetails(@RequestBody BookDetails bookDetails)
    {
        BookDetails newBookDetails = bookStoreService.addBookDetails(bookDetails);
        return new ResponseEntity<>(newBookDetails, HttpStatus.CREATED);
    }

    //updateBookDetails
    @PutMapping("/update")
    public ResponseEntity<BookDetails> updateBookDetails(@RequestBody BookDetails bookDetails)
    {
        BookDetails updateBookDetails = bookStoreService.addBookDetails(bookDetails);
        return new ResponseEntity<>(updateBookDetails, HttpStatus.OK);
    }
/*
    //deleteBookDetails
    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteBookDetails(@PathVariable("id") Long id)
    {
        bookStoreService.deleteBookDetails(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


 */
}
