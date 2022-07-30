package net.javaguides.bookstore.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.javaguides.bookstore.model.BookRating;
import net.javaguides.bookstore.repository.RateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class RateService {

    
/*
    Basic bookRating functions
    Add book
    
 */


    //public List<BookRating> getAllRatings() {
        //return RateRepository.findAll(); //Need to change this
    //}

    public void deleteRating (String id) {
        RateRepository.deleteID(id);
    }

    public void addRating(BookRating bookRating) {

        // Validates the user
        if (!isUserValid(bookRating.getID())) {
            throw new RuntimeException(String.format("User with ID %s is invalid!", bookRating.getID()));
        }

        // Validates the bookIDs
        if (!isBookValid(bookRating.getBookid())) {
            throw new RuntimeException(String.format("Book with ID %s is invalid!", bookRating.getBookid()));
        }

        // Validates the value passed for the rating (1-5 stars)
        int v = bookRating.getValue();
        if (v > 5 || v < 1) {
            throw new RuntimeException(String.format("Invalid value. Ratings must be between 1-5."));
        }

        //Gets a list of all the ratings from this user
        Optional<List<BookRating>> repositoryResults = RateRepository.findByUserId(bookRating.getID());

        //If no ratings exist, add the rating
        if (repositoryResults.isPresent() == false) {
            RateRepository.insert(bookRating); //Insert in repo
        }

        //There's some ratings
        else {


            //Check for existing rating on this specific book
            List<BookRating> queryResultsForUser = repositoryResults.get();
            List<BookRating> queryResultsForUserAndBook = new ArrayList<>();
            for (BookRating r : queryResultsForUser) {
                if (r.getBookid().equals(bookRating.getBookid())) {
                    queryResultsForUserAndBook.add(r);
                }
            }

            //If no rating for this specific book, insert it
            if (queryResultsForUserAndBook.size() == 0) {
                RateRepository.insert(bookRating);
            }

            //Rating exists, input error
            // PUT API should be used to update instead of insert
            else {
                throw new RuntimeException(String.format("Found Existing Rating for Book ID %s by User ID %s", bookRating.getBookid()
                        , bookRating.getID()));
            }
        }
    }

    public void updateRating (BookRating bookRating) {
        if (!isUserValid(bookRating.getID())) {
            throw new RuntimeException(String.format("User with ID %s is invalid!", bookRating.getID()));
        }

        if (!isBookValid(bookRating.getBookid())) {
            throw new RuntimeException(String.format("Book with ID %s is invalid!", bookRating.getBookid()));
        }

        int v = bookRating.getValue();
        if (v > 5 || v < 1) {
            throw new RuntimeException(String.format("Invalid value. Ratings must be between 1-5."));
        }

        Optional<List<BookRating>> repositoryResults = RateRepository.findByUserId(bookRating.getID());
        if (repositoryResults.isPresent() == false) {
            throw new RuntimeException(String.format("Cannot find ratings by user %s",bookRating.getID()));
        }

        else {
            List<BookRating> queryResultsForUser = repositoryResults.get();
            List<BookRating> queryResultsForUserAndBook = new ArrayList<>();
            for (BookRating r : queryResultsForUser) {
                if (r.getBookid().equals(bookRating.getBookid())) {
                    queryResultsForUserAndBook.add(r);
                }
            }

            if (queryResultsForUserAndBook.size() == 0) {
                throw new RuntimeException(String.format("Cannot find bookRating for book ID %s from user %s", bookRating.getBookid()
                        , bookRating.getID()));
            }

            if (queryResultsForUserAndBook.size() > 1) {
                throw new RuntimeException(String.format("Duplicate ratings found for book ID %s from user %s", bookRating.getBookid()
                        , bookRating.getID()));
            }

            else {
                BookRating saveRate = queryResultsForUserAndBook.get(0);
                saveRate.setId(bookRating.getID());
                saveRate.setBookid(bookRating.getBookid());
                saveRate.setDate(bookRating.getDate());
                saveRate.setValue(bookRating.getValue());
                saveRate.setComment(bookRating.getComment());
                
                //RateRepository.save(saveRate);
            }
        }

    }
    
    private boolean isUserValid(String userID) {
        String uri = "http://localhost:8080/BookStore/";
        uri += userID;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, boolean.class);
    }

    private boolean isBookValid(String bookID) {

        String uri = "http://localhost:8080/BookStore/";
        uri += bookID;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, boolean.class);
    }


    /*
    -Must be able to create a bookRating for a book by a user on a 5-star scale with a 
    datestamp. 
    -Must be able to create a comment for a book by a user with a datestamp 
    -Must be able to retrieve a list of ratings and comments sorted by highest bookRating 
    -Must be able to retrieve the average bookRating for a book
 */

public List<BookRating> getRatingsByUser(String userID) {

    if (!isUserValid(userID)) {
        throw new RuntimeException(String.format("User with ID %s is invalid!", userID));
    }

    return RateRepository.findByUserId(userID).orElseThrow(() -> new RuntimeException(
            String.format("Cannot find Ratings by User %s", userID)));
    }

public List<BookRating> getRatingsByBook(String bookId) {

    if (!isBookValid(bookId)) {
        throw new RuntimeException(String.format("Book with ID %s is invalid!", bookId));
    }

    return RateRepository.findByBookId(bookId).orElseThrow(() -> new RuntimeException(
            String.format("Cannot find Ratings for Book %s", bookId)
    )
  );
}

public float getAverageRating(String bookid) {

    List<BookRating> allRatingsByBook = getRatingsByBook(bookid);
    long sum = 0;

    for (BookRating r : allRatingsByBook) {
        sum += r.getValue();
    }

    return sum / (float) allRatingsByBook.size();
}

public List<BookRating> getRatingsByBookSortedDes(String bookid) {
    return null;
}

public List<BookRating> getRatingsByBookSortedAsc(String bookid) {
    List<BookRating> unsortedRatings = getRatingsByBook(bookid);

    return null;
}

public List<BookRating> getRatingsSortedAsc() {
    return null;
}

public List<BookRating> getRatingsSortedDes() {
    return null;
}


    public Object getAllRatings() {
        return null;
    }
}
