package net.javaguides.bookstore.repository;

import java.util.Optional;
import java.util.List;
import net.javaguides.bookstore.model.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStoreRepo extends JpaRepository<BookDetails, String> {

    void deleteBookDetailsId(Long id);

    Optional<BookDetails> findBookDetails(String id);

    @Query("{'genre': ?0}")
    static
    Optional<List<BookDetails>> findByGenre(String genre) {
        // TODO Auto-generated method stub
        return null;
    }
    
}