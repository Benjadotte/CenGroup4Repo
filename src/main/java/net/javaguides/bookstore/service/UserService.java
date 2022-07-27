package net.javaguides.bookstore.service;



import net.javaguides.bookstore.model.User;
import net.javaguides.bookstore.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    User findByEmail();

    User save(UserRegistrationDto registration);


    User findUserById(Long id);

    User addUser(User user);

    User updateUser(User user);
}
