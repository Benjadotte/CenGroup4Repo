package net.javaguides.bookstore.repository;
import net.javaguides.bookstore.model.BookRating;
import net.javaguides.bookstore.service.RateService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository

public interface RateRepository  extends JpaRepository<BookRating, String>{
    @Query("{'id': ?0}")
    Optional<BookRating> findById(String id);

    @Query("{'userid': ?0}")
    static
    Optional<List<BookRating>> findByUserId(String userid) {
        // TODO Auto-generated method stub
        return null;
    }


    @Query("{'bookid': ?0}")
    Optional<List<BookRating>> findByBookId(String bookid);

    //.
}
