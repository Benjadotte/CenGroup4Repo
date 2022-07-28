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
    Basic rating functions
    Add book
    
 */


    //public List<BookRating> getAllRatings() {
        //return RateRepository.findAll(); //Need to change this
    //}

    public void deleteRating (String id) {
        RateRepository.deleteID(id);
    }

    public void addRating(BookRating rating) {

        // validate the user through a call to the User Controller
        if (!isUserValid(rating.getID())) {
            throw new RuntimeException(String.format("User with ID %s is invalid!", rating.getID()));
        }

        // validate the book through a call to the Book Controller
        if (!isBookValid(rating.getBookid())) {
            throw new RuntimeException(String.format("Book with ID %s is invalid!", rating.getBookid()));
        }

        // validate the value passed for the rating (1-5 stars)
        int v = rating.getValue();
        if (v > 5 || v < 1) {
            throw new RuntimeException(String.format("Invalid value. Ratings must be between 1-5."));
        }

        // get a list of all ratings in the database by this user
        Optional<List<BookRating>> repositoryResults = RateRepository.findByUserId(rating.getID());

        // if no ratings already exist for this user, go ahead and add the new rating (user/book were validated earlier)
        if (repositoryResults.isPresent() == false) {
            RateRepository.insert(rating); //Insert in repo
        }

        // some ratings do exist by this user
        else {


            // check that there is not already an existing rating by this user for the specified book
            List<BookRating> queryResultsForUser = repositoryResults.get();
            List<BookRating> queryResultsForUserAndBook = new ArrayList<>();
            for (BookRating r : queryResultsForUser) {
                if (r.getBookid().equals(rating.getBookid())) {
                    queryResultsForUserAndBook.add(r);
                }
            }

            // no rating by this user for this book, so insert it
            if (queryResultsForUserAndBook.size() == 0) {
                RateRepository.insert(rating);
            }

            // rating for this book by this user already exists, so throw error
            // PUT API should be used to update instead of insert
            else {
                throw new RuntimeException(String.format("Found Existing Rating for Book ID %s by User ID %s", rating.getBookid()
                        , rating.getID()));
            }
        }
    }

    public void updateRating (BookRating rating) {

    }
    
    private boolean isUserValid(String userID) {
        String uri = "";
        uri += userID;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, boolean.class);
    }

    private boolean isBookValid(String bookID) {

        String uri = "";
        uri += bookID;

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, boolean.class);
    }


    /*
    -Must be able to create a rating for a book by a user on a 5-star scale with a 
    datestamp. 
    -Must be able to create a comment for a book by a user with a datestamp 
    -Must be able to retrieve a list of ratings and comments sorted by highest rating 
    -Must be able to retrieve the average rating for a book
 */

public List<BookRating> getRatingsByUser(String userId) {

    // validate the user through a call to the User Controller
    if (!isUserValid(userId)) {
        throw new RuntimeException(String.format("User with ID %s is invalid!", userId));
    }

    return RateRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException(
            String.format("Cannot find Ratings by User %s", userId)
    )
  );
}

public List<BookRating> getRatingsByBook(String bookId) {

    // validate the book through a call to the Book Controller
    if (!isBookValid(bookId)) {
        throw new RuntimeException(String.format("Book with ID %s is invalid!", bookId));
    }

    return RateRepository.findByBookId(bookId).orElseThrow(() -> new RuntimeException(
            String.format("Cannot find Ratings for Book %s", bookId)
    )
  );
}

public float getAverageRating(String bookid) {
    // validation of bookid done in getRatingsByBook method

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
