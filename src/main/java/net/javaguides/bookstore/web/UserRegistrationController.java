package net.javaguides.bookstore.web;




import net.javaguides.bookstore.model.User;
import net.javaguides.bookstore.service.UserService;
import net.javaguides.bookstore.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
//@RequestMapping(path = "api/v1/registration")

public class UserRegistrationController<UserRegistrationDto> {

    @Autowired(required = false)
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return userRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result) {

        User existing = userService.findByEmail();
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            return "registration";
        }

        userService.save(userDto());
        return "redirect:/registration?success";
    }

    private net.javaguides.bookstore.web.dto.UserRegistrationDto userDto() {
        return null;
    }
}
