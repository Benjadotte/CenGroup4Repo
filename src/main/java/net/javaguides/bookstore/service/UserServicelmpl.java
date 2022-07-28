package net.javaguides.bookstore.service;


import net.javaguides.bookstore.model.BookRating;
import net.javaguides.bookstore.model.Role;
import net.javaguides.bookstore.model.User;
import net.javaguides.bookstore.repository.UserRepository;
import net.javaguides.bookstore.web.dto.UserRegistrationDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public abstract class UserServicelmpl implements UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User save(UserRegistrationDto registration) {
        User user = new User();
        user.setName(registration.getName());
        user.setPhoneNumber(registration.getPhoneNumber());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        user.setHomeAddress(registration.getHomeAddress());
        user.setCreditCardNumber(registration.getCreditCardNumber());
        return userRepository.save(user);
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @RestController
    @RequestMapping("/api/bookrating")
    @CrossOrigin


    public static class RateController {
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
            public ResponseEntity<Object> getAllRatings() {
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
}
