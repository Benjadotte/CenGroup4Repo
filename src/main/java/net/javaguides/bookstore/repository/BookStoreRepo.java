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
 
    Optional <BookDetails> findBookDetails(Long id);
 
    @Query("{'id': ?0}")
    Optional<BookDetails> findById(Long id);
 
    @Query("{'genre': ?0}")
    Optional<List<BookDetails>> findByGenre(String genre);
 
 }