package net.javaguides.bookstore.service;



import net.javaguides.bookstore.constraint.BookNotFoundException;
import net.javaguides.bookstore.model.BookDetails;
import net.javaguides.bookstore.repository.BookStoreRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookStoreService
{

    private final BookStoreRepo bookStoreRepo;

    @Autowired
    public BookStoreService(BookStoreRepo bookStoreRepo)
    {
        this.bookStoreRepo = bookStoreRepo;
    }
/*
    Basic book functions

    Add book
    find all books
    update book details
    find book by id
    delete book

 */

    //Add book
    public BookDetails addBookDetails(BookDetails details)
    {
        details.setBookCode(UUID.randomUUID().toString());
        return bookStoreRepo.save(details);
    }

    //find all books
    public List<BookDetails> findALLBooks()
    {
        return bookStoreRepo.findAll();
    }

    //update book details
    public BookDetails updateBookDetails(BookDetails details)
    {
        return bookStoreRepo.save(details);
    }


    public BookDetails findBookDetails(Long id)
    {
        return ((BookStoreRepo) bookStoreRepo).findBookDetails(id).orElseThrow(() -> new BookNotFoundException("Book was not found " + id));
    }

    public void deleteBookDetails(Long id){
        ((BookStoreRepo) bookStoreRepo).deleteBookDetailsId(id);
    }


    /*
    -Retrieve List of Books by Genre

    -Retrieve List of Top Sellers (Top 10 books that have sold the most copied)

    -Retrieve List of Books for a particular rating and higher

    -Retrieve List of X Books at a time where X is an integer from a given position in the overall recordset.


*/


    //Retrieve List of Books by Genre
    public List<BookDetails> getBookByGenre(String genre) {
        return ((BookStoreRepo) bookStoreRepo).findByGenre(genre).orElseThrow(() -> new BookNotFoundException("Genre was not found " + genre));

    }


    //Retrieve List of Top Sellers (Top 10 books that have sold the most copied)
    public List<BookDetails> tenMostSold()
    {

        List<BookDetails> allBooks = ((JpaRepository<BookDetails, String>) bookStoreRepo).findAll(Sort.by(Sort.Direction.DESC, "numsold"));
        List<BookDetails> subsetBooks = allBooks.subList(0,10);
        return subsetBooks;
    }


    //Retrieve List of Books for a particular rating and higher
    public List<BookDetails> getBooksWithAvgRatingHigherThan(float value) {

        List<BookDetails> allBooks = findALLBooks();

        // List of all books which average rating was higher than parameter
        List<BookDetails> bookMatches = new ArrayList<BookDetails>();

        for (BookDetails book : allBooks) {
            if (bookAvgValue(book.getId()) >= value) {
                bookMatches.add(book);
            }
        }

        return bookMatches;
    }


    private float bookAvgValue(Long bookID) {

        // Set the server installation/config, do not hard coded
        String uri = "http://localhost:8080/api/rating/avg/";
        uri += bookID;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, float.class);
    }


    //Retrieve List of X Books at a time where X is an integer from a given position in the overall recordset.
    public List<BookDetails> getSubset(int quantity, int position) {

        List<BookDetails> allBooks = findALLBooks();

        if (position >= allBooks.size()) {
            throw new RuntimeException("This starting position exceeds the size of the collection");
        }

        List<BookDetails> subSet = new ArrayList<BookDetails>();

        for (int i = position; (i < allBooks.size()) && quantity > 0;  i++) {
            subSet.add(allBooks.get(i));
            --quantity;
        }

        return subSet;
    }


}
