package net.javaguides.bookstore.service;


import net.javaguides.bookstore.model.Role;
import net.javaguides.bookstore.model.User;
import net.javaguides.bookstore.repository.UserRepository;
import net.javaguides.bookstore.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User findByEmail() {
        return null;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {

        User user = new User(registrationDto.getFirstName(),
                registrationDto.getLastName(),
                registrationDto.getEmail(),
                passwordEncoder.encode(registrationDto
                        .getPassword()),
                Arrays.asList(new Role("ROLE_USER")));

        return userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public org.springframework.security.core.userdetails.User loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException
                    ("Invalid username or password.");
        }
        return new org.springframework.security
                .core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority>
    mapRolesToAuthorities(Collection<Role> roles) {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority
                        (role.getName()))
                .collect(Collectors.toList());
    }

    public List<User> getAll() {

        return userRepository.findAll();
    }
}

    /*@RestController
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
            }*/






