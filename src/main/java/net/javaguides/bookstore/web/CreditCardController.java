package net.javaguides.bookstore.web;

import com.mongodb.DuplicateKeyException;
import net.javaguides.bookstore.model.CreditCard;
import net.javaguides.bookstore.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class CreditCardController {
    @Autowired
    private CreditCardService creditCardManagementService;

    @GetMapping("/creditCards/getAll")
    public List<CreditCard> getAllCreditCards() {
        return creditCardManagementService.findAll();
    }

    @PostMapping("/creditCards/add")
    public ResponseEntity<?> createCreditCard(@RequestBody CreditCard creditCard) {
        if (creditCard.checkLuhn10()) {
            try {
                creditCardManagementService.addCreditCard(creditCard);
                return new ResponseEntity<Object>(HttpStatus.OK);
            } catch (DuplicateKeyException e) {
                return new ResponseEntity<Error>(HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<Error>(HttpStatus.FORBIDDEN);
    }

}

