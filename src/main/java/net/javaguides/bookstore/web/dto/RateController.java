package net.javaguides.bookstore.web.dto;

import net.javaguides.bookstore.service.RateService;
import net.javaguides.bookstore.model.BookRating;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/api/bookrating")
@CrossOrigin


public class RateController {
        private final RateService rateService;
        private BookRating bookrating;

        public RateController(RateService rateService) {
            this.rateService = rateService;
        }

        @PostMapping()
        public ResponseEntity addRating(@RequestBody RateService rateService) {
            rateService.addRating(bookrating); //rating change
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    
        @GetMapping
        public ResponseEntity<List<BookRating>> getAllRatings() {
            return ResponseEntity.ok(rateService.getAllRatings());
        }
    
        @PutMapping
        public ResponseEntity updateRating(@RequestBody BookRating rating) {
            rateService.updateRating(rating);
            return ResponseEntity.ok(rateService.getAllRatings());
        }
    
        @DeleteMapping("/{id}")
        public ResponseEntity deleteRating(@PathVariable String id) {
            rateService.deleteRating(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    
        // Additional API functionality
    
        @GetMapping("/byuser/{userid}")
        public ResponseEntity<List<BookRating>> getRatingsByUser(@PathVariable String userid) {
            return ResponseEntity.ok(rateService.getRatingsByUser(userid));
        }
    
        @GetMapping("/bybook/{bookid}")
        public ResponseEntity<List<BookRating>> getRatingsByBook(@PathVariable String bookid) {
            return ResponseEntity.ok(rateService.getRatingsByBook(bookid));
        }
    
        @GetMapping("/bybook/sorted/highest/{bookid}")
        public ResponseEntity<List<BookRating>> getRatingsByBookSortedDes(@PathVariable String bookid) {
            return ResponseEntity.ok(rateService.getRatingsByBookSortedDes(bookid));
        }
    
        @GetMapping("/bybook/sorted/lowest/{bookid}")
        public ResponseEntity<List<BookRating>> getRatingsByBookSortedAsc(@PathVariable String bookid) {
            return ResponseEntity.ok(rateService.getRatingsByBookSortedAsc(bookid)); //Add methods to rate service
        }
    
        @GetMapping("/allbooks/sorted/highest")
        public ResponseEntity<List<BookRating>> getRatingsByBookSortedDes() {
            return ResponseEntity.ok(rateService.getRatingsSortedDes());
        }
    
        @GetMapping("/allbooks/sorted/lowest")
        public ResponseEntity<List<BookRating>> getRatingsByBookSortedAsc() {
            return ResponseEntity.ok(rateService.getRatingsSortedAsc());
        }
    
        @GetMapping("/avg/{bookid}")
        public ResponseEntity getAverageRating(@PathVariable String bookid) {
            return ResponseEntity.ok(rateService.getAverageRating(bookid));
        }



        
}
